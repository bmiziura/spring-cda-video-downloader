package pl.bmiziura.cdadownloader.exception.impl;

import pl.bmiziura.cdadownloader.exception.ErrorCode;

public class VideoNotFoundException extends AppException {
    public static final int ERROR_CODE = ErrorCode.VIDEO_NOT_FOUND;

    public VideoNotFoundException(long id) {
        super(String.format("Video not found! (id: %d)", id), ERROR_CODE);
    }
}
