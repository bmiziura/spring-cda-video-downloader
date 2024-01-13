package pl.bmiziura.cdadownloader.construction.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VideoSourceQuality {
    vl("360p"),
    lq("480p"),
    sd("720p"),
    hd("1080p");

    private final String resolution;
}
