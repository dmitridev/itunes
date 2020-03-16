package org.astelit.itunes.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@UtilityClass
public class ResponseUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static void send(HttpServletRequest request, HttpServletResponse response, int status, String message) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(status);
        ExceptionResponse r = new ExceptionResponse(status, message, request.getRequestURI());
        mapper.writeValue(response.getOutputStream(), r);
        response.flushBuffer();
    }
}
