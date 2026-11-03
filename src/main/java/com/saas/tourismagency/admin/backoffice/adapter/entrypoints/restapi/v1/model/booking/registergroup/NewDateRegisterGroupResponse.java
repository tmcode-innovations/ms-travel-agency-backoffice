package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDateRegisterGroupResponse {

    @JsonProperty("newDateInit")
    private LocalDate newDateInit;

    @JsonProperty("newDateEnd")
    private LocalDate newDateEnd;

    @JsonProperty("newDateInitStr")
    private String newDateInitStr;

    @JsonProperty("newDateEndStr")
    private String newDateEndStr;

    public static NewDateRegisterGroupResponse from(LocalDate newDateInit, LocalDate newDateEnd) {
        NewDateRegisterGroupResponse response = new NewDateRegisterGroupResponse();
        response.setNewDateInit(newDateInit);
        response.setNewDateInitStr(LocalDateTimeUtil.toLocalDateFormatShortOther(newDateInit));
        response.setNewDateEnd(newDateEnd);
        response.setNewDateEndStr(LocalDateTimeUtil.toLocalDateFormatShortOther(newDateEnd));
        return response;
    }

}
