package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapNoCuponCheckEntityListResponse {

    private List<WrapNoCuponCheckEntityResponse> operators;
    private List<WrapNoCuponCheckEntityResponse> restaurants;
    private List<WrapNoCuponCheckEntityResponse> lodgings;
    private List<WrapNoCuponCheckEntityResponse> lodges;
    private List<WrapNoCuponCheckEntityResponse> lodgesLodging;

}
