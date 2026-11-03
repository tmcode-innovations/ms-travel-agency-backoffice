package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPqtTouristCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public GroupPqtTouristDomain to() {
        GroupPqtTouristDomain domain = new GroupPqtTouristDomain();
        domain.setName(name);
        domain.setDescription(description);
        return domain;
    }
}
