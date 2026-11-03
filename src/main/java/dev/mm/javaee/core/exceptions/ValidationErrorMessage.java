package dev.mm.javaee.core.exceptions;

import java.io.Serializable;

public class ValidationErrorMessage implements Serializable {

    private String source;
    private String invalid_value;
    private String message;

    public ValidationErrorMessage(String source, String invalid_value, String message) {
        this.source = source;
        this.invalid_value = invalid_value;
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getInvalid_value() {
        return invalid_value;
    }

    public void setInvalid_value(String invalid_value) {
        this.invalid_value = invalid_value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
