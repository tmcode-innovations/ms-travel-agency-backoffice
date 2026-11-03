package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityExistBaseResponse {

    @JsonProperty("flag")
    private Boolean flag;

    public static EntityExistBaseResponse from(Boolean flag) {
        EntityExistBaseResponse entityExistBaseResponse = new EntityExistBaseResponse();
        entityExistBaseResponse.setFlag(flag);
        return entityExistBaseResponse;
    }
}
