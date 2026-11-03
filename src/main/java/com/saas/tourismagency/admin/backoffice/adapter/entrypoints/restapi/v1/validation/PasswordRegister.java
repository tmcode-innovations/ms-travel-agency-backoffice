package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordRegister implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password a) {
        ConstraintValidator.super.initialize(a);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext cvc) {
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\"\\[\\]\\(\\)`~!@#$%^&*-+=,.<>?;:'{}]).{8,64}$", password);
    }

}
