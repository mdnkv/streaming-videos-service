package dev.mednikov.streaming.videos.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    
    @Id @GeneratedValue @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID id;

    @Column(name = "video_visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoVisibilityType visibility;

    @Column(name = "video_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoStatusType status;

    @Column(name = "video_title", nullable = false)
    private String title;

    @Column(name = "video_description", nullable = false)
    private String description;

    @Column(name = "version_tag", nullable = false)
    private long version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public VideoVisibilityType getVisibility() {
        return visibility;
    }

    public void setVisibility(VideoVisibilityType visibility) {
        this.visibility = visibility;
    }

    public VideoStatusType getStatus() {
        return status;
    }

    public void setStatus(VideoStatusType status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    

}
