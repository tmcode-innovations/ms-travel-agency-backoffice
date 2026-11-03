package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app.AppResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.ProfileResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

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

    @JsonProperty("tenant")
    private String tenant;

    @JsonProperty("profiles")
    private Set<ProfileResponse> profiles;

    @JsonProperty("apps")
    private Set<AppResponse> apps;

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

    public static UserResponse from(UserDomain domain) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(domain.getId());
        userResponse.setUserName(domain.getUserName());
        userResponse.setEmail(domain.getEmail());
        userResponse.setFullName(domain.getFullName());
        userResponse.setDescription(domain.getDescription());
        userResponse.setTenant(domain.getTenant());

        if (domain.getApps() != null && !domain.getApps().isEmpty()){
            userResponse.setApps(domain.getApps().stream().map(appDomain -> AppResponse.from(appDomain)).collect(Collectors.toSet()));
        }else{
            userResponse.setApps(new HashSet<>());
        }

        if (domain.getProfiles() != null && !domain.getProfiles().isEmpty()){
            userResponse.setProfiles(domain.getProfiles().stream().map(profileDomain -> ProfileResponse.from(profileDomain)).collect(Collectors.toSet()));
        }else{
            userResponse.setProfiles(new HashSet<>());
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            userResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            userResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            userResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        userResponse.setCreatedAt(domain.getCreatedAt());
        userResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        userResponse.setUpdatedAt(domain.getUpdatedAt());
        userResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        userResponse.setDeletedAt(domain.getDeletedAt());
        userResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return userResponse;
    }
}
