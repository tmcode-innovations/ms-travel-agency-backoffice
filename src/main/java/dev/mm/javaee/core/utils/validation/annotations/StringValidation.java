package dev.mm.javaee.core.utils.validation.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StringValidationRegister.class})
public @interface StringValidation {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String regexp() default "";


    boolean isValidateNotEmpty() default false;

    boolean isValidateNotNull() default false;

    boolean isValidateNotBlank() default false;

    String message() default "Does not meet specified conditions.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public String detailMessage() default "";
}
