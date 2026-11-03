package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.domain.UserBaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    public static UserBaseResponse from(UserBaseDomain domain) {
        UserBaseResponse userBaseResponse = new UserBaseResponse();
        userBaseResponse.setId(domain.getId());
        userBaseResponse.setUserName(domain.getUserName());
        userBaseResponse.setEmail(domain.getEmail());
        userBaseResponse.setFullName(domain.getFullName());
        return userBaseResponse;
    }
}
