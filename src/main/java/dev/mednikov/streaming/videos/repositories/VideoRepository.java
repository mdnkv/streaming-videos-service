package dev.mednikov.streaming.videos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mednikov.streaming.videos.models.Video;

public interface VideoRepository extends JpaRepository<Video, UUID> {
    
}
