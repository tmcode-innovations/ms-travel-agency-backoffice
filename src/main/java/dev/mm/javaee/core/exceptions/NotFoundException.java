package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public NotFoundException(String message) {
        super(STATUS, message);
    }

}
