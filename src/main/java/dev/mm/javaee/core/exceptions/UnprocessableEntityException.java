package dev.mm.javaee.core.exceptions;
import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends ApiException {

    public static HttpStatus STATUS = HttpStatus.UNPROCESSABLE_ENTITY;

    public UnprocessableEntityException() {
        super(STATUS, STATUS.getReasonPhrase());
    }

    public UnprocessableEntityException(String message) {
        super(STATUS, message);
    }

}
