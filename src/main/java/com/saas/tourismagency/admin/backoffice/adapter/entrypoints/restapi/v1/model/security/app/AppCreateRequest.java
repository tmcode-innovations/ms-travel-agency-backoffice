package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("code")
    private String code;

    public AppDomain to() {
        AppDomain appDomain = new AppDomain();
        appDomain.setName(name);
        appDomain.setDescription(description);
        appDomain.setIdentify(identify);
        appDomain.setCode(code);
        return appDomain;
    }
}
