package dev.mednikov.streaming.videos.services;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.mednikov.streaming.videos.domain.CreateVideoRequest;
import dev.mednikov.streaming.videos.domain.UpdateVideoRequest;
import dev.mednikov.streaming.videos.domain.VideoMetadataResponse;
import dev.mednikov.streaming.videos.exceptions.VideoDoesNotExistException;
import dev.mednikov.streaming.videos.mappers.VideoMetadataResponseMapper;
import dev.mednikov.streaming.videos.models.Video;
import dev.mednikov.streaming.videos.models.VideoStatusType;
import dev.mednikov.streaming.videos.models.VideoVisibilityType;
import dev.mednikov.streaming.videos.repositories.VideoRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VideoMetadataServiceImplTest {

    @Mock private VideoRepository videoRepository;

    private VideoMetadataServiceImpl videoMetadataService;

    @BeforeEach
    void setup (){
        VideoMetadataResponseMapper mapper = Mappers.getMapper(VideoMetadataResponseMapper.class);
        this.videoMetadataService = new VideoMetadataServiceImpl(videoRepository, mapper);
    }

    @Test
    void getVideoById_existsTest(){
        UUID videoId = UUID.randomUUID();
        String title = "My video title";
        String description = "My video description";

        Video video = new Video();
        video.setId(videoId);
        video.setTitle(title);
        video.setDescription(description);
        video.setStatus(VideoStatusType.CREATED);
        video.setVisibility(VideoVisibilityType.PUBLIC);
        video.setVersion(1L);

        when(this.videoRepository.findById(videoId)).thenReturn(Optional.of(video));

        Optional<VideoMetadataResponse> response = this.videoMetadataService.getVideoById(videoId);

        assertThat(response).isNotNull().isPresent();

    }

    @Test
    void getVideoById_doesNotExistTest(){
        UUID videoId = UUID.randomUUID();

        when(this.videoRepository.findById(videoId)).thenReturn(Optional.empty());

        Optional<VideoMetadataResponse> response = this.videoMetadataService.getVideoById(videoId);

        assertThat(response).isNotNull().isEmpty();
    }

    @Test
    void deleteVideo_doesNotExistTest(){
        UUID videoId = UUID.randomUUID();

        when(this.videoRepository.findById(videoId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.videoMetadataService.deleteVideo(videoId))
            .isInstanceOf(VideoDoesNotExistException.class);
    }

    @Test
    void createVideoTest(){
        UUID videoId = UUID.randomUUID();
        String title = "My video title";
        String description = "My video description";

        Video video = new Video();
        video.setId(videoId);
        video.setTitle(title);
        video.setDescription(description);
        video.setStatus(VideoStatusType.CREATED);
        video.setVisibility(VideoVisibilityType.PUBLIC);
        video.setVersion(1L);

        CreateVideoRequest request = new CreateVideoRequest(title, description, VideoVisibilityType.PUBLIC);

        when(this.videoRepository.save(any(Video.class))).thenReturn(video);

        VideoMetadataResponse response = this.videoMetadataService.createVideo(request);

        assertThat(response)
            .isNotNull()
            .hasFieldOrPropertyWithValue("id", videoId)
            .hasFieldOrPropertyWithValue("title", title)
            .hasFieldOrPropertyWithValue("description", description)
            .hasFieldOrPropertyWithValue("visibility", VideoVisibilityType.PUBLIC)
            .hasFieldOrPropertyWithValue("status", VideoStatusType.CREATED);

    }

    @Test
    void updateVideo_doesNotExistTest(){
        UUID videoId = UUID.randomUUID();
        String title = "My video title";
        String description = "My video description";

        UpdateVideoRequest request = new UpdateVideoRequest(videoId, title, description, VideoVisibilityType.UNLISTED);

        when(this.videoRepository.findById(videoId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.videoMetadataService.updateVideo(request))
            .isInstanceOf(VideoDoesNotExistException.class);
    }

    @Test
    void updateVideo_successTest() {
        UUID videoId = UUID.randomUUID();
        String title = "My video title";
        String description = "My video description";

        Video video = new Video();
        video.setId(videoId);
        video.setTitle(title);
        video.setDescription(description);
        video.setStatus(VideoStatusType.CREATED);
        video.setVisibility(VideoVisibilityType.UNLISTED);
        video.setVersion(2L);

        UpdateVideoRequest request = new UpdateVideoRequest(videoId, title, description, VideoVisibilityType.UNLISTED);

        when(this.videoRepository.findById(videoId)).thenReturn(Optional.of(video));
        when(this.videoRepository.save(any(Video.class))).thenReturn(video);

        VideoMetadataResponse response = this.videoMetadataService.updateVideo(request);

         assertThat(response)
            .isNotNull()
            .hasFieldOrPropertyWithValue("id", videoId)
            .hasFieldOrPropertyWithValue("title", title)
            .hasFieldOrPropertyWithValue("description", description)
            .hasFieldOrPropertyWithValue("visibility", VideoVisibilityType.UNLISTED)
            .hasFieldOrPropertyWithValue("status", VideoStatusType.CREATED);
    }
    
}
