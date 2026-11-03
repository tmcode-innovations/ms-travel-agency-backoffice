package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.EntryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature.AffectIgvResponse;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.AffectIgvEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFinanceDetailResponse {

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

    @JsonProperty("importIgvUsd")
    private Double importIgvUsd;

    @JsonProperty("importIgvSoles")
    private Double importIgvSoles;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("serviceFinance")
    private String serviceFinance;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("strNoItemFormat")
    private String strNoItemFormat;

    @JsonProperty("documentFinance")
    private DocumentFinanceResponse documentFinance;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    // Rubro
    @JsonProperty("entry")
    private EntryResponse entry;

    @JsonProperty("affectIgv")
    private String affectIgv;

    @JsonProperty("affectIgvDescription")
    private String affectIgvDescription;

    public static DocumentFinanceDetailResponse from(DocumentFinanceDetailDomain domain) {
        DocumentFinanceDetailResponse response = new DocumentFinanceDetailResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setPriceUnit(domain.getPriceUnit());
        response.setImportUsd(domain.getImportUsd());
        response.setImportSoles(domain.getImportSoles());
        response.setImportIgvUsd(domain.getImportIgvUsd());
        response.setImportIgvSoles(domain.getImportIgvSoles());
        response.setImportTotalUsd(domain.getImportTotalUsd());
        response.setImportTotalSoles(domain.getImportTotalSoles());
        response.setCant(domain.getCant());
        response.setServiceFinance(domain.getServiceFinance());
        response.setNoItem(domain.getNoItem());
        response.setStrNoItemFormat(domain.getStrNoItemFormat());

        if (domain.getDocumentFinance() != null &&
                domain.getDocumentFinance().getId() != null &&
                !domain.getDocumentFinance().getId().isEmpty()){
            response.setDocumentFinance(DocumentFinanceResponse.from(domain.getDocumentFinance()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        // Rubro
        if (domain.getEntry() != null &&
                domain.getEntry().getId() != null &&
                !domain.getEntry().getId().isEmpty()){
            response.setEntry(EntryResponse.from(domain.getEntry()));
        }

        response.setAffectIgv(domain.getAffectIgv());
        if (domain.getAffectIgv() != null && !domain.getAffectIgv().isEmpty() && !domain.getAffectIgv().isBlank()){
            AffectIgvEnum.AffectIgvModel affectIgvModelData = EnumMappeableService
                    .map(AffectIgvEnum.class)
                    .stream().filter(
                            affectIgvModel -> affectIgvModel.name().equals(domain.getAffectIgv()))
                    .findFirst().get();
            response.setAffectIgvDescription(affectIgvModelData.description());
        }
        return response;
    }
}
