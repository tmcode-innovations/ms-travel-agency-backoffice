package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public BadRequestException(String message) {
        super(STATUS, message);
    }

}
