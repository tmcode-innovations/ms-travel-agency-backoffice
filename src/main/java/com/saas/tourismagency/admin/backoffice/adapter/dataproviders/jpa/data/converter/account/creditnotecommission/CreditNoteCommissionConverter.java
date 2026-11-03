package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.creditnotecommission;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.creditnotecommission.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.creditnotecommission.RptDocumentAccountUsd;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberToLetterConverter;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditNoteCommissionConverter implements Converter<CreditNoteCommissionDomain, CreditNoteCommissionEntity> {

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
    private DocumentFinanceConverter documentFinanceConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Override
    public CreditNoteCommissionDomain toDomain(CreditNoteCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CreditNoteCommissionDomain creditNoteCommissionDomain = new CreditNoteCommissionDomain();

        creditNoteCommissionDomain.setId( source.getId() );
        creditNoteCommissionDomain.setDeleted( source.getDeleted() );
        creditNoteCommissionDomain.setEnabled( source.getEnabled() );
        creditNoteCommissionDomain.setMigrated( source.getMigrated() );
        creditNoteCommissionDomain.setCreatedAt( source.getCreatedAt() );
        creditNoteCommissionDomain.setUpdatedAt( source.getUpdatedAt() );
        creditNoteCommissionDomain.setDeletedAt( source.getDeletedAt() );
        creditNoteCommissionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        creditNoteCommissionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        creditNoteCommissionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        creditNoteCommissionDomain.setIdentifierName( source.getIdentifierName() );
        creditNoteCommissionDomain.setTenant( source.getTenant() );
        creditNoteCommissionDomain.setName( source.getName() );
        creditNoteCommissionDomain.setDescription( source.getDescription() );
        creditNoteCommissionDomain.setIdentify( source.getIdentify() );

        creditNoteCommissionDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        creditNoteCommissionDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        creditNoteCommissionDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        creditNoteCommissionDomain.setDate( source.getDate() );
        creditNoteCommissionDomain.setDateCancel( source.getDateCancel() );
        creditNoteCommissionDomain.setDateAnnulled( source.getDateAnnulled() );
        creditNoteCommissionDomain.setAddress( source.getAddress() );
        creditNoteCommissionDomain.setReferenceDocument( source.getReferenceDocument() );
        creditNoteCommissionDomain.setDateInit( source.getDateInit() );
        creditNoteCommissionDomain.setDateEnd( source.getDateEnd() );
        creditNoteCommissionDomain.setNoPax( source.getNoPax() );
        creditNoteCommissionDomain.setSubTotalPrice( source.getSubTotalPrice() );
        creditNoteCommissionDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        creditNoteCommissionDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        creditNoteCommissionDomain.setImportIgvUsd( source.getImportIgvUsd() );
        creditNoteCommissionDomain.setImportIgvSoles( source.getImportIgvSoles() );
        creditNoteCommissionDomain.setImportTotalUsd( source.getImportTotalUsd() );
        creditNoteCommissionDomain.setImportTotalSoles( source.getImportTotalSoles() );

        creditNoteCommissionDomain.setDocumentAccountType( source.getDocumentAccountType() );
        creditNoteCommissionDomain.setDocumentAccountState( source.getDocumentAccountState() );


        creditNoteCommissionDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        creditNoteCommissionDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        creditNoteCommissionDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        creditNoteCommissionDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        creditNoteCommissionDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        creditNoteCommissionDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        creditNoteCommissionDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        creditNoteCommissionDomain.setReference(source.getReference());
        creditNoteCommissionDomain.setIdentifyDocumentFinance(source.getIdentifyDocumentFinance());
        creditNoteCommissionDomain.setDocumentAccountTypeAssociate(source.getDocumentAccountTypeAssociate());
        creditNoteCommissionDomain.setDocumentFinanceAssociate(documentFinanceConverter.toDomain(source.getDocumentFinanceAssociate()));
        creditNoteCommissionDomain.setSupplier(supplierConverter.toDomain(source.getSupplier()));
        return creditNoteCommissionDomain;
    }

    @Override
    public CreditNoteCommissionEntity toEntity(CreditNoteCommissionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CreditNoteCommissionEntity creditNoteCommissionEntity = new CreditNoteCommissionEntity();

        creditNoteCommissionEntity.setId( source.getId() );
        creditNoteCommissionEntity.setDeleted( source.getDeleted() );
        creditNoteCommissionEntity.setEnabled( source.getEnabled() );
        creditNoteCommissionEntity.setMigrated( source.getMigrated() );
        creditNoteCommissionEntity.setCreatedAt( source.getCreatedAt() );
        creditNoteCommissionEntity.setUpdatedAt( source.getUpdatedAt() );
        creditNoteCommissionEntity.setDeletedAt( source.getDeletedAt() );
        creditNoteCommissionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        creditNoteCommissionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        creditNoteCommissionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        creditNoteCommissionEntity.setIdentifierName( source.getIdentifierName() );
        creditNoteCommissionEntity.setTenant( source.getTenant() );
        creditNoteCommissionEntity.setName( source.getName() );
        creditNoteCommissionEntity.setDescription( source.getDescription() );
        creditNoteCommissionEntity.setIdentify( source.getIdentify() );

        creditNoteCommissionEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        creditNoteCommissionEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        creditNoteCommissionEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        creditNoteCommissionEntity.setDate( source.getDate() );
        creditNoteCommissionEntity.setDateCancel( source.getDateCancel() );
        creditNoteCommissionEntity.setDateAnnulled( source.getDateAnnulled() );
        creditNoteCommissionEntity.setAddress( source.getAddress() );
        creditNoteCommissionEntity.setReferenceDocument( source.getReferenceDocument() );
        creditNoteCommissionEntity.setDateInit( source.getDateInit() );
        creditNoteCommissionEntity.setDateEnd( source.getDateEnd() );
        creditNoteCommissionEntity.setNoPax( source.getNoPax() );
        creditNoteCommissionEntity.setSubTotalPrice( source.getSubTotalPrice() );
        creditNoteCommissionEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        creditNoteCommissionEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        creditNoteCommissionEntity.setImportIgvUsd( source.getImportIgvUsd() );
        creditNoteCommissionEntity.setImportIgvSoles( source.getImportIgvSoles() );
        creditNoteCommissionEntity.setImportTotalUsd( source.getImportTotalUsd() );
        creditNoteCommissionEntity.setImportTotalSoles( source.getImportTotalSoles() );

        creditNoteCommissionEntity.setDocumentAccountType( source.getDocumentAccountType() );
        creditNoteCommissionEntity.setDocumentAccountState( source.getDocumentAccountState() );


        creditNoteCommissionEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        creditNoteCommissionEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        creditNoteCommissionEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        creditNoteCommissionEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        creditNoteCommissionEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        creditNoteCommissionEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        creditNoteCommissionEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        creditNoteCommissionEntity.setReference(source.getReference());
        creditNoteCommissionEntity.setIdentifyDocumentFinance(source.getIdentifyDocumentFinance());
        creditNoteCommissionEntity.setDocumentAccountTypeAssociate(source.getDocumentAccountTypeAssociate());
        creditNoteCommissionEntity.setDocumentFinanceAssociate(documentFinanceConverter.toEntity(source.getDocumentFinanceAssociate()));
        creditNoteCommissionEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()));

        return creditNoteCommissionEntity;
    }


    public IdBaseDomain toDomainIdBase(CreditNoteCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CreditNoteCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CreditNoteCommissionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public CreditNoteCommissionDomain toDomainIdSimpleDomainCaseGetModificationData(CreditNoteCommissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CreditNoteCommissionDomain creditNoteCommissionDomain = new CreditNoteCommissionDomain();
        creditNoteCommissionDomain.setId( source.getId() );
        creditNoteCommissionDomain.setImportTotalUsd( source.getImportTotalUsd() );
        creditNoteCommissionDomain.setImportTotalSoles( source.getImportTotalSoles() );
        creditNoteCommissionDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        creditNoteCommissionDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        creditNoteCommissionDomain.setImportIgvUsd( source.getImportIgvUsd() );
        creditNoteCommissionDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return creditNoteCommissionDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<CreditNoteCommissionDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(CreditNoteCommissionDomain source) {
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

        if (source.getDocumentFinanceAssociate() != null) {
            target.setTypedocaffetValue(source.getDocumentFinanceAssociate().getTypeInheritanceDocumentFinance());
            target.setNrodocaffectValue(source.getIdentifyDocumentFinance());
        }

        String importTotalUsdFormat = source.getImportTotalUsdFormat();
        target.setValuestrlettertotalusdValue(importTotalUsdFormat != null ? NumberToLetterConverter.convertNumberToLetter(importTotalUsdFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<CreditNoteCommissionDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(CreditNoteCommissionDomain source) {
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

        if (source.getDocumentFinanceAssociate() != null) {
            target.setTypedocaffetValue(source.getDocumentFinanceAssociate().getTypeInheritanceDocumentFinance());
            target.setNrodocaffectValue(source.getIdentifyDocumentFinance());
        }

        String importTotalSolesFormat = source.getImportTotalSolesFormat();
        target.setValuestrlettertotalsolesValue(importTotalSolesFormat != null ? NumberToLetterConverter.convertNumberToLetterSoles(importTotalSolesFormat.replace(",", "")) : null);
        target.setNrorucValue("");
        target.setTextaditionalValue("");
        target.setDatedocaffectValue("");
        return target;
    }

    public List<CreditNoteCommissionDomain> toDomainAllSimpleToDomainCaseListCreditNoteCommission(List<CreditNoteCommissionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CreditNoteCommissionDomain creditNoteCommissionDomain = new CreditNoteCommissionDomain();
            creditNoteCommissionDomain.setId(entity.getId());
            creditNoteCommissionDomain.setIdentify(entity.getIdentify());
            creditNoteCommissionDomain.setDate(entity.getDate());
            creditNoteCommissionDomain.setDocumentAccountState(entity.getDocumentAccountState());
            creditNoteCommissionDomain.setImportTotalUsd(entity.getImportTotalUsd());
            creditNoteCommissionDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                creditNoteCommissionDomain.setCurrency(new CurrencyDomain());
                creditNoteCommissionDomain.getCurrency().setId(entity.getCurrency().getId());
                creditNoteCommissionDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                creditNoteCommissionDomain.setCustomer(new CustomerDomain());
                creditNoteCommissionDomain.getCustomer().setId(entity.getCustomer().getId());
                creditNoteCommissionDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            creditNoteCommissionDomain.setDocumentAccountTypeAssociate(entity.getDocumentAccountTypeAssociate());
            creditNoteCommissionDomain.setIdentifyDocumentFinance(entity.getIdentifyDocumentFinance());
            if (entity.getDocumentFinanceAssociate() != null && entity.getDocumentFinanceAssociate().getId() != null && !entity.getDocumentFinanceAssociate().getId().isEmpty()){
                creditNoteCommissionDomain.setDocumentFinanceAssociate(new DocumentFinanceDomain());
                creditNoteCommissionDomain.getDocumentFinanceAssociate().setId(entity.getDocumentFinanceAssociate().getId());
                creditNoteCommissionDomain.getDocumentFinanceAssociate().setTypeInheritanceDocumentFinance(entity.getDocumentFinanceAssociate().getTypeInheritanceDocumentFinance());
                creditNoteCommissionDomain.getDocumentFinanceAssociate().setIdentify(entity.getDocumentFinanceAssociate().getIdentify());
            }
            return creditNoteCommissionDomain;
        }).toList();
    }

    public PageDTO<CreditNoteCommissionDomain> toDomainPageCaseListCreditNoteCommission(Page<CreditNoteCommissionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CreditNoteCommissionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCreditNoteCommission(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
