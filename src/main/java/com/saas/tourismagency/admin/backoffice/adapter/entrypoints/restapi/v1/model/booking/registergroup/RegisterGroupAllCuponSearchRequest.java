package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SortRequest;
import dev.mm.javaee.core.domain.ISearchSortDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterGroupAllCuponSearchRequest implements ISearchSortDomain {

    @JsonProperty("sort")
    Map<String, String> sort;

    @JsonProperty("sortOtherFormat")
    SortRequest[] sortOtherFormat;
}
