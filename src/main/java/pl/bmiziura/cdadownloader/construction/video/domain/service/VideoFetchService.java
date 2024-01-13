package pl.bmiziura.cdadownloader.construction.video.domain.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Service;
import pl.bmiziura.cdadownloader.construction.model.VideoSourceQuality;
import pl.bmiziura.cdadownloader.construction.video.domain.model.Video;
import pl.bmiziura.cdadownloader.construction.video.domain.model.VideoSource;
import pl.bmiziura.cdadownloader.exception.impl.VideoFetchException;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VideoFetchService {
    private static final String PLAYER_DATA_KEY = "player_data";

    private final Gson gson;
    private final HttpClient httpClient;

    public Video fetchVideoData(long id) {
        var url = getUrl(id);
        var connection = createConnection(url);

        Video video = new Video();

        try {
            var doc = connection.get();
            var body = doc.body();
            var element = body.getElementsByAttribute(PLAYER_DATA_KEY).stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("PlayerData element not found!"));
            var attr = element.attribute(PLAYER_DATA_KEY);
            var playerData = JsonParser.parseString(attr.getValue()).getAsJsonObject();
            var videoPlayer = playerData.get("video").getAsJsonObject();

            var qualities = videoPlayer.get("qualities").getAsJsonObject();
            var formats = new HashMap<String, String>();
            for (String key : qualities.keySet()) {
                formats.put(key, qualities.get(key).getAsString());
            }

            Set<VideoSource> sourceSet = new HashSet<>();

            for (String quality : formats.values()) {
                var source = new VideoSource();
                source.setQuality(VideoSourceQuality.valueOf(quality));

                var data = getData(videoPlayer, quality);

                var request = HttpRequest.newBuilder()
                        .uri(URI.create("https://www.cda.pl/"))
                        .header("User-Agent", "Chrome")
                        .POST(HttpRequest.BodyPublishers.ofString(data))
                        .build();

                var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if(response.statusCode() == 200) {
                    var sourceUrl = JsonParser.parseString(response.body()).getAsJsonObject()
                            .get("result").getAsJsonObject()
                            .get("resp").getAsString();

                    source.setSource(sourceUrl);
                } else {
                    throw new VideoFetchException("Fetch Response Error! (id: "+id+", status: "+response.statusCode()+", quality: "+quality+")");
                }

                sourceSet.add(source);
            }

            video.setId(id);
            video.setTitle(URLEncoder.encode(videoPlayer.get("title").getAsString(), Charset.defaultCharset()));
            video.setDuration(videoPlayer.get("duration").getAsLong());
            video.setSources(sourceSet);
        } catch (IOException | InterruptedException e) {
            throw new VideoFetchException(e.getMessage());
        }

        return video;
    }

    private String getData(JsonObject videoPlayer, String quality) {
        var videoId = videoPlayer.get("id").getAsString();
        var ts = videoPlayer.get("ts").getAsString();
        var hash2 = videoPlayer.get("hash2");

        return "{\"jsonrpc\":\"2.0\",\"method\":\"videoGetLink\",\"params\":[\""+videoId+"\",\""+quality+"\","+ts+","+hash2+",{}],\"id\":1}";
    }

    private Connection createConnection(String url) {
        return Jsoup.connect(url)
                .userAgent("cdadl");
    }

    private String getUrl(long id) {
        return "https://ebd.cda.pl/620x368/"+id;
    }
}
