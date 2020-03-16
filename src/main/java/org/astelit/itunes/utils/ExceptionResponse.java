package org.astelit.itunes.utils;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final long timestamp = DateUtils.nowUnix();
    private final int status;
    private final String message;
    private final String path;

    public ExceptionResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
