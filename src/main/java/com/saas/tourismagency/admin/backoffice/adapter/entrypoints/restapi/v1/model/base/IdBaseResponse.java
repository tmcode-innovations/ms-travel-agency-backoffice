package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdBaseResponse {

    @JsonProperty("id")
    private String id;

    public static IdBaseResponse from(IdBaseDomain domain) {
        IdBaseResponse idBaseResponse = new IdBaseResponse();
        idBaseResponse.setId(domain.getId());
        return idBaseResponse;
    }
}
