package dev.mednikov.streaming.videos.domain;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import dev.mednikov.streaming.videos.models.VideoVisibilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateVideoRequest(
    @NotNull UUID id,
    @NotNull @NotBlank @Length(max=255) String title,
    @NotNull @NotBlank String description,
    @NotNull VideoVisibilityType visibility
) {
    
}
