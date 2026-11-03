package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.userpassword.UserPasswordRequest;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("userPassword")
    private UserPasswordRequest userPassword;

    @JsonProperty("profiles")
    private Set<String> profiles;

    @JsonProperty("apps")
    private Set<String> apps;

    public UserDomain to() {
        UserDomain userDomain = new UserDomain();
        userDomain.setId(id);
        userDomain.setUserName(userName);
        userDomain.setEmail(email);
        userDomain.setFullName(fullName);
        userDomain.setDescription(description);
        return userDomain;
    }

}
