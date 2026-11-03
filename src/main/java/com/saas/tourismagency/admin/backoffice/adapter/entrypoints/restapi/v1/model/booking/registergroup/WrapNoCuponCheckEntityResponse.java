package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapNoCuponCheckEntityResponse implements Comparable<WrapNoCuponCheckEntityResponse> {

    @JsonProperty("id")
    private String id;

    @JsonProperty("strRegisterBookingBase")
    private String strRegisterBookingBase;

    @JsonProperty("noCupon")
    private String noCupon;

    @JsonProperty("noCuponPreImpress")
    private String noCuponPreImpress;

    @JsonProperty("checkRegisterBookingBase")
    private Boolean checkRegisterBookingBase;

    @JsonProperty("dateInitService")
    private LocalDate dateInitService;

    @JsonProperty("dateTimeInitService")
    private LocalDateTime dateTimeInitService;

    @JsonProperty("dateInitServiceStr")
    private String dateInitServiceStr;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("lodgeLodging")
    private Boolean lodgeLodging;

    public static WrapNoCuponCheckEntityResponse from(RegisterBookingBaseDomain registerBookingBasedomain, String noCupon, String noCuponPreImpress, Boolean checkRegisterBookingBase, LocalDate dateInitService, LocalDateTime dateTimeInitService, String supplierType, Boolean lodgeLodging) {
        WrapNoCuponCheckEntityResponse response = new WrapNoCuponCheckEntityResponse();
        if (registerBookingBasedomain != null){
            response.setId(registerBookingBasedomain.getId());
        }
        response.setNoCupon(noCupon);
        response.setNoCuponPreImpress(noCuponPreImpress);
        response.setCheckRegisterBookingBase(checkRegisterBookingBase);
        response.setStrRegisterBookingBase(registerBookingBasedomain.getIdentify() + " - " + registerBookingBasedomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBasedomain.getSupplier().getName());
        response.setDateInitService(dateInitService);
        response.setDateInitServiceStr(LocalDateTimeUtil.toLocalDateFormatShort(dateInitService));
        response.setDateTimeInitService(dateTimeInitService);
        response.setSupplierType(supplierType);
        response.setLodgeLodging(lodgeLodging);
        return response;
    }

    @Override
    public int compareTo(WrapNoCuponCheckEntityResponse other) {
        int value = dateTimeInitService.compareTo(other.dateTimeInitService);
        return value;
    }
}
