package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    public PositionDomain to() {
        PositionDomain positionDomain = new PositionDomain();
        positionDomain.setId(id);
        positionDomain.setName(name);
        positionDomain.setDescription(description);
        positionDomain.setIdentify(identify);
        return positionDomain;
    }

}
