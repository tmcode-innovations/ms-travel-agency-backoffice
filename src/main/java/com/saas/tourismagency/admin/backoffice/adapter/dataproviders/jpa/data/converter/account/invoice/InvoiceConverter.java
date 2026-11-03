package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.invoice;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformacreditnote.ProformaCreditNoteConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformainvoice.ProformaInvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.igv.IgvConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DebitNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.DebitNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoice.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoice.RptDocumentAccountUsd;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberToLetterConverter;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceConverter implements Converter<InvoiceDomain, InvoiceEntity> {

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
    private IgvConverter igvConverter;

    @Autowired
    private ProformaInvoiceConverter proformaInvoiceConverter;

    @Autowired
    private ProformaCreditNoteConverter proformaCreditNoteConverter;

    @Override
    public InvoiceDomain toDomain(InvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        InvoiceDomain invoiceDomain = new InvoiceDomain();

        invoiceDomain.setId( source.getId() );
        invoiceDomain.setDeleted( source.getDeleted() );
        invoiceDomain.setEnabled( source.getEnabled() );
        invoiceDomain.setMigrated( source.getMigrated() );
        invoiceDomain.setCreatedAt( source.getCreatedAt() );
        invoiceDomain.setUpdatedAt( source.getUpdatedAt() );
        invoiceDomain.setDeletedAt( source.getDeletedAt() );
        invoiceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        invoiceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        invoiceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        invoiceDomain.setIdentifierName( source.getIdentifierName() );
        invoiceDomain.setTenant( source.getTenant() );
        invoiceDomain.setName( source.getName() );
        invoiceDomain.setDescription( source.getDescription() );
        invoiceDomain.setIdentify( source.getIdentify() );

        invoiceDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        invoiceDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        invoiceDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        invoiceDomain.setDate( source.getDate() );
        invoiceDomain.setDateCancel( source.getDateCancel() );
        invoiceDomain.setDateAnnulled( source.getDateAnnulled() );
        invoiceDomain.setAddress( source.getAddress() );
        invoiceDomain.setReferenceDocument( source.getReferenceDocument() );
        invoiceDomain.setDateInit( source.getDateInit() );
        invoiceDomain.setDateEnd( source.getDateEnd() );
        invoiceDomain.setNoPax( source.getNoPax() );
        invoiceDomain.setSubTotalPrice( source.getSubTotalPrice() );
        invoiceDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        invoiceDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        invoiceDomain.setImportIgvUsd( source.getImportIgvUsd() );
        invoiceDomain.setImportIgvSoles( source.getImportIgvSoles() );
        invoiceDomain.setImportTotalUsd( source.getImportTotalUsd() );
        invoiceDomain.setImportTotalSoles( source.getImportTotalSoles() );

        invoiceDomain.setDocumentAccountType( source.getDocumentAccountType() );
        invoiceDomain.setDocumentAccountState( source.getDocumentAccountState() );


        invoiceDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        invoiceDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        invoiceDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        invoiceDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        invoiceDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        invoiceDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        invoiceDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        invoiceDomain.setNliqpyear(source.getNliqpyear());
        invoiceDomain.setNliqpnumber(source.getNliqpnumber());
        invoiceDomain.setNproformayear(source.getNproformayear());
        invoiceDomain.setNproformanumber(source.getNproformanumber());
        invoiceDomain.setNproformancred(source.getNproformancred());
        invoiceDomain.setNproformancrednumber(source.getNproformancrednumber());

        invoiceDomain.setIgv(igvConverter.toDomain(source.getIgv()));
        invoiceDomain.setProformaInvoice(proformaInvoiceConverter.toDomain(source.getProformaInvoice()));
        invoiceDomain.setProformaCreditNote(proformaCreditNoteConverter.toDomain(source.getProformaCreditNote()));

        return invoiceDomain;
    }

    @Override
    public InvoiceEntity toEntity(InvoiceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setId( source.getId() );
        invoiceEntity.setDeleted( source.getDeleted() );
        invoiceEntity.setEnabled( source.getEnabled() );
        invoiceEntity.setMigrated( source.getMigrated() );
        invoiceEntity.setCreatedAt( source.getCreatedAt() );
        invoiceEntity.setUpdatedAt( source.getUpdatedAt() );
        invoiceEntity.setDeletedAt( source.getDeletedAt() );
        invoiceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        invoiceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        invoiceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        invoiceEntity.setIdentifierName( source.getIdentifierName() );
        invoiceEntity.setTenant( source.getTenant() );
        invoiceEntity.setName( source.getName() );
        invoiceEntity.setDescription( source.getDescription() );
        invoiceEntity.setIdentify( source.getIdentify() );

        invoiceEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        invoiceEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        invoiceEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        invoiceEntity.setDate( source.getDate() );
        invoiceEntity.setDateCancel( source.getDateCancel() );
        invoiceEntity.setDateAnnulled( source.getDateAnnulled() );
        invoiceEntity.setAddress( source.getAddress() );
        invoiceEntity.setReferenceDocument( source.getReferenceDocument() );
        invoiceEntity.setDateInit( source.getDateInit() );
        invoiceEntity.setDateEnd( source.getDateEnd() );
        invoiceEntity.setNoPax( source.getNoPax() );
        invoiceEntity.setSubTotalPrice( source.getSubTotalPrice() );
        invoiceEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        invoiceEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        invoiceEntity.setImportIgvUsd( source.getImportIgvUsd() );
        invoiceEntity.setImportIgvSoles( source.getImportIgvSoles() );
        invoiceEntity.setImportTotalUsd( source.getImportTotalUsd() );
        invoiceEntity.setImportTotalSoles( source.getImportTotalSoles() );

        invoiceEntity.setDocumentAccountType( source.getDocumentAccountType() );
        invoiceEntity.setDocumentAccountState( source.getDocumentAccountState() );


        invoiceEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        invoiceEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        invoiceEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        invoiceEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        invoiceEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        invoiceEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        invoiceEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        invoiceEntity.setNliqpyear(source.getNliqpyear());
        invoiceEntity.setNliqpnumber(source.getNliqpnumber());
        invoiceEntity.setNproformayear(source.getNproformayear());
        invoiceEntity.setNproformanumber(source.getNproformanumber());
        invoiceEntity.setNproformancred(source.getNproformancred());
        invoiceEntity.setNproformancrednumber(source.getNproformancrednumber());

        invoiceEntity.setIgv(igvConverter.toEntity(source.getIgv()));
        invoiceEntity.setProformaInvoice(proformaInvoiceConverter.toEntity(source.getProformaInvoice()));
        invoiceEntity.setProformaCreditNote(proformaCreditNoteConverter.toEntity(source.getProformaCreditNote()));

        return invoiceEntity;
    }


    public IdBaseDomain toDomainIdBase(InvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(InvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<InvoiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public InvoiceDomain toDomainIdSimpleDomainCaseGetModificationData(InvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        InvoiceDomain invoiceDomain = new InvoiceDomain();
        invoiceDomain.setId( source.getId() );
        invoiceDomain.setImportTotalUsd( source.getImportTotalUsd() );
        invoiceDomain.setImportTotalSoles( source.getImportTotalSoles() );
        invoiceDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        invoiceDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        invoiceDomain.setImportIgvUsd( source.getImportIgvUsd() );
        invoiceDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return invoiceDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<InvoiceDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(InvoiceDomain source) {
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
        } else if (source.getProformaCreditNote() != null) {
            target.setTypedocaffetValue(source.getProformaCreditNote().getTypeInheritanceProforma());
            target.setNrodocaffectValue(source.getNproformancred() != null && source.getNproformancrednumber() != null ? source.getNproformancred() + "-" + source.getNproformancrednumber() : null);
        }

        String importTotalUsdFormat = source.getImportTotalUsdFormat();
        target.setValuestrlettertotalusdValue(importTotalUsdFormat != null ? NumberToLetterConverter.convertNumberToLetter(importTotalUsdFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<InvoiceDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(InvoiceDomain source) {
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
        } else if (source.getProformaCreditNote() != null) {
            target.setTypedocaffetValue(source.getProformaCreditNote().getTypeInheritanceProforma());
            target.setNrodocaffectValue(source.getNproformancred() != null && source.getNproformancrednumber() != null ? source.getNproformancred() + "-" + source.getNproformancrednumber() : null);
        }

        String importTotalSolesFormat = source.getImportTotalSolesFormat();
        target.setValuestrlettertotalsolesValue(importTotalSolesFormat != null ? NumberToLetterConverter.convertNumberToLetterSoles(importTotalSolesFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<InvoiceDomain> toDomainAllSimpleToDomainCaseListInvoice(List<InvoiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            InvoiceDomain invoiceDomain = new InvoiceDomain();
            invoiceDomain.setId(entity.getId());
            invoiceDomain.setIdentify(entity.getIdentify());
            invoiceDomain.setDate(entity.getDate());
            invoiceDomain.setDocumentAccountState(entity.getDocumentAccountState());
            invoiceDomain.setImportTotalUsd(entity.getImportTotalUsd());
            invoiceDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                invoiceDomain.setCurrency(new CurrencyDomain());
                invoiceDomain.getCurrency().setId(entity.getCurrency().getId());
                invoiceDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                invoiceDomain.setCustomer(new CustomerDomain());
                invoiceDomain.getCustomer().setId(entity.getCustomer().getId());
                invoiceDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                invoiceDomain.setRegisterGroup(new RegisterGroupDomain());
                invoiceDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                invoiceDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                invoiceDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return invoiceDomain;
        }).toList();
    }

    public PageDTO<InvoiceDomain> toDomainPageCaseListInvoice(Page<InvoiceEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<InvoiceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListInvoice(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
