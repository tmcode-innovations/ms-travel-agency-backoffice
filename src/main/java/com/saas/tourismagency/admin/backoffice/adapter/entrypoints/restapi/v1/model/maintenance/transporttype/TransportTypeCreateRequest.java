package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TransportTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    public TransportTypeDomain to() {
        TransportTypeDomain transportTypeDomain = new TransportTypeDomain();
        transportTypeDomain.setName(name);
        transportTypeDomain.setDescription(description);
        transportTypeDomain.setIdentify(identify);
        return transportTypeDomain;
    }
}
