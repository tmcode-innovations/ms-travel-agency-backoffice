package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.creditnote;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.creditnote.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.creditnote.RptDocumentAccountUsd;
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
public class CreditNoteConverter implements Converter<CreditNoteDomain, CreditNoteEntity> {

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

    @Override
    public CreditNoteDomain toDomain(CreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CreditNoteDomain creditNoteDomain = new CreditNoteDomain();

        creditNoteDomain.setId( source.getId() );
        creditNoteDomain.setDeleted( source.getDeleted() );
        creditNoteDomain.setEnabled( source.getEnabled() );
        creditNoteDomain.setMigrated( source.getMigrated() );
        creditNoteDomain.setCreatedAt( source.getCreatedAt() );
        creditNoteDomain.setUpdatedAt( source.getUpdatedAt() );
        creditNoteDomain.setDeletedAt( source.getDeletedAt() );
        creditNoteDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        creditNoteDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        creditNoteDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        creditNoteDomain.setIdentifierName( source.getIdentifierName() );
        creditNoteDomain.setTenant( source.getTenant() );
        creditNoteDomain.setName( source.getName() );
        creditNoteDomain.setDescription( source.getDescription() );
        creditNoteDomain.setIdentify( source.getIdentify() );

        creditNoteDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        creditNoteDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        creditNoteDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        creditNoteDomain.setDate( source.getDate() );
        creditNoteDomain.setDateCancel( source.getDateCancel() );
        creditNoteDomain.setDateAnnulled( source.getDateAnnulled() );
        creditNoteDomain.setAddress( source.getAddress() );
        creditNoteDomain.setReferenceDocument( source.getReferenceDocument() );
        creditNoteDomain.setDateInit( source.getDateInit() );
        creditNoteDomain.setDateEnd( source.getDateEnd() );
        creditNoteDomain.setNoPax( source.getNoPax() );
        creditNoteDomain.setSubTotalPrice( source.getSubTotalPrice() );
        creditNoteDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        creditNoteDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        creditNoteDomain.setImportIgvUsd( source.getImportIgvUsd() );
        creditNoteDomain.setImportIgvSoles( source.getImportIgvSoles() );
        creditNoteDomain.setImportTotalUsd( source.getImportTotalUsd() );
        creditNoteDomain.setImportTotalSoles( source.getImportTotalSoles() );

        creditNoteDomain.setDocumentAccountType( source.getDocumentAccountType() );
        creditNoteDomain.setDocumentAccountState( source.getDocumentAccountState() );


        creditNoteDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        creditNoteDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        creditNoteDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        creditNoteDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        creditNoteDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        creditNoteDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        creditNoteDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        creditNoteDomain.setIdentifyDocumentFinance(source.getIdentifyDocumentFinance());
        creditNoteDomain.setDocumentAccountTypeAssociate(source.getDocumentAccountTypeAssociate());
        creditNoteDomain.setDocumentFinanceAssociate(documentFinanceConverter.toDomain(source.getDocumentFinanceAssociate()));

        return creditNoteDomain;
    }

    @Override
    public CreditNoteEntity toEntity(CreditNoteDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CreditNoteEntity creditNoteEntity = new CreditNoteEntity();

        creditNoteEntity.setId( source.getId() );
        creditNoteEntity.setDeleted( source.getDeleted() );
        creditNoteEntity.setEnabled( source.getEnabled() );
        creditNoteEntity.setMigrated( source.getMigrated() );
        creditNoteEntity.setCreatedAt( source.getCreatedAt() );
        creditNoteEntity.setUpdatedAt( source.getUpdatedAt() );
        creditNoteEntity.setDeletedAt( source.getDeletedAt() );
        creditNoteEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        creditNoteEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        creditNoteEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        creditNoteEntity.setIdentifierName( source.getIdentifierName() );
        creditNoteEntity.setTenant( source.getTenant() );
        creditNoteEntity.setName( source.getName() );
        creditNoteEntity.setDescription( source.getDescription() );
        creditNoteEntity.setIdentify( source.getIdentify() );

        creditNoteEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        creditNoteEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        creditNoteEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        creditNoteEntity.setDate( source.getDate() );
        creditNoteEntity.setDateCancel( source.getDateCancel() );
        creditNoteEntity.setDateAnnulled( source.getDateAnnulled() );
        creditNoteEntity.setAddress( source.getAddress() );
        creditNoteEntity.setReferenceDocument( source.getReferenceDocument() );
        creditNoteEntity.setDateInit( source.getDateInit() );
        creditNoteEntity.setDateEnd( source.getDateEnd() );
        creditNoteEntity.setNoPax( source.getNoPax() );
        creditNoteEntity.setSubTotalPrice( source.getSubTotalPrice() );
        creditNoteEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        creditNoteEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        creditNoteEntity.setImportIgvUsd( source.getImportIgvUsd() );
        creditNoteEntity.setImportIgvSoles( source.getImportIgvSoles() );
        creditNoteEntity.setImportTotalUsd( source.getImportTotalUsd() );
        creditNoteEntity.setImportTotalSoles( source.getImportTotalSoles() );

        creditNoteEntity.setDocumentAccountType( source.getDocumentAccountType() );
        creditNoteEntity.setDocumentAccountState( source.getDocumentAccountState() );

        creditNoteEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        creditNoteEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        creditNoteEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        creditNoteEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        creditNoteEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        creditNoteEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        creditNoteEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        creditNoteEntity.setIdentifyDocumentFinance(source.getIdentifyDocumentFinance());
        creditNoteEntity.setDocumentAccountTypeAssociate(source.getDocumentAccountTypeAssociate());
        creditNoteEntity.setDocumentFinanceAssociate(documentFinanceConverter.toEntity(source.getDocumentFinanceAssociate()));

        return creditNoteEntity;
    }

    public IdBaseDomain toDomainIdBase(CreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CreditNoteEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public CreditNoteDomain toDomainIdSimpleDomainCaseGetModificationData(CreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CreditNoteDomain creditNoteDomain = new CreditNoteDomain();
        creditNoteDomain.setId( source.getId() );
        creditNoteDomain.setImportTotalUsd( source.getImportTotalUsd() );
        creditNoteDomain.setImportTotalSoles( source.getImportTotalSoles() );
        creditNoteDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        creditNoteDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        creditNoteDomain.setImportIgvUsd( source.getImportIgvUsd() );
        creditNoteDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return creditNoteDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<CreditNoteDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(CreditNoteDomain source) {
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

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<CreditNoteDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(CreditNoteDomain source) {
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

    public List<CreditNoteDomain> toDomainAllSimpleToDomainCaseListCreditNote(List<CreditNoteEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CreditNoteDomain creditNoteDomain = new CreditNoteDomain();
            creditNoteDomain.setId(entity.getId());
            creditNoteDomain.setIdentify(entity.getIdentify());
            creditNoteDomain.setDate(entity.getDate());
            creditNoteDomain.setDocumentAccountState(entity.getDocumentAccountState());
            creditNoteDomain.setImportTotalUsd(entity.getImportTotalUsd());
            creditNoteDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                creditNoteDomain.setCurrency(new CurrencyDomain());
                creditNoteDomain.getCurrency().setId(entity.getCurrency().getId());
                creditNoteDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                creditNoteDomain.setCustomer(new CustomerDomain());
                creditNoteDomain.getCustomer().setId(entity.getCustomer().getId());
                creditNoteDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                creditNoteDomain.setRegisterGroup(new RegisterGroupDomain());
                creditNoteDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                creditNoteDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                creditNoteDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            creditNoteDomain.setDocumentAccountTypeAssociate(entity.getDocumentAccountTypeAssociate());
            creditNoteDomain.setIdentifyDocumentFinance(entity.getIdentifyDocumentFinance());
            if (entity.getDocumentFinanceAssociate() != null && entity.getDocumentFinanceAssociate().getId() != null && !entity.getDocumentFinanceAssociate().getId().isEmpty()){
                creditNoteDomain.setDocumentFinanceAssociate(new DocumentFinanceDomain());
                creditNoteDomain.getDocumentFinanceAssociate().setId(entity.getDocumentFinanceAssociate().getId());
                creditNoteDomain.getDocumentFinanceAssociate().setTypeInheritanceDocumentFinance(entity.getDocumentFinanceAssociate().getTypeInheritanceDocumentFinance());
                creditNoteDomain.getDocumentFinanceAssociate().setIdentify(entity.getDocumentFinanceAssociate().getIdentify());
            }
            return creditNoteDomain;
        }).toList();
    }

    public PageDTO<CreditNoteDomain> toDomainPageCaseListCreditNote(Page<CreditNoteEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CreditNoteDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCreditNote(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
