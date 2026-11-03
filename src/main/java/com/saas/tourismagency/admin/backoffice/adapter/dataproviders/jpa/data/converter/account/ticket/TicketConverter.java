package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.ticket;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformainvoice.ProformaInvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.TicketEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.TicketDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.ticket.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.ticket.RptDocumentAccountUsd;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberToLetterConverter;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketConverter implements Converter<TicketDomain, TicketEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DateProcessConverter dateProcessConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private ProformaInvoiceConverter proformaInvoiceConverter;

    @Override
    public TicketDomain toDomain(TicketEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TicketDomain ticketDomain = new TicketDomain();

        ticketDomain.setId( source.getId() );
        ticketDomain.setDeleted( source.getDeleted() );
        ticketDomain.setEnabled( source.getEnabled() );
        ticketDomain.setMigrated( source.getMigrated() );
        ticketDomain.setCreatedAt( source.getCreatedAt() );
        ticketDomain.setUpdatedAt( source.getUpdatedAt() );
        ticketDomain.setDeletedAt( source.getDeletedAt() );
        ticketDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        ticketDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        ticketDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        ticketDomain.setIdentifierName( source.getIdentifierName() );
        ticketDomain.setTenant( source.getTenant() );
        ticketDomain.setName( source.getName() );
        ticketDomain.setDescription( source.getDescription() );
        ticketDomain.setIdentify( source.getIdentify() );

        ticketDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        ticketDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        ticketDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        ticketDomain.setDate( source.getDate() );
        ticketDomain.setDateCancel( source.getDateCancel() );
        ticketDomain.setDateAnnulled( source.getDateAnnulled() );
        ticketDomain.setAddress( source.getAddress() );
        ticketDomain.setReferenceDocument( source.getReferenceDocument() );
        ticketDomain.setDateInit( source.getDateInit() );
        ticketDomain.setDateEnd( source.getDateEnd() );
        ticketDomain.setNoPax( source.getNoPax() );
        ticketDomain.setSubTotalPrice( source.getSubTotalPrice() );
        ticketDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        ticketDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        ticketDomain.setImportIgvUsd( source.getImportIgvUsd() );
        ticketDomain.setImportIgvSoles( source.getImportIgvSoles() );
        ticketDomain.setImportTotalUsd( source.getImportTotalUsd() );
        ticketDomain.setImportTotalSoles( source.getImportTotalSoles() );

        ticketDomain.setDocumentAccountType( source.getDocumentAccountType() );
        ticketDomain.setDocumentAccountState( source.getDocumentAccountState() );


        ticketDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        ticketDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        ticketDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        ticketDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        ticketDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        ticketDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        ticketDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        ticketDomain.setNliqpyear(source.getNliqpyear());
        ticketDomain.setNliqpnumber(source.getNliqpnumber());
        ticketDomain.setNproformayear(source.getNproformayear());
        ticketDomain.setNproformanumber(source.getNproformanumber());

        ticketDomain.setProformaInvoice(proformaInvoiceConverter.toDomain(source.getProformaInvoice()));

        return ticketDomain;
    }

    @Override
    public TicketEntity toEntity(TicketDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TicketEntity ticketEntity = new TicketEntity();

        ticketEntity.setId( source.getId() );
        ticketEntity.setDeleted( source.getDeleted() );
        ticketEntity.setEnabled( source.getEnabled() );
        ticketEntity.setMigrated( source.getMigrated() );
        ticketEntity.setCreatedAt( source.getCreatedAt() );
        ticketEntity.setUpdatedAt( source.getUpdatedAt() );
        ticketEntity.setDeletedAt( source.getDeletedAt() );
        ticketEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        ticketEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        ticketEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        ticketEntity.setIdentifierName( source.getIdentifierName() );
        ticketEntity.setTenant( source.getTenant() );
        ticketEntity.setName( source.getName() );
        ticketEntity.setDescription( source.getDescription() );
        ticketEntity.setIdentify( source.getIdentify() );

        ticketEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        ticketEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        ticketEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        ticketEntity.setDate( source.getDate() );
        ticketEntity.setDateCancel( source.getDateCancel() );
        ticketEntity.setDateAnnulled( source.getDateAnnulled() );
        ticketEntity.setAddress( source.getAddress() );
        ticketEntity.setReferenceDocument( source.getReferenceDocument() );
        ticketEntity.setDateInit( source.getDateInit() );
        ticketEntity.setDateEnd( source.getDateEnd() );
        ticketEntity.setNoPax( source.getNoPax() );
        ticketEntity.setSubTotalPrice( source.getSubTotalPrice() );
        ticketEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        ticketEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        ticketEntity.setImportIgvUsd( source.getImportIgvUsd() );
        ticketEntity.setImportIgvSoles( source.getImportIgvSoles() );
        ticketEntity.setImportTotalUsd( source.getImportTotalUsd() );
        ticketEntity.setImportTotalSoles( source.getImportTotalSoles() );

        ticketEntity.setDocumentAccountType( source.getDocumentAccountType() );
        ticketEntity.setDocumentAccountState( source.getDocumentAccountState() );


        ticketEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        ticketEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        ticketEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        ticketEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        ticketEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        ticketEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        ticketEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        ticketEntity.setNliqpyear(source.getNliqpyear());
        ticketEntity.setNliqpnumber(source.getNliqpnumber());
        ticketEntity.setNproformayear(source.getNproformayear());
        ticketEntity.setNproformanumber(source.getNproformanumber());

        ticketEntity.setProformaInvoice(proformaInvoiceConverter.toEntity(source.getProformaInvoice()));

        return ticketEntity;
    }


    public IdBaseDomain toDomainIdBase(TicketEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(TicketEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<TicketEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public TicketDomain toDomainIdSimpleDomainCaseGetModificationData(TicketEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TicketDomain ticketDomain = new TicketDomain();
        ticketDomain.setId( source.getId() );
        ticketDomain.setImportTotalUsd( source.getImportTotalUsd() );
        ticketDomain.setImportTotalSoles( source.getImportTotalSoles() );
        ticketDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        ticketDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        ticketDomain.setImportIgvUsd( source.getImportIgvUsd() );
        ticketDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return ticketDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<TicketDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(TicketDomain source) {
        if (source == null) {
            return null;
        }
        RptDocumentAccountUsd target = new RptDocumentAccountUsd();
        target.setCustomerValue(source.getCustomer() != null ? source.getCustomer().getName() : null);
        target.setReferenceValue(source.getReferenceDocument());
        target.setAddressValue(source.getAddress());
        target.setIdentifygroupValue(source.getRegisterGroup() != null ? source.getRegisterGroup().getIdentify() : null);
        target.setNamegroupValue(source.getRegisterGroup() != null ? source.getRegisterGroup().getName() : source.getName());
        target.setDateValue(LocalDateTimeUtil.toLocalDateFormatShort(source.getDate()));
        target.setIdentifydocumentValue(source.getIdentify());
        target.setChangetypeValue(source.getChangeType() != null ? source.getChangeType().getPriceSaleFormat() : null);
        target.setSubtotalusdValue(source.getImportSubTotalUsdFormat());
        target.setIgvusdValue(source.getImportIgvUsdFormat());
        target.setTotalusdValue(source.getImportTotalUsdFormat());

        if (source.getProformaInvoice() != null) {
            target.setTypedocaffetValue(source.getProformaInvoice().getTypeInheritanceProforma());
            target.setNrodocaffectValue(source.getNproformayear() != null && source.getNproformanumber() != null ? source.getNproformayear() + "-" + source.getNproformanumber() : null);
        }

        String importTotalUsdFormat = source.getImportTotalUsdFormat();
        target.setValuestrlettertotalusdValue(importTotalUsdFormat != null ? NumberToLetterConverter.convertNumberToLetter(importTotalUsdFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<TicketDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(TicketDomain source) {
        if (source == null) {
            return null;
        }
        RptDocumentAccountSoles target = new RptDocumentAccountSoles();
        target.setCustomerValue(source.getCustomer() != null ? source.getCustomer().getName() : null);
        target.setReferenceValue(source.getReferenceDocument());
        target.setAddressValue(source.getAddress());
        target.setIdentifygroupValue(source.getRegisterGroup() != null ? source.getRegisterGroup().getIdentify() : null);
        target.setNamegroupValue(source.getRegisterGroup() != null ? source.getRegisterGroup().getName() : source.getName());
        target.setDateValue(LocalDateTimeUtil.toLocalDateFormatShort(source.getDate()));
        target.setIdentifydocumentValue(source.getIdentify());
        target.setChangetypeValue(source.getChangeType() != null ? source.getChangeType().getPriceSaleFormat() : null);
        target.setSubtotalsolesValue(source.getImportSubTotalSolesFormat());
        target.setIgvsolesValue(source.getImportIgvSolesFormat());
        target.setTotalsolesValue(source.getImportTotalSolesFormat());

        if (source.getProformaInvoice() != null) {
            target.setTypedocaffetValue(source.getProformaInvoice().getTypeInheritanceProforma());
            target.setNrodocaffectValue(source.getNproformayear() != null && source.getNproformanumber() != null ? source.getNproformayear() + "-" + source.getNproformanumber() : null);
        }

        String importTotalSolesFormat = source.getImportTotalSolesFormat();
        target.setValuestrlettertotalsolesValue(importTotalSolesFormat != null ? NumberToLetterConverter.convertNumberToLetterSoles(importTotalSolesFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<TicketDomain> toDomainAllSimpleToDomainCaseListTicket(List<TicketEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            TicketDomain ticketDomain = new TicketDomain();
            ticketDomain.setId(entity.getId());
            ticketDomain.setIdentify(entity.getIdentify());
            ticketDomain.setDate(entity.getDate());
            ticketDomain.setDocumentAccountState(entity.getDocumentAccountState());
            ticketDomain.setImportTotalUsd(entity.getImportTotalUsd());
            ticketDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                ticketDomain.setCurrency(new CurrencyDomain());
                ticketDomain.getCurrency().setId(entity.getCurrency().getId());
                ticketDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                ticketDomain.setCustomer(new CustomerDomain());
                ticketDomain.getCustomer().setId(entity.getCustomer().getId());
                ticketDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                ticketDomain.setRegisterGroup(new RegisterGroupDomain());
                ticketDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                ticketDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                ticketDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return ticketDomain;
        }).toList();
    }

    public PageDTO<TicketDomain> toDomainPageCaseListTicket(Page<TicketEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<TicketDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListTicket(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
