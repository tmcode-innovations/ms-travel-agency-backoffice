package dev.mm.javaee.core.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ValidationException extends RuntimeException {

    private final HttpStatus statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    private List<ValidationErrorMessage> messages;

    public ValidationException(List<ValidationErrorMessage> messages) {
        this.messages = messages;
    }

    public ValidationException(ValidationErrorMessage message) {
        if(message != null){
            this.messages = List.of(message);
        }else{
            this.messages = null;
        }

    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public List<ValidationErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ValidationErrorMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ValidationException{" + "statusCode=" + statusCode + ", messages=" + Arrays.toString(messages.toArray()) + '}';
    }

}
