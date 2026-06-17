package dev.mednikov.streaming.videos.services;

import java.util.Optional;
import java.util.UUID;

import dev.mednikov.streaming.videos.domain.CreateVideoRequest;
import dev.mednikov.streaming.videos.domain.UpdateVideoRequest;
import dev.mednikov.streaming.videos.domain.VideoMetadataResponse;

public interface VideoMetadataService {
    
    VideoMetadataResponse createVideo (CreateVideoRequest request);

    VideoMetadataResponse updateVideo (UpdateVideoRequest request);

    Optional<VideoMetadataResponse> getVideoById (UUID id);

    void deleteVideo (UUID id);

}
