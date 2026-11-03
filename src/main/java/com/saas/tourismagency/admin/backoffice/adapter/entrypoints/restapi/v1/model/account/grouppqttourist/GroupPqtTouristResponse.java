package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPqtTouristResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    public static GroupPqtTouristResponse from(GroupPqtTouristDomain domain) {
        GroupPqtTouristResponse response = new GroupPqtTouristResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        return response;
    }
}
