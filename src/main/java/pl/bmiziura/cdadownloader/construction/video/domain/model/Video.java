package pl.bmiziura.cdadownloader.construction.video.domain.model;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {
    private Long id;
    private String title;
    private long duration;

    private Set<VideoSource> sources;
}
