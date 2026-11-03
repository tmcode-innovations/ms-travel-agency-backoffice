package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFinanceResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("identifyPrefix")
    protected String identifyPrefix;

    @JsonProperty("identifyConsecutive")
    protected String identifyConsecutive;

    @JsonProperty("date")
    protected LocalDate date;

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("dateCancel")
    protected LocalDate dateCancel;

    @JsonProperty("dateAnnulled")
    protected LocalDate dateAnnulled;

    @JsonProperty("address")
    protected String address;

    @JsonProperty("referenceDocument")
    protected String referenceDocument;

    @JsonProperty("dateInit")
    protected LocalDate dateInit;

    @JsonProperty("dateEnd")
    protected LocalDate dateEnd;

    @JsonProperty("noPax")
    protected Integer noPax;

    @JsonProperty("subTotalPrice")
    protected Double subTotalPrice;

    @JsonProperty("importSubTotalUsd")
    protected Double importSubTotalUsd;

    @JsonProperty("importSubTotalUsdFormat")
    private String importSubTotalUsdFormat;

    @JsonProperty("importSubTotalSoles")
    protected Double importSubTotalSoles;

    @JsonProperty("importSubTotalSolesFormat")
    protected String importSubTotalSolesFormat;

    @JsonProperty("importIgvUsd")
    protected Double importIgvUsd;

    @JsonProperty("importIgvUsdFormat")
    protected String importIgvUsdFormat;

    @JsonProperty("importIgvSoles")
    protected Double importIgvSoles;

    @JsonProperty("importIgvSolesFormat")
    protected String importIgvSolesFormat;

    @JsonProperty("importTotalUsd")
    protected Double importTotalUsd;

    @JsonProperty("importTotalUsdFormat")
    protected String importTotalUsdFormat;

    @JsonProperty("importTotalSoles")
    protected Double importTotalSoles;

    @JsonProperty("importTotalSolesFormat")
    protected String importTotalSolesFormat;

    @JsonProperty("documentAccountType")
    protected String documentAccountType;

    @JsonProperty("documentAccountState")
    protected String documentAccountState;

    @JsonProperty("documentAccountStateDescription")
    protected String documentAccountStateDescription;

    @JsonProperty("dateProcess")
    protected DateProcessResponse dateProcess;

    @JsonProperty("country")
    protected CountryResponse country;

    @JsonProperty("currency")
    protected CurrencyResponse currency;

    @JsonProperty("changeType")
    protected ChangeTypeResponse changeType;

    @JsonProperty("customer")
    protected CustomerResponse customer;

    @JsonProperty("registerGroup")
    protected RegisterGroupResponse registerGroup;

    @JsonProperty("responsible")
    protected EmployeeResponse responsible;

    @JsonProperty("totalDocumentFinancyCurrency")
    protected Double totalDocumentFinancyCurrency;

    @JsonProperty("totalDocumentFinancyCurrencyFormat")
    protected String totalDocumentFinancyCurrencyFormat;

    public static DocumentFinanceResponse from(DocumentFinanceDomain domain) {
        DocumentFinanceResponse response = new DocumentFinanceResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setIdentifyPrefix(domain.getIdentifyPrefix());
        response.setIdentifyConsecutive(domain.getIdentifyConsecutive());

        response.setDate( domain.getDate() );
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));

        response.setDateCancel( domain.getDateCancel() );
        response.setDateAnnulled( domain.getDateAnnulled() );
        response.setAddress( domain.getAddress() );
        response.setReferenceDocument( domain.getReferenceDocument() );
        response.setDateInit( domain.getDateInit() );
        response.setDateEnd( domain.getDateEnd() );
        response.setNoPax( domain.getNoPax() );
        response.setSubTotalPrice( domain.getSubTotalPrice() );

        response.setImportSubTotalUsd( domain.getImportSubTotalUsd() );
        response.setImportSubTotalUsdFormat( domain.getImportSubTotalUsdFormat() );

        response.setImportSubTotalSoles( domain.getImportSubTotalSoles() );
        response.setImportSubTotalSolesFormat(domain.getImportSubTotalSolesFormat());

        response.setImportIgvUsd( domain.getImportIgvUsd() );
        response.setImportIgvUsdFormat(domain.getImportIgvUsdFormat());

        response.setImportIgvSoles( domain.getImportIgvSoles() );
        response.setImportIgvSolesFormat(domain.getImportIgvSolesFormat());

        response.setImportTotalUsd( domain.getImportTotalUsd() );
        response.setImportTotalUsdFormat(domain.getImportTotalUsdFormat());

        response.setImportTotalSoles( domain.getImportTotalSoles() );
        response.setImportTotalSolesFormat(domain.getImportTotalSolesFormat());

        response.setDocumentAccountType( domain.getDocumentAccountType() );
        response.setDocumentAccountState( domain.getDocumentAccountState() );
        response.setDocumentAccountState( domain.getDocumentAccountState() );
        if (domain.getDocumentAccountState() != null && !domain.getDocumentAccountState().isEmpty()){
            DocumentAccountStateEnum.DocumentAccountStateModel documentAccountStateModelData = EnumMappeableService
                    .map(DocumentAccountStateEnum.class)
                    .stream().filter(
                            documentAccountStateModel -> documentAccountStateModel.name().equals(domain.getDocumentAccountState()))
                    .findFirst().get();
            response.setDocumentAccountStateDescription(documentAccountStateModelData.description());
        }

        if (domain.getDateProcess() != null &&
                domain.getDateProcess().getId() != null &&
                !domain.getDateProcess().getId().isEmpty()){
            response.setDateProcess( DateProcessResponse.from(domain.getDateProcess()) );
        }

        if (domain.getCountry() != null &&
                domain.getCountry().getId() != null &&
                !domain.getCountry().getId().isEmpty()){
            response.setCountry( CountryResponse.from(domain.getCountry()) );
        }

        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency( CurrencyResponse.from(domain.getCurrency()) );
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            response.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getResponsible() != null &&
                domain.getResponsible().getId() != null &&
                !domain.getResponsible().getId().isEmpty()){
            response.setResponsible(EmployeeResponse.from(domain.getResponsible()));
        }

        response.setTotalDocumentFinancyCurrency(domain.getTotalDocumentFinancyCurrency());
        response.setTotalDocumentFinancyCurrencyFormat(domain.getTotalDocumentFinancyCurrencyFormat());
        return response;
    }
}
