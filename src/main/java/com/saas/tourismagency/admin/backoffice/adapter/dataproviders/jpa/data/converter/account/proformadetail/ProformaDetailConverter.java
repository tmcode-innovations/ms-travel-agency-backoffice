package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformadetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proforma.ProformaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaDetailEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProformaDetailConverter implements Converter<ProformaDetailDomain, ProformaDetailEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private ProformaConverter proformaConverter;

    @Override
    public ProformaDetailDomain toDomain(ProformaDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaDetailDomain proformaDetailDomain = new ProformaDetailDomain();

        proformaDetailDomain.setId( source.getId() );
        proformaDetailDomain.setDeleted( source.getDeleted() );
        proformaDetailDomain.setEnabled( source.getEnabled() );
        proformaDetailDomain.setMigrated( source.getMigrated() );
        proformaDetailDomain.setCreatedAt( source.getCreatedAt() );
        proformaDetailDomain.setUpdatedAt( source.getUpdatedAt() );
        proformaDetailDomain.setDeletedAt( source.getDeletedAt() );
        proformaDetailDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        proformaDetailDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        proformaDetailDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        proformaDetailDomain.setIdentifierName( source.getIdentifierName() );
        proformaDetailDomain.setTenant( source.getTenant() );
        proformaDetailDomain.setName( source.getName() );
        proformaDetailDomain.setDescription( source.getDescription() );
        proformaDetailDomain.setIdentify( source.getIdentify() );

        proformaDetailDomain.setPriceUnit(source.getPriceUnit());
        proformaDetailDomain.setImportUsd(source.getImportUsd());
        proformaDetailDomain.setImportSoles(source.getImportSoles());
        proformaDetailDomain.setCant(source.getCant());
        proformaDetailDomain.setService(source.getService());
        proformaDetailDomain.setNoItem(source.getNoItem());
        proformaDetailDomain.setDateTime(source.getDateTime());

        proformaDetailDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        proformaDetailDomain.setProforma(proformaConverter.toDomain(source.getProformaEntity()));

        return proformaDetailDomain;
    }

    @Override
    public ProformaDetailEntity toEntity(ProformaDetailDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaDetailEntity proformaDetailEntity = new ProformaDetailEntity();

        proformaDetailEntity.setId( source.getId() );
        proformaDetailEntity.setDeleted( source.getDeleted() );
        proformaDetailEntity.setEnabled( source.getEnabled() );
        proformaDetailEntity.setMigrated( source.getMigrated() );
        proformaDetailEntity.setCreatedAt( source.getCreatedAt() );
        proformaDetailEntity.setUpdatedAt( source.getUpdatedAt() );
        proformaDetailEntity.setDeletedAt( source.getDeletedAt() );
        proformaDetailEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        proformaDetailEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        proformaDetailEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        proformaDetailEntity.setIdentifierName( source.getIdentifierName() );
        proformaDetailEntity.setTenant( source.getTenant() );
        proformaDetailEntity.setName( source.getName() );
        proformaDetailEntity.setDescription( source.getDescription() );
        proformaDetailEntity.setIdentify( source.getIdentify() );

        proformaDetailEntity.setPriceUnit(source.getPriceUnit());
        proformaDetailEntity.setImportUsd(source.getImportUsd());
        proformaDetailEntity.setImportSoles(source.getImportSoles());
        proformaDetailEntity.setCant(source.getCant());
        proformaDetailEntity.setService(source.getService());
        proformaDetailEntity.setNoItem(source.getNoItem());
        proformaDetailEntity.setDateTime(source.getDateTime());

        proformaDetailEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        proformaDetailEntity.setProformaEntity(proformaConverter.toEntity(source.getProforma()));

        return proformaDetailEntity;
    }

    public IdBaseDomain toDomainIdBase(ProformaDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProformaDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProformaDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProformaDetailDomain> toDomainAllSimpleToDomainCaseShowProformaDetail(List<ProformaDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProformaDetailDomain proformaDetailDomain = new ProformaDetailDomain();
            proformaDetailDomain.setId(entity.getId());

            proformaDetailDomain.setPriceUnit(entity.getPriceUnit());
            proformaDetailDomain.setImportUsd(entity.getImportUsd());
            proformaDetailDomain.setImportSoles(entity.getImportSoles());
            proformaDetailDomain.setCant(entity.getCant());
            proformaDetailDomain.setService(entity.getService());
            proformaDetailDomain.setNoItem(entity.getNoItem());
            proformaDetailDomain.setDateTime(entity.getDateTime());

            return proformaDetailDomain;
        }).toList();
    }

    public List<ProformaDetailDomain> toDomainAllSimpleToDomainCaseShowNoItem(List<ProformaDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProformaDetailDomain proformaDetailDomain = new ProformaDetailDomain();
            proformaDetailDomain.setId(entity.getId());
            proformaDetailDomain.setNoItem(entity.getNoItem());
            return proformaDetailDomain;
        }).toList();
    }
}
