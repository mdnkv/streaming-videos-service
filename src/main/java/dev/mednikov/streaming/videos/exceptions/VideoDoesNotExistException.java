package dev.mednikov.streaming.videos.exceptions;

import java.util.UUID;

public final class VideoDoesNotExistException extends RuntimeException {

    public VideoDoesNotExistException(UUID id){
        super("Video with id " + id.toString() + " does not exist");
    }
    
}
