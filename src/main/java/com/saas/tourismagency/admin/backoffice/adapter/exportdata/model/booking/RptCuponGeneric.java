package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptCuponGeneric implements Comparable<RptCuponGeneric>{

    @EqualsAndHashCode.Include
    private String id;

    private String identify;
    private String noCupon;
    private String date;
    private String typeGeneric;
    private String registerGroup;
    private String paxNumberPerson;
    private String supplierGeneric;
    private String supplierGenericAddress;
    private String supplierGenericDepartmentRegion;
    private String registerBookingResponsible;
    private String observation;
    private LocalDate dateInitService;
    private LocalDateTime dateTimeInitService;
    private Integer gastosExtrasNoIncluidos;
    private List<RptCuponGenericDetail> list;
    private String strRegisterBookingBase;

    @Override
    public int compareTo(RptCuponGeneric other) {
        return dateTimeInitService.compareTo(other.dateTimeInitService);
    }
}
