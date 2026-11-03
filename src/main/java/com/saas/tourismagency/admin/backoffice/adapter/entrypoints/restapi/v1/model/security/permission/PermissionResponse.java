package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app.AppResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.objectsecurity.ObjectResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {

    @JsonProperty("id")
    private String id;

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
    private ObjectResponse entity;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static PermissionResponse from(PermissionDomain domain) {
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setId(domain.getId());
        permissionResponse.setName(domain.getName());
        permissionResponse.setDescription(domain.getDescription());
        permissionResponse.setIdentify(domain.getIdentify());
        permissionResponse.setPermCreate(domain.getPermCreate());
        permissionResponse.setPermRead(domain.getPermRead());
        permissionResponse.setPermUpdate(domain.getPermUpdate());
        permissionResponse.setPermDelete(domain.getPermDelete());
        if (domain.getEntity() != null &&
                domain.getEntity().getId() != null &&
                !domain.getEntity().getId().isEmpty()){
            permissionResponse.setEntity(ObjectResponse.from(domain.getEntity()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            permissionResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            permissionResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            permissionResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        permissionResponse.setCreatedAt(domain.getCreatedAt());
        permissionResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        permissionResponse.setUpdatedAt(domain.getUpdatedAt());
        permissionResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        permissionResponse.setDeletedAt(domain.getDeletedAt());
        permissionResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return permissionResponse;
    }
}
