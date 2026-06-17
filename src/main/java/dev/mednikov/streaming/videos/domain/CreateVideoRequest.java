package dev.mednikov.streaming.videos.domain;

import org.hibernate.validator.constraints.Length;

import dev.mednikov.streaming.videos.models.VideoVisibilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVideoRequest(
    @NotNull @NotBlank @Length(max=255) String title,
    @NotNull @NotBlank String description,
    @NotNull VideoVisibilityType visibility
) {
    
}
