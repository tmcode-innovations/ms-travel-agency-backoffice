package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourSearchFilterRequest implements ISearchFilterPageDomain {

    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    @JsonProperty("filter")
    Map<String, String> filter;

    @JsonProperty("sort")
    Map<String, String> sort;

}
