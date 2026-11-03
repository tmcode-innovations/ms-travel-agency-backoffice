package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingBaseSearchRequest implements ISearchPageDomain {

    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    @JsonProperty("sort")
    Map<String, String> sort;

}
