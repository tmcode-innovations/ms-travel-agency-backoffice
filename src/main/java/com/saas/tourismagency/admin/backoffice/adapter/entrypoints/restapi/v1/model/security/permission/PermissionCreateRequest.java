package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("permCreate")
    private Boolean permCreate;

    @JsonProperty("permRead")
    private Boolean permRead;

    @JsonProperty("permUpdate")
    private Boolean permUpdate;

    @JsonProperty("permDelete")
    private Boolean permDelete;

    @JsonProperty("entity")
    private String entity;

    public PermissionDomain to() {
        PermissionDomain permissionDomain = new PermissionDomain();
        permissionDomain.setName(name);
        permissionDomain.setDescription(description);
        permissionDomain.setIdentify(identify);
        permissionDomain.setPermCreate(permCreate);
        permissionDomain.setPermRead(permRead);
        permissionDomain.setPermUpdate(permUpdate);
        permissionDomain.setPermDelete(permDelete);
        return permissionDomain;
    }

    public PermissionDomain to(ObjectDomain entity) {
        PermissionDomain permissionDomain = new PermissionDomain();
        permissionDomain.setName(name);
        permissionDomain.setDescription(description);
        permissionDomain.setIdentify(identify);
        permissionDomain.setPermCreate(permCreate);
        permissionDomain.setPermRead(permRead);
        permissionDomain.setPermUpdate(permUpdate);
        permissionDomain.setPermDelete(permDelete);
        if (entity != null)
            permissionDomain.setEntity(entity);
        return permissionDomain;
    }
}
