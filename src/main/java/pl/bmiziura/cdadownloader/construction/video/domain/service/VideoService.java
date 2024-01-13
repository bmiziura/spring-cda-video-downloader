package pl.bmiziura.cdadownloader.construction.video.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bmiziura.cdadownloader.construction.model.entity.VideoSourceEntity;
import pl.bmiziura.cdadownloader.construction.model.repository.VideoRepository;
import pl.bmiziura.cdadownloader.construction.video.domain.mapper.VideoMapper;
import pl.bmiziura.cdadownloader.construction.video.domain.model.Video;
import pl.bmiziura.cdadownloader.exception.impl.VideoInvalidUrlException;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoFetchService videoDataService;
    private final VideoMapper videoMapper;

    public Video fetchAndSaveVideo(String url) {
        var id = extractIdFromUrl(url);

        return fetchAndSaveVideo(id);
    }

    public Video fetchAndSaveVideo(long id) {
        var video = videoRepository.findById(id);

        if (video.isPresent()) {
            videoRepository.deleteById(id);
        }

        var videoData = videoDataService.fetchVideoData(id);
        var entity = videoMapper.toVideoEntity(videoData);

        for (VideoSourceEntity source : entity.getSources()) {
            source.setVideo(entity);
        }

        return videoMapper.toVideo(videoRepository.save(entity));
    }

    private Long extractIdFromUrl(String url) {
        url = url.replace("https://", "");
        url = url.replace("http://", "");
        url = url.replace("www.", "");

        if(!url.startsWith("edb.cda.pl") && !url.startsWith("cda.pl")) {
            throw new VideoInvalidUrlException(url);
        }

        return Long.parseLong(url.split("/")[2]);
    }

    public Video getVideo(Long id) {
        return videoRepository.findById(id)
                .map(videoMapper::toVideo)
                .orElse(fetchAndSaveVideo(id));
    }
}
