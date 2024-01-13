package pl.bmiziura.cdadownloader.construction.video.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import pl.bmiziura.cdadownloader.construction.model.entity.VideoEntity;
import pl.bmiziura.cdadownloader.construction.model.entity.VideoSourceEntity;
import pl.bmiziura.cdadownloader.construction.video.domain.model.Video;
import pl.bmiziura.cdadownloader.construction.video.domain.model.VideoSource;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VideoMapper {
    VideoEntity toVideoEntity(Video video);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "video", ignore = true)
    VideoSourceEntity toVideoSourceEntity(VideoSource videoSource);

    Video toVideo(VideoEntity entity);

    VideoSource toVideoSource(VideoSourceEntity entity);
}
