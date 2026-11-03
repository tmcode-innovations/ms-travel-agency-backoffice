package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapSupplierRegisterBookingAllResponse {

    @JsonProperty("operators")
    List<WrapSupplierRegisterBookingResponse> operators;

    @JsonProperty("lodgings")
    List<WrapSupplierRegisterBookingResponse> lodgings;

    @JsonProperty("lodges")
    List<WrapSupplierRegisterBookingResponse> lodges;

    @JsonProperty("restaurants")
    List<WrapSupplierRegisterBookingResponse> restaurants;

    @JsonProperty("glampings")
    List<WrapSupplierRegisterBookingResponse> glampings;

    @JsonProperty("institutions")
    List<WrapSupplierRegisterBookingResponse> institutions;

}
