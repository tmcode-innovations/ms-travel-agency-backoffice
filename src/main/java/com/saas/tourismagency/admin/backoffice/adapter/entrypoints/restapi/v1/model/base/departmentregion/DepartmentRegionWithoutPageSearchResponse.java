package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRegionWithoutPageSearchResponse {

    @JsonProperty("entities")
    private List<DepartmentRegionResponse> entities;

}
