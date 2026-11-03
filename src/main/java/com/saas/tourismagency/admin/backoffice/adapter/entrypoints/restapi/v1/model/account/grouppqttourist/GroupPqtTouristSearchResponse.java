package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPqtTouristSearchResponse {

    @JsonProperty("entities")
    private List<GroupPqtTouristResponse> items;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
