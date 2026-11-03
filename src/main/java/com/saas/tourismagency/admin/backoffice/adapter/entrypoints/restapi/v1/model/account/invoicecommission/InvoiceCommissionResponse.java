package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv.IgvResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class InvoiceCommissionResponse extends DocumentFinanceResponse {

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("igv")
    private IgvResponse igv;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("nproformayear")
    private String nproformayear;

    @JsonProperty("nproformanumber")
    private String nproformanumber;

    @JsonProperty("nproformancred")
    private String nproformancred;

    @JsonProperty("nproformancrednumber")
    private String nproformancrednumber;

    public static InvoiceCommissionResponse from(InvoiceCommissionDomain domain) {
        InvoiceCommissionResponse response = new InvoiceCommissionResponse();
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

        response.setReference(domain.getReference());

        if (domain.getIgv() != null &&
                domain.getIgv().getId() != null &&
                !domain.getIgv().getId().isEmpty()) {
            response.setIgv(IgvResponse.from(domain.getIgv()));
        }

        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()) {
            response.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }

        response.setNproformayear(domain.getNproformayear());
        response.setNproformanumber(domain.getNproformanumber());

        response.setNproformancred(domain.getNproformancred());
        response.setNproformancrednumber(domain.getNproformanumber());

        response.setTotalDocumentFinancyCurrency(domain.getTotalDocumentFinancyCurrency());
        response.setTotalDocumentFinancyCurrencyFormat(domain.getTotalDocumentFinancyCurrencyFormat());

        return response;
    }
}
