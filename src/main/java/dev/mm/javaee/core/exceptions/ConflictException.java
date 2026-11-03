package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;


public class ConflictException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.CONFLICT;

    public ConflictException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public ConflictException(String message) {
        super(STATUS, message);
    }

}
