package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;


public class UnauthorizedException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public UnauthorizedException(String message) {
        super(STATUS, message);
    }

}
