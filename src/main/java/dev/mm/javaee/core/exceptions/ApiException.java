package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final int rawStatusCode;
    private final String message;

    public ApiException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.rawStatusCode = status.value();
        this.message = status.getReasonPhrase();
    }

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.rawStatusCode = status.value();
        this.message = message;
    }

    public ApiException(int rawStatusCode, String message) {
        super(message);
        this.rawStatusCode = rawStatusCode;
        this.message = message;
    }

    public HttpStatus status() {
        return HttpStatus.resolve(rawStatusCode);
    }

    public int getRawStatusCode() {
        return rawStatusCode;
    }

    public String getReasonPhrase() {
        return message;
    }

}
