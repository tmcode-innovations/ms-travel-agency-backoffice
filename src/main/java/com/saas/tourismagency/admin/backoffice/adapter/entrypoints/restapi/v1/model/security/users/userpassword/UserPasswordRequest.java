package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.userpassword;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.validation.Password;
import dev.mm.javaee.core.utils.validation.annotations.StringValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordRequest {

    @StringValidation(isValidateNotNull = true,isValidateNotEmpty = true,isValidateNotBlank = true
            ,min = 8, max = 64)
    @Password
    @JsonProperty("password")
    private String password;

}
