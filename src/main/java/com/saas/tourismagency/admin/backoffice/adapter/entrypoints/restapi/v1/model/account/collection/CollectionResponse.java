package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.InvoiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.ticket.TicketResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.CostSheetTwoStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CollectionResponse extends DocumentFinanceResponse {

    @JsonProperty("invoice")
    protected InvoiceResponse invoice;

    @JsonProperty("ticket")
    protected TicketResponse ticket;

    @JsonProperty("nroInvoice")
    protected String nroInvoice;

    @JsonProperty("nroTicket")
    protected String nroTicket;

    @JsonProperty("nroInvoicePrefix")
    protected String nroInvoicePrefix;

    @JsonProperty("nroTicketPrefix")
    protected String nroTicketPrefix;

    public static CollectionResponse from(CollectionDomain domain) {
        CollectionResponse response = new CollectionResponse();
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
        response.setImportSubTotalSoles( domain.getImportSubTotalSoles() );
        response.setImportIgvUsd( domain.getImportIgvUsd() );
        response.setImportIgvSoles( domain.getImportIgvSoles() );
        response.setImportTotalUsd( domain.getImportTotalUsd() );
        response.setImportTotalSoles( domain.getImportTotalSoles() );

        response.setImportSubTotalUsdFormat( domain.getImportSubTotalUsdFormat() );
        response.setImportSubTotalSolesFormat( domain.getImportSubTotalSolesFormat() );
        response.setImportIgvUsdFormat( domain.getImportIgvUsdFormat() );
        response.setImportIgvSolesFormat( domain.getImportIgvSolesFormat() );
        response.setImportTotalUsdFormat( domain.getImportTotalUsdFormat() );
        response.setImportTotalSolesFormat( domain.getImportTotalSolesFormat() );

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


        //
        if (domain.getInvoice() != null &&
                domain.getInvoice().getId() != null &&
                !domain.getInvoice().getId().isEmpty()){
            response.setInvoice(InvoiceResponse.from(domain.getInvoice()));
        }

        if (domain.getTicket() != null &&
                domain.getTicket().getId() != null &&
                !domain.getTicket().getId().isEmpty()){
            response.setTicket(TicketResponse.from(domain.getTicket()));
        }

        response.setTotalDocumentFinancyCurrency(domain.getTotalDocumentFinancyCurrency());
        response.setTotalDocumentFinancyCurrencyFormat(domain.getTotalDocumentFinancyCurrencyFormat());

        response.setNroInvoice(domain.getNroInvoice());
        response.setNroInvoicePrefix(domain.getNroInvoicePrefix());

        response.setNroTicket(domain.getNroTickets());
        response.setNroTicketPrefix(domain.getNroTicketsPrefix());

        return response;
    }
}
