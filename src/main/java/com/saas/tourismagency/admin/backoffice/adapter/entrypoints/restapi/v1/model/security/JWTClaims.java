package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTClaims {

    private String userId;
    private String userUserName;
    private String userEmail;
    private String userFullName;
    private String tenant;
    private List<String> apps;
    private List<String> permissions;
}
