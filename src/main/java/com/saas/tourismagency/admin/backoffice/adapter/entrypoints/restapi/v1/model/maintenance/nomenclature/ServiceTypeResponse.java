package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ServiceTypeResponse from(ServiceTypeEnum.ServiceTypeModel domain) {
        ServiceTypeResponse serviceTypeResponse = new ServiceTypeResponse();
        serviceTypeResponse.setId(domain.id());
        serviceTypeResponse.setName(domain.name());
        serviceTypeResponse.setDescription(domain.description());
        return serviceTypeResponse;
    }
}
