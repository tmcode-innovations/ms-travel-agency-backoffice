package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.debitnote;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DebitNoteEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DebitNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.debitnote.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.debitnote.RptDocumentAccountUsd;
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
public class DebitNoteConverter implements Converter<DebitNoteDomain, DebitNoteEntity> {

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
    public DebitNoteDomain toDomain(DebitNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DebitNoteDomain debitNoteDomain = new DebitNoteDomain();

        debitNoteDomain.setId( source.getId() );
        debitNoteDomain.setDeleted( source.getDeleted() );
        debitNoteDomain.setEnabled( source.getEnabled() );
        debitNoteDomain.setMigrated( source.getMigrated() );
        debitNoteDomain.setCreatedAt( source.getCreatedAt() );
        debitNoteDomain.setUpdatedAt( source.getUpdatedAt() );
        debitNoteDomain.setDeletedAt( source.getDeletedAt() );
        debitNoteDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        debitNoteDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        debitNoteDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        debitNoteDomain.setIdentifierName( source.getIdentifierName() );
        debitNoteDomain.setTenant( source.getTenant() );
        debitNoteDomain.setName( source.getName() );
        debitNoteDomain.setDescription( source.getDescription() );
        debitNoteDomain.setIdentify( source.getIdentify() );

        debitNoteDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        debitNoteDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        debitNoteDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        debitNoteDomain.setDate( source.getDate() );
        debitNoteDomain.setDateCancel( source.getDateCancel() );
        debitNoteDomain.setDateAnnulled( source.getDateAnnulled() );
        debitNoteDomain.setAddress( source.getAddress() );
        debitNoteDomain.setReferenceDocument( source.getReferenceDocument() );
        debitNoteDomain.setDateInit( source.getDateInit() );
        debitNoteDomain.setDateEnd( source.getDateEnd() );
        debitNoteDomain.setNoPax( source.getNoPax() );
        debitNoteDomain.setSubTotalPrice( source.getSubTotalPrice() );
        debitNoteDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        debitNoteDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        debitNoteDomain.setImportIgvUsd( source.getImportIgvUsd() );
        debitNoteDomain.setImportIgvSoles( source.getImportIgvSoles() );
        debitNoteDomain.setImportTotalUsd( source.getImportTotalUsd() );
        debitNoteDomain.setImportTotalSoles( source.getImportTotalSoles() );

        debitNoteDomain.setDocumentAccountType( source.getDocumentAccountType() );
        debitNoteDomain.setDocumentAccountState( source.getDocumentAccountState() );


        debitNoteDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        debitNoteDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        debitNoteDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        debitNoteDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        debitNoteDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        debitNoteDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        debitNoteDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        debitNoteDomain.setIdentifyDocumentFinance(source.getIdentifyDocumentFinance());
        debitNoteDomain.setDocumentAccountTypeAssociate(source.getDocumentAccountTypeAssociate());
        debitNoteDomain.setDocumentFinanceAssociate(documentFinanceConverter.toDomain(source.getDocumentFinanceAssociate()));

        return debitNoteDomain;
    }

    @Override
    public DebitNoteEntity toEntity(DebitNoteDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DebitNoteEntity debitNoteEntity = new DebitNoteEntity();

        debitNoteEntity.setId( source.getId() );
        debitNoteEntity.setDeleted( source.getDeleted() );
        debitNoteEntity.setEnabled( source.getEnabled() );
        debitNoteEntity.setMigrated( source.getMigrated() );
        debitNoteEntity.setCreatedAt( source.getCreatedAt() );
        debitNoteEntity.setUpdatedAt( source.getUpdatedAt() );
        debitNoteEntity.setDeletedAt( source.getDeletedAt() );
        debitNoteEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        debitNoteEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        debitNoteEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        debitNoteEntity.setIdentifierName( source.getIdentifierName() );
        debitNoteEntity.setTenant( source.getTenant() );
        debitNoteEntity.setName( source.getName() );
        debitNoteEntity.setDescription( source.getDescription() );
        debitNoteEntity.setIdentify( source.getIdentify() );

        debitNoteEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        debitNoteEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        debitNoteEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        debitNoteEntity.setDate( source.getDate() );
        debitNoteEntity.setDateCancel( source.getDateCancel() );
        debitNoteEntity.setDateAnnulled( source.getDateAnnulled() );
        debitNoteEntity.setAddress( source.getAddress() );
        debitNoteEntity.setReferenceDocument( source.getReferenceDocument() );
        debitNoteEntity.setDateInit( source.getDateInit() );
        debitNoteEntity.setDateEnd( source.getDateEnd() );
        debitNoteEntity.setNoPax( source.getNoPax() );
        debitNoteEntity.setSubTotalPrice( source.getSubTotalPrice() );
        debitNoteEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        debitNoteEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        debitNoteEntity.setImportIgvUsd( source.getImportIgvUsd() );
        debitNoteEntity.setImportIgvSoles( source.getImportIgvSoles() );
        debitNoteEntity.setImportTotalUsd( source.getImportTotalUsd() );
        debitNoteEntity.setImportTotalSoles( source.getImportTotalSoles() );

        debitNoteEntity.setDocumentAccountType( source.getDocumentAccountType() );
        debitNoteEntity.setDocumentAccountState( source.getDocumentAccountState() );

        debitNoteEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        debitNoteEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        debitNoteEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        debitNoteEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        debitNoteEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        debitNoteEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        debitNoteEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        debitNoteEntity.setIdentifyDocumentFinance(source.getIdentifyDocumentFinance());
        debitNoteEntity.setDocumentAccountTypeAssociate(source.getDocumentAccountTypeAssociate());
        debitNoteEntity.setDocumentFinanceAssociate(documentFinanceConverter.toEntity(source.getDocumentFinanceAssociate()));

        return debitNoteEntity;
    }


    public IdBaseDomain toDomainIdBase(DebitNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DebitNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DebitNoteEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public DebitNoteDomain toDomainIdSimpleDomainCaseGetModificationData(DebitNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DebitNoteDomain debitNoteDomain = new DebitNoteDomain();
        debitNoteDomain.setId( source.getId() );
        debitNoteDomain.setImportTotalUsd( source.getImportTotalUsd() );
        debitNoteDomain.setImportTotalSoles( source.getImportTotalSoles() );
        debitNoteDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        debitNoteDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        debitNoteDomain.setImportIgvUsd( source.getImportIgvUsd() );
        debitNoteDomain.setImportIgvSoles( source.getImportIgvSoles() );
        return debitNoteDomain;
    }

    public List<RptDocumentAccountUsd> toRptDocumentAccountUsdList(List<DebitNoteDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountUsd).toList();
    }

    private RptDocumentAccountUsd toRptDocumentAccountUsd(DebitNoteDomain source) {
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

    public List<RptDocumentAccountSoles> toRptDocumentAccountSolesList(List<DebitNoteDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountSoles).toList();
    }

    private RptDocumentAccountSoles toRptDocumentAccountSoles(DebitNoteDomain source) {
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

    public List<DebitNoteDomain> toDomainAllSimpleToDomainCaseListDebitNote(List<DebitNoteEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DebitNoteDomain debitNoteDomain = new DebitNoteDomain();
            debitNoteDomain.setId(entity.getId());
            debitNoteDomain.setIdentify(entity.getIdentify());
            debitNoteDomain.setDate(entity.getDate());
            debitNoteDomain.setDocumentAccountState(entity.getDocumentAccountState());
            debitNoteDomain.setImportTotalUsd(entity.getImportTotalUsd());
            debitNoteDomain.setImportTotalSoles(entity.getImportTotalSoles());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                debitNoteDomain.setCurrency(new CurrencyDomain());
                debitNoteDomain.getCurrency().setId(entity.getCurrency().getId());
                debitNoteDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                debitNoteDomain.setCustomer(new CustomerDomain());
                debitNoteDomain.getCustomer().setId(entity.getCustomer().getId());
                debitNoteDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                debitNoteDomain.setRegisterGroup(new RegisterGroupDomain());
                debitNoteDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                debitNoteDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                debitNoteDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            debitNoteDomain.setDocumentAccountTypeAssociate(entity.getDocumentAccountTypeAssociate());
            debitNoteDomain.setIdentifyDocumentFinance(entity.getIdentifyDocumentFinance());
            if (entity.getDocumentFinanceAssociate() != null && entity.getDocumentFinanceAssociate().getId() != null && !entity.getDocumentFinanceAssociate().getId().isEmpty()){
                debitNoteDomain.setDocumentFinanceAssociate(new DocumentFinanceDomain());
                debitNoteDomain.getDocumentFinanceAssociate().setId(entity.getDocumentFinanceAssociate().getId());
                debitNoteDomain.getDocumentFinanceAssociate().setTypeInheritanceDocumentFinance(entity.getDocumentFinanceAssociate().getTypeInheritanceDocumentFinance());
                debitNoteDomain.getDocumentFinanceAssociate().setIdentify(entity.getDocumentFinanceAssociate().getIdentify());
            }
            return debitNoteDomain;
        }).toList();
    }

    public PageDTO<DebitNoteDomain> toDomainPageCaseListDebitNote(Page<DebitNoteEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DebitNoteDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDebitNote(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
