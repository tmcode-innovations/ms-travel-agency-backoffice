package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.ProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("cantDayTour")
    private Integer cantDayTour;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("paxAdult")
    private Integer paxAdult;

    @JsonProperty("paxChild")
    private Integer paxChild;

    @JsonProperty("paxTc")
    private Integer paxTc;

    @JsonProperty("paxTcLocal")
    private Integer paxTcLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("dateInitTour")
    private LocalDate dateInitTour;

    @JsonProperty("dateInitTourStr")
    private String dateInitTourStr;

    @JsonProperty("dateEndTour")
    private LocalDate dateEndTour;

    @JsonProperty("dateEndTourStr")
    private String dateEndTourStr;

    @JsonProperty("stateQuotation")
    private String stateQuotation;

    @JsonProperty("stateQuotationDescription")
    private String stateQuotationDescription;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("amountFormat")
    private String amountFormat;

    @JsonProperty("profitPercent")
    private Double profitPercent;

    @JsonProperty("otherExpenses")
    private Double otherExpenses;

    @JsonProperty("customer")
    private CustomerResponse customer;

    @JsonProperty("program")
    private ProgramResponse program;

    @JsonProperty("employee")
    private EmployeeResponse employee;

    @JsonProperty("idiom")
    private IdiomResponse idiom;

    @JsonProperty("responsibleFile")
    private EmployeeResponse responsibleFile;

    @JsonProperty("country")
    private CountryResponse country;

    @JsonProperty("identifyFile")
    private String identifyFile;

    @JsonProperty("quotationType")
    private String quotationType;

    @JsonProperty("urlFileQuotation")
    private String urlFileQuotation;

    @JsonProperty("urlFileItinerary")
    private String urlFileItinerary;

    @JsonProperty("fileNameQuotation")
    private String fileNameQuotation;

    @JsonProperty("fileNameItinerary")
    private String fileNameItinerary;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("linkGDrive")
    private String linkGDrive;

    @JsonProperty("linkBitrix")
    private String linkBitrix;

    @JsonProperty("linkWetravel")
    private String linkWetravel;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStr")
    private String createdAtStr;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static QuotationResponse from(QuotationDomain domain) {
        QuotationResponse quotationResponse = new QuotationResponse();
        quotationResponse.setId(domain.getId());
        quotationResponse.setName(domain.getName());
        quotationResponse.setDescription(domain.getDescription());
        quotationResponse.setIdentify(domain.getIdentify());
        quotationResponse.setCantDayTour(domain.getCantDayTour());
        quotationResponse.setPaxTotal(domain.getPaxTotal());
        quotationResponse.setPaxAdult(domain.getPaxAdult());
        quotationResponse.setPaxChild(domain.getPaxChild());
        quotationResponse.setPaxTc(domain.getPaxTc());
        quotationResponse.setPaxTcLocal(domain.getPaxTcLocal());
        quotationResponse.setGuide(domain.getGuide());
        quotationResponse.setDateInitTour(domain.getDateInitTour());
        quotationResponse.setDateInitTourStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateInitTour()));
        quotationResponse.setDateEndTour(domain.getDateEndTour());
        quotationResponse.setDateEndTourStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateEndTour()));
        quotationResponse.setStateQuotation(domain.getStateQuotation());
        if (domain.getStateQuotation() != null && !domain.getStateQuotation().isEmpty()){
            StateQuotationEnum.StateQuotationModel stateQuotationModelData = EnumMappeableService
                    .map(StateQuotationEnum.class)
                    .stream().filter(
                            stateQuotationModel -> stateQuotationModel.name().equals(domain.getStateQuotation()))
                    .findFirst().get();
            quotationResponse.setStateQuotationDescription(stateQuotationModelData.description());
        }
        quotationResponse.setObservation(domain.getObservation());
        quotationResponse.setAmount(domain.getAmount());
        quotationResponse.setAmountFormat(domain.getAmountFormat());

        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            quotationResponse.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }
        if (domain.getProgram() != null &&
                domain.getProgram().getId() != null &&
                !domain.getProgram().getId().isEmpty()){
            quotationResponse.setProgram(ProgramResponse.from(domain.getProgram()));
        }
        if (domain.getEmployee() != null &&
                domain.getEmployee().getId() != null &&
                !domain.getEmployee().getId().isEmpty()){
            quotationResponse.setEmployee(EmployeeResponse.from(domain.getEmployee()));
        }
        if (domain.getIdiom() != null &&
                domain.getIdiom().getId() != null &&
                !domain.getIdiom().getId().isEmpty()){
            quotationResponse.setIdiom(IdiomResponse.from(domain.getIdiom()));
        }
        if (domain.getResponsibleFile() != null &&
                domain.getResponsibleFile().getId() != null &&
                !domain.getResponsibleFile().getId().isEmpty()){
            quotationResponse.setResponsibleFile(EmployeeResponse.from(domain.getResponsibleFile()));
        }
        if (domain.getCountry() != null &&
                domain.getCountry().getId() != null &&
                !domain.getCountry().getId().isEmpty()){
            quotationResponse.setCountry(CountryResponse.from(domain.getCountry()));
        }
        quotationResponse.setIdentifyFile(domain.getIdentifyFile());

        quotationResponse.setProfitPercent(domain.getProfitPercent());
        quotationResponse.setOtherExpenses(domain.getOtherExpenses());
        quotationResponse.setQuotationType(domain.getQuotationType());
        quotationResponse.setUrlFileQuotation(domain.getUrlFileQuotation());
        quotationResponse.setUrlFileItinerary(domain.getUrlFileItinerary());
        quotationResponse.setFileNameQuotation(domain.getFileNameQuotation());
        quotationResponse.setFileNameItinerary(domain.getFileNameItinerary());
        quotationResponse.setLinkGDrive(domain.getLinkGDrive());
        quotationResponse.setLinkBitrix(domain.getLinkBitrix());
        quotationResponse.setLinkWetravel(domain.getLinkWetravel());
        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            quotationResponse.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            quotationResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            quotationResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            quotationResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        quotationResponse.setCreatedAt(domain.getCreatedAt());
        quotationResponse.setCreatedAtStr(LocalDateTimeUtil.toLocalDateTimeFormatShort(domain.getCreatedAt()));
        quotationResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        quotationResponse.setUpdatedAt(domain.getUpdatedAt());
        quotationResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        quotationResponse.setDeletedAt(domain.getDeletedAt());
        quotationResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return quotationResponse;
    }
}
