package pl.bmiziura.cdadownloader.construction.video.domain.model;

import lombok.*;
import pl.bmiziura.cdadownloader.construction.model.VideoSourceQuality;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoSource {
    private VideoSourceQuality quality;
    private String source;
}
