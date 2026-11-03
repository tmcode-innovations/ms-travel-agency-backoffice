package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.collection;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.invoice.InvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.ticket.TicketConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.collection.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.collection.RptDocumentAccountUsd;
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
public class CollectionConverter implements Converter<CollectionDomain, CollectionEntity> {

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
    private InvoiceConverter invoiceConverter;

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public CollectionDomain toDomain(CollectionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CollectionDomain collectionDomain = new CollectionDomain();

        collectionDomain.setId( source.getId() );
        collectionDomain.setDeleted( source.getDeleted() );
        collectionDomain.setEnabled( source.getEnabled() );
        collectionDomain.setMigrated( source.getMigrated() );
        collectionDomain.setCreatedAt( source.getCreatedAt() );
        collectionDomain.setUpdatedAt( source.getUpdatedAt() );
        collectionDomain.setDeletedAt( source.getDeletedAt() );
        collectionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        collectionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        collectionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        collectionDomain.setIdentifierName( source.getIdentifierName() );
        collectionDomain.setTenant( source.getTenant() );
        collectionDomain.setName( source.getName() );
        collectionDomain.setDescription( source.getDescription() );
        collectionDomain.setIdentify( source.getIdentify() );

        collectionDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        collectionDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        collectionDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        collectionDomain.setDate( source.getDate() );
        collectionDomain.setDateCancel( source.getDateCancel() );
        collectionDomain.setDateAnnulled( source.getDateAnnulled() );
        collectionDomain.setAddress( source.getAddress() );
        collectionDomain.setReferenceDocument( source.getReferenceDocument() );
        collectionDomain.setDateInit( source.getDateInit() );
        collectionDomain.setDateEnd( source.getDateEnd() );
        collectionDomain.setNoPax( source.getNoPax() );
        collectionDomain.setSubTotalPrice( source.getSubTotalPrice() );
        collectionDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        collectionDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        collectionDomain.setImportIgvUsd( source.getImportIgvUsd() );
        collectionDomain.setImportIgvSoles( source.getImportIgvSoles() );
        collectionDomain.setImportTotalUsd( source.getImportTotalUsd() );
        collectionDomain.setImportTotalSoles( source.getImportTotalSoles() );

        collectionDomain.setDocumentAccountType( source.getDocumentAccountType() );
        collectionDomain.setDocumentAccountState( source.getDocumentAccountState() );


        collectionDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        collectionDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        collectionDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        collectionDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        collectionDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        collectionDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        collectionDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        collectionDomain.setNroInvoice(source.getNroInvoice());
        collectionDomain.setNroTickets(source.getNroTickets());

        collectionDomain.setNroInvoicePrefix(source.getNroInvoicePrefix());
        collectionDomain.setNroTicketsPrefix(source.getNroTicketsPrefix());

        collectionDomain.setInvoice(invoiceConverter.toDomain(source.getInvoice()));
        collectionDomain.setTicket(ticketConverter.toDomain(source.getTicket()));

        return collectionDomain;
    }

    @Override
    public CollectionEntity toEntity(CollectionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CollectionEntity collectionEntity = new CollectionEntity();

        collectionEntity.setId( source.getId() );
        collectionEntity.setDeleted( source.getDeleted() );
        collectionEntity.setEnabled( source.getEnabled() );
        collectionEntity.setMigrated( source.getMigrated() );
        collectionEntity.setCreatedAt( source.getCreatedAt() );
        collectionEntity.setUpdatedAt( source.getUpdatedAt() );
        collectionEntity.setDeletedAt( source.getDeletedAt() );
        collectionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        collectionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        collectionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        collectionEntity.setIdentifierName( source.getIdentifierName() );
        collectionEntity.setTenant( source.getTenant() );
        collectionEntity.setName( source.getName() );
        collectionEntity.setDescription( source.getDescription() );
        collectionEntity.setIdentify( source.getIdentify() );

        collectionEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        collectionEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        collectionEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        collectionEntity.setDate( source.getDate() );
        collectionEntity.setDateCancel( source.getDateCancel() );
        collectionEntity.setDateAnnulled( source.getDateAnnulled() );
        collectionEntity.setAddress( source.getAddress() );
        collectionEntity.setReferenceDocument( source.getReferenceDocument() );
        collectionEntity.setDateInit( source.getDateInit() );
        collectionEntity.setDateEnd( source.getDateEnd() );
        collectionEntity.setNoPax( source.getNoPax() );
        collectionEntity.setSubTotalPrice( source.getSubTotalPrice() );
        collectionEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        collectionEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        collectionEntity.setImportIgvUsd( source.getImportIgvUsd() );
        collectionEntity.setImportIgvSoles( source.getImportIgvSoles() );
        collectionEntity.setImportTotalUsd( source.getImportTotalUsd() );
        collectionEntity.setImportTotalSoles( source.getImportTotalSoles() );

        collectionEntity.setDocumentAccountType( source.getDocumentAccountType() );
        collectionEntity.setDocumentAccountState( source.getDocumentAccountState() );


        collectionEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        collectionEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        collectionEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        collectionEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        collectionEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        collectionEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        collectionEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        collectionEntity.setNroInvoice(source.getNroInvoice());
        collectionEntity.setNroTickets(source.getNroTickets());

        collectionEntity.setNroInvoicePrefix(source.getNroInvoicePrefix());
        collectionEntity.setNroTicketsPrefix(source.getNroTicketsPrefix());

        collectionEntity.setInvoice(invoiceConverter.toEntity(source.getInvoice()));
        collectionEntity.setTicket(ticketConverter.toEntity(source.getTicket()));

        return collectionEntity;
    }


    public IdBaseDomain toDomainIdBase(CollectionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CollectionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CollectionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public CollectionDomain toDomainIdSimpleDomainCaseGetModificationData(CollectionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CollectionDomain collectionDomain = new CollectionDomain();
        collectionDomain.setId( source.getId() );
        collectionDomain.setImportTotalUsd( source.getImportTotalUsd() );
        collectionDomain.setImportTotalSoles( source.getImportTotalSoles() );
        collectionDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        collectionDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        collectionDomain.setImportIgvUsd( source.getImportIgvUsd() );
        collectionDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return collectionDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<CollectionDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(CollectionDomain source) {
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

        if (source.getInvoice() != null) {
            target.setTypedocaffetValue(source.getInvoice().getTypeInheritanceDocumentFinance());
            target.setNrodocaffectValue(source.getNroInvoice());
        } else if (source.getTicket() != null) {
            target.setTypedocaffetValue(source.getTicket().getTypeInheritanceDocumentFinance());
            target.setNrodocaffectValue(source.getNroTickets());
        }

        String importTotalUsdFormat = source.getImportTotalUsdFormat();
        target.setValuestrlettertotalusdValue(importTotalUsdFormat != null ? NumberToLetterConverter.convertNumberToLetter(importTotalUsdFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<CollectionDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(CollectionDomain source) {
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

        if (source.getInvoice() != null) {
            target.setTypedocaffetValue(source.getInvoice().getTypeInheritanceDocumentFinance());
            target.setNrodocaffectValue(source.getNroInvoice());
        } else if (source.getTicket() != null) {
            target.setTypedocaffetValue(source.getTicket().getTypeInheritanceDocumentFinance());
            target.setNrodocaffectValue(source.getNroTickets());
        }

        String importTotalSolesFormat = source.getImportTotalSolesFormat();
        target.setValuestrlettertotalsolesValue(importTotalSolesFormat != null ? NumberToLetterConverter.convertNumberToLetterSoles(importTotalSolesFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<CollectionDomain> toDomainAllSimpleToDomainCaseListCollection(List<CollectionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CollectionDomain collectionDomain = new CollectionDomain();
            collectionDomain.setId(entity.getId());
            collectionDomain.setIdentify(entity.getIdentify());
            collectionDomain.setDate(entity.getDate());
            collectionDomain.setDocumentAccountState(entity.getDocumentAccountState());
            collectionDomain.setImportTotalUsd(entity.getImportTotalUsd());
            collectionDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                collectionDomain.setCurrency(new CurrencyDomain());
                collectionDomain.getCurrency().setId(entity.getCurrency().getId());
                collectionDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                collectionDomain.setCustomer(new CustomerDomain());
                collectionDomain.getCustomer().setId(entity.getCustomer().getId());
                collectionDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                collectionDomain.setRegisterGroup(new RegisterGroupDomain());
                collectionDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                collectionDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                collectionDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return collectionDomain;
        }).toList();
    }

    public PageDTO<CollectionDomain> toDomainPageCaseListCollection(Page<CollectionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CollectionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCollection(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
