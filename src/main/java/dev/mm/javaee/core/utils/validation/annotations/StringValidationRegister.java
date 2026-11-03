package dev.mm.javaee.core.utils.validation.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidationRegister implements ConstraintValidator<StringValidation,String> {

    private int min;
    private int max;
    private String regexp;
    private boolean isValidateNotEmpty;
    private boolean isValidateNotNull;
    private boolean isValidateNotBlank;
    private String message;

    @Override
    public void initialize(StringValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
        this.isValidateNotBlank = constraintAnnotation.isValidateNotBlank();
        this.isValidateNotNull = constraintAnnotation.isValidateNotNull();
        this.isValidateNotEmpty = constraintAnnotation.isValidateNotEmpty();
        this.regexp = constraintAnnotation.regexp();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (isValidateNotNull && value == null) {
            context.buildConstraintViolationWithTemplate(
                            "Field can't be null." )
                    .addConstraintViolation();
            return false;
        }

        if(isValidateNotEmpty && value != null && value.isEmpty()){
            context.buildConstraintViolationWithTemplate(
                            "Field can't be empty." )
                    .addConstraintViolation();
            return false;
        }

        if(isValidateNotBlank && value != null && value.isBlank()){
            context.buildConstraintViolationWithTemplate(
                            "Field can't be blank." )
                    .addConstraintViolation();
            return false;
        }

        if(value != null && !value.isBlank() && !value.isEmpty()){
            if(min > 0 && value.length() < min){
                context.buildConstraintViolationWithTemplate(
                                "Field must be greater than or equal to %s.".formatted(min) )
                        .addConstraintViolation();
                return false;
            }

            if(value.length() > max){
                context.buildConstraintViolationWithTemplate(
                                "Field must be less than or equal to %s.".formatted(max) )
                        .addConstraintViolation();
                return false;
            }

            if(regexp != null && !regexp.isEmpty() && !regexp.isBlank()){

                if(value != null && !value.isEmpty()){
                    Pattern pattern = Pattern.compile(regexp);
                    Matcher matcher = pattern.matcher(value.trim());
                    if (!matcher.matches()) {
                        context.buildConstraintViolationWithTemplate(
                                        message )
                                .addConstraintViolation();
                        return false;
                    }
                }


            }
        }
        return true;
    }


}
