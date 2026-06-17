package dev.mednikov.streaming.videos.mappers;

import org.mapstruct.Mapper;

import dev.mednikov.streaming.videos.domain.VideoMetadataResponse;
import dev.mednikov.streaming.videos.models.Video;

@Mapper
public interface VideoMetadataResponseMapper {
    
    VideoMetadataResponse map (Video video);

}
