package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchResponse {

    @JsonProperty("entities")
    private List<EmployeeResponse> employees;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
