package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.ExpenseGuideResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGuideDetailResponse {

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

    @JsonProperty("dateTimeService")
    private LocalDateTime dateTimeService;

    @JsonProperty("expenseGuide")
    private ExpenseGuideResponse expenseGuide;

    @JsonProperty("service")
    private ServiceResponse service;

    @JsonProperty("orderServiceItem")
    private OrderServiceItemResponse orderServiceItem;

    @JsonProperty("guideDrive")
    private GuideResponse guideDrive;

    @JsonProperty("priceUnitFormat")
    private String priceUnitFormat;

    @JsonProperty("importUsdFormat")
    private String importUsdFormat;

    @JsonProperty("importSolesFormat")
    private String importSolesFormat;

    public static ExpenseGuideDetailResponse from(ExpenseGuideDetailDomain domain) {
        ExpenseGuideDetailResponse response = new ExpenseGuideDetailResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setPriceUnit(domain.getPriceUnit());
        response.setImportUsd(domain.getImportUsd());
        response.setImportSoles(domain.getImportSoles());
        response.setCant(domain.getCant());
        response.setDateTimeService(domain.getDateTimeService());

        if (domain.getExpenseGuide() != null &&
                domain.getExpenseGuide().getId() != null &&
                !domain.getExpenseGuide().getId().isEmpty()){
            response.setExpenseGuide(ExpenseGuideResponse.from(domain.getExpenseGuide()));
        }

        if (domain.getService() != null &&
                domain.getService().getId() != null &&
                !domain.getService().getId().isEmpty()){
            response.setService(ServiceResponse.from(domain.getService()));
        }

        if (domain.getOrderServiceItem() != null &&
                domain.getOrderServiceItem().getId() != null &&
                !domain.getOrderServiceItem().getId().isEmpty()){
            response.setOrderServiceItem(OrderServiceItemResponse.from(domain.getOrderServiceItem()));
        }

        if (domain.getGuideDrive() != null &&
                domain.getGuideDrive().getId() != null &&
                !domain.getGuideDrive().getId().isEmpty()){
            response.setGuideDrive(GuideResponse.from(domain.getGuideDrive()));
        }

        response.setPriceUnitFormat(domain.getPriceUnitFormat());
        response.setImportUsdFormat(domain.getImportUsdFormat());
        response.setImportSolesFormat(domain.getImportSolesFormat());
        return response;
    }
}
