package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proforma;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.repo.Converter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProformaConverter implements Converter<ProformaDomain, ProformaEntity> {

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
    public ProformaDomain toDomain(ProformaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaDomain proformaDomain = new ProformaDomain();

        proformaDomain.setId( source.getId() );
        proformaDomain.setDeleted( source.getDeleted() );
        proformaDomain.setEnabled( source.getEnabled() );
        proformaDomain.setMigrated( source.getMigrated() );
        proformaDomain.setCreatedAt( source.getCreatedAt() );
        proformaDomain.setUpdatedAt( source.getUpdatedAt() );
        proformaDomain.setDeletedAt( source.getDeletedAt() );
        proformaDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        proformaDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        proformaDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        proformaDomain.setIdentifierName( source.getIdentifierName() );
        proformaDomain.setTenant( source.getTenant() );
        proformaDomain.setName( source.getName() );
        proformaDomain.setDescription( source.getDescription() );
        proformaDomain.setIdentify( source.getIdentify() );

        proformaDomain.setTypeInheritanceProforma(source.getTypeInheritanceProforma());

        proformaDomain.setTitle( source.getTitle() );
        proformaDomain.setDate( source.getDate() );
        proformaDomain.setAddress( source.getAddress() );
        proformaDomain.setReference( source.getReference() );
        proformaDomain.setDateInit( source.getDateInit() );
        proformaDomain.setDateEnd( source.getDateEnd() );
        proformaDomain.setNoPax( source.getNoPax() );
        proformaDomain.setImportTotalUsd( source.getImportTotalUsd() );
        proformaDomain.setImportTotalSoles( source.getImportTotalSoles() );
        proformaDomain.setImportAmortizationUsd( source.getImportAmortizationUsd() );
        proformaDomain.setImportAmortizationSoles( source.getImportAmortizationSoles() );
        proformaDomain.setImportSaldxAmortizarUsd( source.getImportSaldxAmortizarUsd() );
        proformaDomain.setImportSaldxAmortizarSoles( source.getImportSaldxAmortizarSoles() );
        proformaDomain.setPriceUnit( source.getPriceUnit() );
        proformaDomain.setCantRegister( source.getCantRegister() );
        proformaDomain.setDateCancel( source.getDateCancel() );

        proformaDomain.setProformaType( source.getProformaType() );
        proformaDomain.setProformaState( source.getProformaState() );

        proformaDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        proformaDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        proformaDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        proformaDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        proformaDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        proformaDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        proformaDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()));

        return proformaDomain;
    }

    @Override
    public ProformaEntity toEntity(ProformaDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaEntity proformaEntity = new ProformaEntity();

        proformaEntity.setId( source.getId() );
        proformaEntity.setDeleted( source.getDeleted() );
        proformaEntity.setEnabled( source.getEnabled() );
        proformaEntity.setMigrated( source.getMigrated() );
        proformaEntity.setCreatedAt( source.getCreatedAt() );
        proformaEntity.setUpdatedAt( source.getUpdatedAt() );
        proformaEntity.setDeletedAt( source.getDeletedAt() );
        proformaEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        proformaEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        proformaEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        proformaEntity.setIdentifierName( source.getIdentifierName() );
        proformaEntity.setTenant( source.getTenant() );
        proformaEntity.setName( source.getName() );
        proformaEntity.setDescription( source.getDescription() );
        proformaEntity.setIdentify( source.getIdentify() );

        proformaEntity.setTypeInheritanceProforma(source.getTypeInheritanceProforma());

        proformaEntity.setTitle( source.getTitle() );
        proformaEntity.setDate( source.getDate() );
        proformaEntity.setAddress( source.getAddress() );
        proformaEntity.setReference( source.getReference() );
        proformaEntity.setDateInit( source.getDateInit() );
        proformaEntity.setDateEnd( source.getDateEnd() );
        proformaEntity.setNoPax( source.getNoPax() );
        proformaEntity.setImportTotalUsd( source.getImportTotalUsd() );
        proformaEntity.setImportTotalSoles( source.getImportTotalSoles() );
        proformaEntity.setImportAmortizationUsd( source.getImportAmortizationUsd() );
        proformaEntity.setImportAmortizationSoles( source.getImportAmortizationSoles() );
        proformaEntity.setImportSaldxAmortizarUsd( source.getImportSaldxAmortizarUsd() );
        proformaEntity.setImportSaldxAmortizarSoles( source.getImportSaldxAmortizarSoles() );
        proformaEntity.setPriceUnit( source.getPriceUnit() );
        proformaEntity.setCantRegister( source.getCantRegister() );
        proformaEntity.setDateCancel( source.getDateCancel() );

        proformaEntity.setProformaType( source.getProformaType() );
        proformaEntity.setProformaState( source.getProformaState() );

        proformaEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        proformaEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        proformaEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        proformaEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        proformaEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        proformaEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        proformaEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()));

        return proformaEntity;
    }

    public IdBaseDomain toDomainIdBase(ProformaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProformaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProformaEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
