package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCreateRequestById {

    @JsonProperty("id")
    private String id;

}
