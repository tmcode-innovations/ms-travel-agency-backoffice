package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.object;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.app.AppConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ObjectEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectConverter implements Converter<ObjectDomain, ObjectEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AppConverter appConverter;

    @Override
    public ObjectDomain toDomain(ObjectEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ObjectDomain objectDomain = new ObjectDomain();

        objectDomain.setId( source.getId() );
        objectDomain.setDeleted( source.getDeleted() );
        objectDomain.setEnabled( source.getEnabled() );
        objectDomain.setMigrated( source.getMigrated() );
        objectDomain.setCreatedAt( source.getCreatedAt() );
        objectDomain.setUpdatedAt( source.getUpdatedAt() );
        objectDomain.setDeletedAt( source.getDeletedAt() );
        objectDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        objectDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        objectDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        objectDomain.setIdentifierName( source.getIdentifierName() );
        objectDomain.setTenant( source.getTenant() );
        objectDomain.setName( source.getName() );
        objectDomain.setDescription( source.getDescription() );
        objectDomain.setIdentify( source.getIdentify() );
        objectDomain.setIndex( source.getIndex() );
        objectDomain.setEntity( source.getEntity() );
        objectDomain.setDomain( source.getDomain() );
        objectDomain.setApp( appConverter.toDomain( source.getApp() ) );
        return objectDomain;
    }

    @Override
    public ObjectEntity toEntity(ObjectDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ObjectEntity objectEntity = new ObjectEntity();

        objectEntity.setId( source.getId() );
        objectEntity.setDeleted( source.getDeleted() );
        objectEntity.setEnabled( source.getEnabled() );
        objectEntity.setMigrated( source.getMigrated() );
        objectEntity.setCreatedAt( source.getCreatedAt() );
        objectEntity.setUpdatedAt( source.getUpdatedAt() );
        objectEntity.setDeletedAt( source.getDeletedAt() );
        objectEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        objectEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        objectEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        objectEntity.setIdentifierName( source.getIdentifierName() );
        objectEntity.setTenant( source.getTenant() );
        objectEntity.setName( source.getName() );
        objectEntity.setDescription( source.getDescription() );
        objectEntity.setIdentify( source.getIdentify() );
        objectEntity.setIndex( source.getIndex() );
        objectEntity.setEntity( source.getEntity() );
        objectEntity.setDomain( source.getDomain() );
        objectEntity.setApp( appConverter.toEntity( source.getApp() ) );
        return objectEntity;
    }

    public IdBaseDomain toDomainIdBase(ObjectEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ObjectEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ObjectEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
