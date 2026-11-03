package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
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
public class ProgramModelViewResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("supplierType")
    SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    SupplierResponse supplier;

    @JsonProperty("service")
    ServiceResponse service;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("programDateTime")
    private LocalDateTime programDateTime;

    @JsonProperty("programDateTimeStr")
    private String programDateTimeStr;

    @JsonProperty("parentProgramDate")
    private Boolean parentProgramDate;

    @JsonProperty("subRows")
    private List<ProgramModelViewResponse> subRows;

    public static ProgramModelViewResponse from(
            String id,
            String name,
            SupplierTypeResponse supplierType,
            SupplierResponse supplier,
            ServiceResponse service,
            String observation,
            LocalDateTime programDateTime,
            String programDateTimeStr,
            Boolean parentProgramDate,
            List<ProgramModelViewResponse> subRows) {
        ProgramModelViewResponse programModelViewResponse = new ProgramModelViewResponse();
        programModelViewResponse.setId(id);
        programModelViewResponse.setName(name);
        programModelViewResponse.setSupplierType(supplierType);
        programModelViewResponse.setSupplier(supplier);
        programModelViewResponse.setService(service);
        programModelViewResponse.setObservation(observation);
        programModelViewResponse.setProgramDateTime(programDateTime);
        programModelViewResponse.setProgramDateTimeStr(programDateTimeStr);
        programModelViewResponse.setParentProgramDate(parentProgramDate);
        programModelViewResponse.setSubRows(subRows);
        return programModelViewResponse;
    }
}
