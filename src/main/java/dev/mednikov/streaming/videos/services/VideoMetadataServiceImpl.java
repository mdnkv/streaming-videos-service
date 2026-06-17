package dev.mednikov.streaming.videos.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.mednikov.streaming.videos.domain.CreateVideoRequest;
import dev.mednikov.streaming.videos.domain.UpdateVideoRequest;
import dev.mednikov.streaming.videos.domain.VideoMetadataResponse;
import dev.mednikov.streaming.videos.exceptions.VideoDoesNotExistException;
import dev.mednikov.streaming.videos.mappers.VideoMetadataResponseMapper;
import dev.mednikov.streaming.videos.models.Video;
import dev.mednikov.streaming.videos.models.VideoStatusType;
import dev.mednikov.streaming.videos.repositories.VideoRepository;

@Service
public class VideoMetadataServiceImpl implements VideoMetadataService {

    private final VideoRepository videoRepository;
    private final VideoMetadataResponseMapper mapper;

    public VideoMetadataServiceImpl(VideoRepository videoRepository, VideoMetadataResponseMapper mapper) {
        this.videoRepository = videoRepository;
        this.mapper = mapper;
    }

    @Override
    public VideoMetadataResponse createVideo(CreateVideoRequest request) {
        // Create video
        Video video = new Video();
        video.setTitle(request.title());
        video.setDescription(request.description());
        video.setVisibility(request.visibility());
        video.setStatus(VideoStatusType.CREATED);
        video.setVersion(1L);

        // Persist video
        Video result = this.videoRepository.save(video);

        // TODO send VideoCreatedEvent

        // Map and return
        return this.mapper.map(result);
    }

    @Override
    public VideoMetadataResponse updateVideo(UpdateVideoRequest request) {
        // Find video or throw exception
        Video video = this.videoRepository.findById(request.id())
            .orElseThrow(() -> new VideoDoesNotExistException(request.id()));

        // Update video metadata
        video.setTitle(request.title());
        video.setDescription(request.description());
        video.setVisibility(request.visibility());
        video.setVersion(video.getVersion() + 1); // update version

        // Persist video
        Video result = this.videoRepository.save(video);

        // TODO send VideoUpdatedEvent

        // Map and return
        return this.mapper.map(result);
    }

    @Override
    public void deleteVideo(UUID id) {
        // find video by id or throw an exception
        Video video = this.videoRepository.findById(id)
            .orElseThrow(() -> new VideoDoesNotExistException(id));

        // TODO check video ownership

        // set video status to DELETED
        video.setStatus(VideoStatusType.DELETED);
        this.videoRepository.save(video);

        // TODO send VideoDeletedEvent
    }

    @Override
    public Optional<VideoMetadataResponse> getVideoById(UUID id) {
        return this.videoRepository.findById(id).map(this.mapper::map);
    }

    
    
}
