package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountBaseResponse {

    @JsonProperty("count")
    private Long count;

    public static CountBaseResponse from(Long count) {
        CountBaseResponse countBaseResponse = new CountBaseResponse();
        countBaseResponse.setCount(count);
        return countBaseResponse;
    }
}
