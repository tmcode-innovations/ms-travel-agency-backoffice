package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapSupplierRegisterBookingAllGenerate {

    @JsonProperty("operators")
    List<WrapSupplierRegisterBookingGenerate> operators;

    @JsonProperty("lodgings")
    List<WrapSupplierRegisterBookingGenerate> lodgings;

    @JsonProperty("lodges")
    List<WrapSupplierRegisterBookingGenerate> lodges;

    @JsonProperty("restaurants")
    List<WrapSupplierRegisterBookingGenerate> restaurants;

    @JsonProperty("glampings")
    List<WrapSupplierRegisterBookingGenerate> glampings;

    @JsonProperty("institutions")
    List<WrapSupplierRegisterBookingGenerate> institutions;

}
