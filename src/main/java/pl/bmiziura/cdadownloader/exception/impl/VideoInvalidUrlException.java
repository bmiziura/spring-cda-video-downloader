package pl.bmiziura.cdadownloader.exception.impl;

import pl.bmiziura.cdadownloader.exception.ErrorCode;

public class VideoInvalidUrlException extends AppException {
    public static final int ERROR_CODE = ErrorCode.VIDEO_INVALID_URL_ERROR;

    public VideoInvalidUrlException(String url) {
        super(String.format("Url is invalid! (url: %s)", url), ERROR_CODE);
    }
}
