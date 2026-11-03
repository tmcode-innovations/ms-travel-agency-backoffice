package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.permission.PermissionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("permissions")
    private Set<PermissionResponse> permissions;

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

    public static ProfileResponse from(ProfileDomain domain) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(domain.getId());
        profileResponse.setName(domain.getName());
        profileResponse.setDescription(domain.getDescription());
        profileResponse.setIdentify(domain.getIdentify());
        if (domain.getPermissions() != null && !domain.getPermissions().isEmpty()){
            profileResponse.setPermissions(domain.getPermissions().stream().map(permissionDomain -> PermissionResponse.from(permissionDomain)).collect(Collectors.toSet()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            profileResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            profileResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            profileResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        profileResponse.setCreatedAt(domain.getCreatedAt());
        profileResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        profileResponse.setUpdatedAt(domain.getUpdatedAt());
        profileResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        profileResponse.setDeletedAt(domain.getDeletedAt());
        profileResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return profileResponse;
    }
}
