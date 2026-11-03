package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("permissions")
    private Set<String> permissions;

    public ProfileDomain to() {
        ProfileDomain profileDomain = new ProfileDomain();
        profileDomain.setId(id);
        profileDomain.setName(name);
        profileDomain.setDescription(description);
        profileDomain.setIdentify(identify);
        return profileDomain;
    }

    public ProfileDomain to(Set<PermissionDomain> permissions) {
        ProfileDomain profileDomain = new ProfileDomain();
        profileDomain.setId(id);
        profileDomain.setName(name);
        profileDomain.setDescription(description);
        profileDomain.setIdentify(identify);
        if (permissions != null && !permissions.isEmpty())
            profileDomain.setPermissions(permissions);
        return profileDomain;
    }
}
