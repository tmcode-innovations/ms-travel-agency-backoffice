package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRegister.class)
public @interface Password {

    String message() default "Password must containt an Uppercase Letter, a Lowercase Letter, a Number and a Special Character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public String detailMessage() default "";

}
