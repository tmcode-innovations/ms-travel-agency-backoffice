package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptCuponGenericList {

    private List<RptCuponGeneric> operators;
    private List<RptCuponGeneric> restaurants;
    private List<RptCuponGeneric> lodgings;
    private List<RptCuponGeneric> lodges;
    private List<RptCuponGeneric> lodgesLodging;

}
