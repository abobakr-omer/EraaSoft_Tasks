package com.spring.demo.securebankingsystem.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/** Same JSON shape for every error (400, 401, 403, 404 ...). */
public record ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {

    public static ErrorResponse of(HttpStatus status, String message, String path) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path);
    }

    /** Used by the security handlers, which run before Spring MVC, so they write the JSON by hand. */
    public String toJson() {
        return "{\"timestamp\":\"" + timestamp + "\","
                + "\"status\":" + status + ","
                + "\"error\":\"" + escape(error) + "\","
                + "\"message\":\"" + escape(message) + "\","
                + "\"path\":\"" + escape(path) + "\"}";
    }

    private static String escape(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ");
    }
}
