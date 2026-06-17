package dev.mednikov.streaming.videos.models;

public enum VideoStatusType {
    CREATED,
    UPLOAD_STARTED, UPLOAD_FINISHED, UPLOAD_FAILED,
    ENCODING_STARTED, ENCODING_FINISHED, ENCODING_FAILED,
    ACTIVE,
    DELETED
}
