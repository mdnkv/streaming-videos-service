package dev.mednikov.streaming.videos.domain;

import java.util.UUID;

import dev.mednikov.streaming.videos.models.VideoStatusType;
import dev.mednikov.streaming.videos.models.VideoVisibilityType;

public record VideoMetadataResponse(
    UUID id,
    String title,
    String description,
    VideoVisibilityType visibility,
    VideoStatusType status
) {
    
}
