package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.*;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentFinanceConverter implements Converter<DocumentFinanceDomain, DocumentFinanceEntity> {

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

    @Override
    public DocumentFinanceDomain toDomain(DocumentFinanceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentFinanceDomain documentFinanceDomain = new DocumentFinanceDomain();

        documentFinanceDomain.setId( source.getId() );
        documentFinanceDomain.setDeleted( source.getDeleted() );
        documentFinanceDomain.setEnabled( source.getEnabled() );
        documentFinanceDomain.setMigrated( source.getMigrated() );
        documentFinanceDomain.setCreatedAt( source.getCreatedAt() );
        documentFinanceDomain.setUpdatedAt( source.getUpdatedAt() );
        documentFinanceDomain.setDeletedAt( source.getDeletedAt() );
        documentFinanceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        documentFinanceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        documentFinanceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        documentFinanceDomain.setIdentifierName( source.getIdentifierName() );
        documentFinanceDomain.setTenant( source.getTenant() );
        documentFinanceDomain.setName( source.getName() );
        documentFinanceDomain.setDescription( source.getDescription() );
        documentFinanceDomain.setIdentify( source.getIdentify() );

        documentFinanceDomain.setIdentifyPrefix( source.getIdentifyPrefix() );
        documentFinanceDomain.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        documentFinanceDomain.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        documentFinanceDomain.setDate( source.getDate() );
        documentFinanceDomain.setDateCancel( source.getDateCancel() );
        documentFinanceDomain.setDateAnnulled( source.getDateAnnulled() );
        documentFinanceDomain.setAddress( source.getAddress() );
        documentFinanceDomain.setReferenceDocument( source.getReferenceDocument() );
        documentFinanceDomain.setDateInit( source.getDateInit() );
        documentFinanceDomain.setDateEnd( source.getDateEnd() );
        documentFinanceDomain.setNoPax( source.getNoPax() );
        documentFinanceDomain.setSubTotalPrice( source.getSubTotalPrice() );
        documentFinanceDomain.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        documentFinanceDomain.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        documentFinanceDomain.setImportIgvUsd( source.getImportIgvUsd() );
        documentFinanceDomain.setImportIgvSoles( source.getImportIgvSoles() );
        documentFinanceDomain.setImportTotalUsd( source.getImportTotalUsd() );
        documentFinanceDomain.setImportTotalSoles( source.getImportTotalSoles() );

        documentFinanceDomain.setDocumentAccountType( source.getDocumentAccountType() );
        documentFinanceDomain.setDocumentAccountState( source.getDocumentAccountState() );


        documentFinanceDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        documentFinanceDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        documentFinanceDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        documentFinanceDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        documentFinanceDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        documentFinanceDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        documentFinanceDomain.setResponsible(employeeConverter.toDomain(source.getResponsible()));

        return documentFinanceDomain;
    }

    @Override
    public DocumentFinanceEntity toEntity(DocumentFinanceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentFinanceEntity documentFinanceEntity = new DocumentFinanceEntity();

        documentFinanceEntity.setId( source.getId() );
        documentFinanceEntity.setDeleted( source.getDeleted() );
        documentFinanceEntity.setEnabled( source.getEnabled() );
        documentFinanceEntity.setMigrated( source.getMigrated() );
        documentFinanceEntity.setCreatedAt( source.getCreatedAt() );
        documentFinanceEntity.setUpdatedAt( source.getUpdatedAt() );
        documentFinanceEntity.setDeletedAt( source.getDeletedAt() );
        documentFinanceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        documentFinanceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        documentFinanceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        documentFinanceEntity.setIdentifierName( source.getIdentifierName() );
        documentFinanceEntity.setTenant( source.getTenant() );
        documentFinanceEntity.setName( source.getName() );
        documentFinanceEntity.setDescription( source.getDescription() );
        documentFinanceEntity.setIdentify( source.getIdentify() );

        documentFinanceEntity.setIdentifyPrefix( source.getIdentifyPrefix() );
        documentFinanceEntity.setIdentifyConsecutive( source.getIdentifyConsecutive() );

        documentFinanceEntity.setTypeInheritanceDocumentFinance(source.getTypeInheritanceDocumentFinance());

        documentFinanceEntity.setDate( source.getDate() );
        documentFinanceEntity.setDateCancel( source.getDateCancel() );
        documentFinanceEntity.setDateAnnulled( source.getDateAnnulled() );
        documentFinanceEntity.setAddress( source.getAddress() );
        documentFinanceEntity.setReferenceDocument( source.getReferenceDocument() );
        documentFinanceEntity.setDateInit( source.getDateInit() );
        documentFinanceEntity.setDateEnd( source.getDateEnd() );
        documentFinanceEntity.setNoPax( source.getNoPax() );
        documentFinanceEntity.setSubTotalPrice( source.getSubTotalPrice() );
        documentFinanceEntity.setImportSubTotalUsd( source.getImportSubTotalUsd() );
        documentFinanceEntity.setImportSubTotalSoles( source.getImportSubTotalSoles() );
        documentFinanceEntity.setImportIgvUsd( source.getImportIgvUsd() );
        documentFinanceEntity.setImportIgvSoles( source.getImportIgvSoles() );
        documentFinanceEntity.setImportTotalUsd( source.getImportTotalUsd() );
        documentFinanceEntity.setImportTotalSoles( source.getImportTotalSoles() );

        documentFinanceEntity.setDocumentAccountType( source.getDocumentAccountType() );
        documentFinanceEntity.setDocumentAccountState( source.getDocumentAccountState() );


        documentFinanceEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        documentFinanceEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        documentFinanceEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        documentFinanceEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        documentFinanceEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        documentFinanceEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        documentFinanceEntity.setResponsible(employeeConverter.toEntity(source.getResponsible()));

        return documentFinanceEntity;
    }

    public IdBaseDomain toDomainIdBase(DocumentFinanceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DocumentFinanceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DocumentFinanceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
