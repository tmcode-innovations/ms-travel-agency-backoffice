package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.invoicecommission;

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
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoicecommission.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoicecommission.RptDocumentAccountUsd;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberToLetterConverter;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
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
public class InvoiceCommissionConverter implements Converter<InvoiceCommissionDomain, InvoiceCommissionEntity> {

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
    private SupplierConverter supplierConverter;

    @Autowired
    private ProformaCreditNoteConverter proformaCreditNoteConverter;

    @Autowired
    private ProformaInvoiceConverter proformaInvoiceConverter;

    @Override
    public InvoiceCommissionDomain toDomain(InvoiceCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        InvoiceCommissionDomain invoiceCommissionDomain = new InvoiceCommissionDomain();

        invoiceCommissionDomain.setId( source.getId() );
        invoiceCommissionDomain.setDeleted( source.getDeleted() );
        invoiceCommissionDomain.setEnabled( source.getEnabled() );
        invoiceCommissionDomain.setMigrated( source.getMigrated() );
        invoiceCommissionDomain.setCreatedAt( source.getCreatedAt() );
        invoiceCommissionDomain.setUpdatedAt( source.getUpdatedAt() );
        invoiceCommissionDomain.setDeletedAt( source.getDeletedAt() );
        invoiceCommissionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        invoiceCommissionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        invoiceCommissionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        invoiceCommissionDomain.setIdentifierName( source.getIdentifierName() );
        invoiceCommissionDomain.setTenant( source.getTenant() );
        invoiceCommissionDomain.setName( source.getName() );
        invoiceCommissionDomain.setDescription( source.getDescription() );
        invoiceCommissionDomain.setIdentify( source.getIdentify() );

        invoiceCommissionDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        invoiceCommissionDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        invoiceCommissionDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        invoiceCommissionDomain.setDate( source.getDate() );
        invoiceCommissionDomain.setDateCancel( source.getDateCancel() );
        invoiceCommissionDomain.setDateAnnulled( source.getDateAnnulled() );
        invoiceCommissionDomain.setAddress( source.getAddress() );
        invoiceCommissionDomain.setReferenceDocument( source.getReferenceDocument() );
        invoiceCommissionDomain.setDateInit( source.getDateInit() );
        invoiceCommissionDomain.setDateEnd( source.getDateEnd() );
        invoiceCommissionDomain.setNoPax( source.getNoPax() );
        invoiceCommissionDomain.setSubTotalPrice( source.getSubTotalPrice() );
        invoiceCommissionDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        invoiceCommissionDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        invoiceCommissionDomain.setImportIgvUsd( source.getImportIgvUsd() );
        invoiceCommissionDomain.setImportIgvSoles( source.getImportIgvSoles() );
        invoiceCommissionDomain.setImportTotalUsd( source.getImportTotalUsd() );
        invoiceCommissionDomain.setImportTotalSoles( source.getImportTotalSoles() );

        invoiceCommissionDomain.setDocumentAccountType( source.getDocumentAccountType() );
        invoiceCommissionDomain.setDocumentAccountState( source.getDocumentAccountState() );


        invoiceCommissionDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        invoiceCommissionDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        invoiceCommissionDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        invoiceCommissionDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        invoiceCommissionDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        invoiceCommissionDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        invoiceCommissionDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        invoiceCommissionDomain.setReference(source.getReference());
        invoiceCommissionDomain.setIgv(igvConverter.toDomain(source.getIgv()));
        invoiceCommissionDomain.setSupplier(supplierConverter.toDomain(source.getSupplier()));

        invoiceCommissionDomain.setNproformayear(source.getNproformayear());
        invoiceCommissionDomain.setNproformanumber(source.getNproformanumber());
        invoiceCommissionDomain.setNproformancred(source.getNproformancred());
        invoiceCommissionDomain.setNproformancrednumber(source.getNproformancrednumber());

        invoiceCommissionDomain.setProformaInvoice(proformaInvoiceConverter.toDomain(source.getProformaInvoice()));
        invoiceCommissionDomain.setProformaCreditNote(proformaCreditNoteConverter.toDomain(source.getProformaCreditNote()));

        return invoiceCommissionDomain;
    }

    @Override
    public InvoiceCommissionEntity toEntity(InvoiceCommissionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        InvoiceCommissionEntity invoiceCommissionEntity = new InvoiceCommissionEntity();

        invoiceCommissionEntity.setId( source.getId() );
        invoiceCommissionEntity.setDeleted( source.getDeleted() );
        invoiceCommissionEntity.setEnabled( source.getEnabled() );
        invoiceCommissionEntity.setMigrated( source.getMigrated() );
        invoiceCommissionEntity.setCreatedAt( source.getCreatedAt() );
        invoiceCommissionEntity.setUpdatedAt( source.getUpdatedAt() );
        invoiceCommissionEntity.setDeletedAt( source.getDeletedAt() );
        invoiceCommissionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        invoiceCommissionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        invoiceCommissionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        invoiceCommissionEntity.setIdentifierName( source.getIdentifierName() );
        invoiceCommissionEntity.setTenant( source.getTenant() );
        invoiceCommissionEntity.setName( source.getName() );
        invoiceCommissionEntity.setDescription( source.getDescription() );
        invoiceCommissionEntity.setIdentify( source.getIdentify() );

        invoiceCommissionEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        invoiceCommissionEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        invoiceCommissionEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        invoiceCommissionEntity.setDate( source.getDate() );
        invoiceCommissionEntity.setDateCancel( source.getDateCancel() );
        invoiceCommissionEntity.setDateAnnulled( source.getDateAnnulled() );
        invoiceCommissionEntity.setAddress( source.getAddress() );
        invoiceCommissionEntity.setReferenceDocument( source.getReferenceDocument() );
        invoiceCommissionEntity.setDateInit( source.getDateInit() );
        invoiceCommissionEntity.setDateEnd( source.getDateEnd() );
        invoiceCommissionEntity.setNoPax( source.getNoPax() );
        invoiceCommissionEntity.setSubTotalPrice( source.getSubTotalPrice() );
        invoiceCommissionEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        invoiceCommissionEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        invoiceCommissionEntity.setImportIgvUsd( source.getImportIgvUsd() );
        invoiceCommissionEntity.setImportIgvSoles( source.getImportIgvSoles() );
        invoiceCommissionEntity.setImportTotalUsd( source.getImportTotalUsd() );
        invoiceCommissionEntity.setImportTotalSoles( source.getImportTotalSoles() );

        invoiceCommissionEntity.setDocumentAccountType( source.getDocumentAccountType() );
        invoiceCommissionEntity.setDocumentAccountState( source.getDocumentAccountState() );


        invoiceCommissionEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        invoiceCommissionEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        invoiceCommissionEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        invoiceCommissionEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        invoiceCommissionEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        invoiceCommissionEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        invoiceCommissionEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        invoiceCommissionEntity.setReference(source.getReference());
        invoiceCommissionEntity.setIgv(igvConverter.toEntity(source.getIgv()));
        invoiceCommissionEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()));

        invoiceCommissionEntity.setNproformayear(source.getNproformayear());
        invoiceCommissionEntity.setNproformanumber(source.getNproformanumber());
        invoiceCommissionEntity.setNproformancred(source.getNproformancred());
        invoiceCommissionEntity.setNproformancrednumber(source.getNproformancrednumber());

        invoiceCommissionEntity.setProformaInvoice(proformaInvoiceConverter.toEntity(source.getProformaInvoice()));
        invoiceCommissionEntity.setProformaCreditNote(proformaCreditNoteConverter.toEntity(source.getProformaCreditNote()));

        return invoiceCommissionEntity;
    }

    public IdBaseDomain toDomainIdBase(InvoiceCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(InvoiceCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<InvoiceCommissionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public InvoiceCommissionDomain toDomainIdSimpleDomainCaseGetModificationData(InvoiceCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        InvoiceCommissionDomain invoiceCommissionDomain = new InvoiceCommissionDomain();
        invoiceCommissionDomain.setId( source.getId() );
        invoiceCommissionDomain.setImportTotalUsd( source.getImportTotalUsd() );
        invoiceCommissionDomain.setImportTotalSoles( source.getImportTotalSoles() );
        invoiceCommissionDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        invoiceCommissionDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        invoiceCommissionDomain.setImportIgvUsd( source.getImportIgvUsd() );
        invoiceCommissionDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return invoiceCommissionDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<InvoiceCommissionDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(InvoiceCommissionDomain source) {
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

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<InvoiceCommissionDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(InvoiceCommissionDomain source) {
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

    public List<InvoiceCommissionDomain> toDomainAllSimpleToDomainCaseListInvoiceCommission(List<InvoiceCommissionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            InvoiceCommissionDomain invoiceCommissionDomain = new InvoiceCommissionDomain();
            invoiceCommissionDomain.setId(entity.getId());
            invoiceCommissionDomain.setIdentify(entity.getIdentify());
            invoiceCommissionDomain.setDate(entity.getDate());
            invoiceCommissionDomain.setDocumentAccountState(entity.getDocumentAccountState());
            invoiceCommissionDomain.setImportTotalUsd(entity.getImportTotalUsd());
            invoiceCommissionDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                invoiceCommissionDomain.setCurrency(new CurrencyDomain());
                invoiceCommissionDomain.getCurrency().setId(entity.getCurrency().getId());
                invoiceCommissionDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                invoiceCommissionDomain.setCustomer(new CustomerDomain());
                invoiceCommissionDomain.getCustomer().setId(entity.getCustomer().getId());
                invoiceCommissionDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                invoiceCommissionDomain.setRegisterGroup(new RegisterGroupDomain());
                invoiceCommissionDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                invoiceCommissionDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                invoiceCommissionDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return invoiceCommissionDomain;
        }).toList();
    }

    public PageDTO<InvoiceCommissionDomain> toDomainPageCaseListInvoiceCommission(Page<InvoiceCommissionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<InvoiceCommissionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListInvoiceCommission(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
