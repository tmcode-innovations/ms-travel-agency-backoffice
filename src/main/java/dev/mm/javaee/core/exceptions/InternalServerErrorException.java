package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;


public class InternalServerErrorException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerErrorException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public InternalServerErrorException(String message) {
        super(STATUS, message);
    }

}
