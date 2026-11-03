package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.FORBIDDEN;

    public ForbiddenException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public ForbiddenException(String message) {
        super(STATUS, message);
    }

}
