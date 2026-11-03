package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaDetailResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("priceUnit")
    private Double priceUnit;

    @JsonProperty("importUsd")
    private Double importUsd;

    @JsonProperty("importSoles")
    private Double importSoles;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("service")
    private String service;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("strNoItemFormat")
    private String strNoItemFormat;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    public static ProformaDetailResponse from(ProformaDetailDomain domain) {
        ProformaDetailResponse response = new ProformaDetailResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setPriceUnit(domain.getPriceUnit());
        response.setImportUsd(domain.getImportUsd());
        response.setImportSoles(domain.getImportSoles());
        response.setCant(domain.getCant());
        response.setService(domain.getService());
        response.setNoItem(domain.getNoItem());
        response.setStrNoItemFormat(domain.getStrNoItemFormat());
        response.setDateTime(domain.getDateTime());
        return response;
    }
}
