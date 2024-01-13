package pl.bmiziura.cdadownloader.construction.video.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bmiziura.cdadownloader.construction.video.domain.model.Video;
import pl.bmiziura.cdadownloader.construction.video.domain.service.VideoService;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<Video> fetchVideo(@RequestParam String url) {
        return ResponseEntity.ok(videoService.fetchAndSaveVideo(url));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideo(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideo(id));
    }
}
