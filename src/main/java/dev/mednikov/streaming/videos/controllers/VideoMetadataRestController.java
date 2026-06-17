package dev.mednikov.streaming.videos.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.mednikov.streaming.videos.domain.CreateVideoRequest;
import dev.mednikov.streaming.videos.domain.UpdateVideoRequest;
import dev.mednikov.streaming.videos.domain.VideoMetadataResponse;
import dev.mednikov.streaming.videos.services.VideoMetadataService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoMetadataRestController {

    private final VideoMetadataService videoMetadataService;

    public VideoMetadataRestController(VideoMetadataService videoMetadataService) {
        this.videoMetadataService = videoMetadataService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody VideoMetadataResponse createVideo (@RequestBody @Valid CreateVideoRequest request) {
        return this.videoMetadataService.createVideo(request);
    }

    @PutMapping
    public @ResponseBody VideoMetadataResponse updateVideo (@RequestBody @Valid UpdateVideoRequest request) {
        return this.videoMetadataService.updateVideo(request);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoMetadataResponse> getVideoById (@PathVariable UUID videoId){
        Optional<VideoMetadataResponse> result = this.videoMetadataService.getVideoById(videoId);
        return ResponseEntity.of(result);
    }

    @DeleteMapping("/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVideo (@PathVariable UUID videoId){
        this.videoMetadataService.deleteVideo(videoId);
    }
    
}
