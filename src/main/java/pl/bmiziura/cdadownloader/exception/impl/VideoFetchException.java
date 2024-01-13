package pl.bmiziura.cdadownloader.exception.impl;

import pl.bmiziura.cdadownloader.exception.ErrorCode;

public class VideoFetchException extends AppException {
    public static final int ERROR_CODE = ErrorCode.VIDEO_FETCH_ERROR;

    public VideoFetchException(String message) {
        super(message, ERROR_CODE);
    }
}
