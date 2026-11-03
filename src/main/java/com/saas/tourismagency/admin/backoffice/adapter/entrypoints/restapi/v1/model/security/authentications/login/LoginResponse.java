package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @JsonProperty("jwt")
    private String jwt;

    @JsonProperty("expire_at")
    private LocalDateTime expireAt;

    @JsonProperty("settings")
    private SettingEmbeddedDomain settings;

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("apps")
    private List<String> apps;

    @JsonProperty("permissions")
    private List<String> permissions;

}
