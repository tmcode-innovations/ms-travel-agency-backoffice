package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDateQuotationResponse {

    @JsonProperty("newDateInit")
    private LocalDate newDateInit;

    @JsonProperty("newDateEnd")
    private LocalDate newDateEnd;

    @JsonProperty("newDateInitStr")
    private String newDateInitStr;

    @JsonProperty("newDateEndStr")
    private String newDateEndStr;

    public static NewDateQuotationResponse from(LocalDate newDateInit, LocalDate newDateEnd) {
        NewDateQuotationResponse response = new NewDateQuotationResponse();
        response.setNewDateInit(newDateInit);
        response.setNewDateInitStr(LocalDateTimeUtil.toLocalDateFormatShortOther(newDateInit));
        response.setNewDateEnd(newDateEnd);
        response.setNewDateEndStr(LocalDateTimeUtil.toLocalDateFormatShortOther(newDateEnd));
        return response;
    }

}
