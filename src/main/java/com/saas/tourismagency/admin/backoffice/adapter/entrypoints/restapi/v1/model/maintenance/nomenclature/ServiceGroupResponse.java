package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceGroupResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ServiceGroupResponse from(ServiceGroupEnum.ServiceGroupModel domain) {
        ServiceGroupResponse serviceGroupResponse = new ServiceGroupResponse();
        serviceGroupResponse.setId(domain.id());
        serviceGroupResponse.setName(domain.name());
        serviceGroupResponse.setDescription(domain.description());
        return serviceGroupResponse;
    }
}
