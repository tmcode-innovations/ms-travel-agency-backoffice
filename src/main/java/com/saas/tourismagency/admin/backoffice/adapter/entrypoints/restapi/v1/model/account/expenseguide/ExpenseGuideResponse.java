package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierLodgingResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGuideResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateExpenseGuide")
    private LocalDate dateExpenseGuide;

    @JsonProperty("dateExpenseGuideStr")
    private String dateExpenseGuideStr;

    @JsonProperty("dateExpenseGuideStrDescription")
    private String dateExpenseGuideStrDescription;

    @JsonProperty("nroPax")
    private Integer nroPax;

    @JsonProperty("lodging")
    private String lodging;

    @JsonProperty("importSubTotalUsd")
    private Double importSubTotalUsd;

    @JsonProperty("importSubTotalSoles")
    private Double importSubTotalSoles;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("subTotalPrice")
    private Double subTotalPrice;

    @JsonProperty("expenseGuideState")
    private String expenseGuideState;

    @JsonProperty("guideTransfer")
    private GuideResponse guideTransfer;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("supplierLodging")
    private SupplierLodgingResponse supplierLodging;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("dateProcess")
    private DateProcessResponse dateProcess;

    @JsonProperty("importSubTotalUsdFormat")
    private String importSubTotalUsdFormat;

    @JsonProperty("importSubTotalSolesFormat")
    private String importSubTotalSolesFormat;

    @JsonProperty("importTotalUsdFormat")
    private String importTotalUsdFormat;

    @JsonProperty("importTotalSolesFormat")
    private String importTotalSolesFormat;

    @JsonProperty("subTotalPriceFormat")
    private String subTotalPriceFormat;

    public static ExpenseGuideResponse from(ExpenseGuideDomain domain) {
        ExpenseGuideResponse response = new ExpenseGuideResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setDateExpenseGuide(domain.getDateExpenseGuide());

        response.setDateExpenseGuideStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateExpenseGuide()));
        response.setDateExpenseGuideStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateExpenseGuide()));

        response.setNroPax(domain.getNroPax());
        response.setLodging(domain.getLodging());
        response.setImportSubTotalUsd(domain.getImportSubTotalUsd());
        response.setImportSubTotalSoles(domain.getImportSubTotalSoles());
        response.setImportTotalUsd(domain.getImportTotalUsd());
        response.setImportTotalSoles(domain.getImportTotalSoles());
        response.setSubTotalPrice(domain.getSubTotalPrice());
        response.setExpenseGuideState(domain.getExpenseGuideState());

        if (domain.getGuideTransfer() != null &&
                domain.getGuideTransfer().getId() != null &&
                !domain.getGuideTransfer().getId().isEmpty()){
            response.setGuideTransfer(GuideResponse.from(domain.getGuideTransfer()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getSupplierLodging() != null &&
                domain.getSupplierLodging().getId() != null &&
                !domain.getSupplierLodging().getId().isEmpty()){
            response.setSupplierLodging(SupplierLodgingResponse.from(domain.getSupplierLodging()));
        }

        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        if (domain.getDateProcess() != null &&
                domain.getDateProcess().getId() != null &&
                !domain.getDateProcess().getId().isEmpty()){
            response.setDateProcess(DateProcessResponse.from(domain.getDateProcess()));
        }

        response.setImportSubTotalUsdFormat(domain.getImportSubTotalUsdFormat());
        response.setImportSubTotalSolesFormat(domain.getImportSubTotalSolesFormat());
        response.setImportTotalUsdFormat(domain.getImportTotalUsdFormat());
        response.setImportTotalSolesFormat(domain.getImportTotalSolesFormat());
        response.setSubTotalPriceFormat(domain.getSubTotalPriceFormat());
        return response;
    }
}
