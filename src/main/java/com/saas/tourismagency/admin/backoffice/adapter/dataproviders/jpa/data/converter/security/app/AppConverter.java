package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.app;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.AppEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppConverter implements Converter<AppDomain, AppEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public AppDomain toDomain(AppEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AppDomain appDomain = new AppDomain();

        appDomain.setId( source.getId() );
        appDomain.setDeleted( source.getDeleted() );
        appDomain.setEnabled( source.getEnabled() );
        appDomain.setMigrated( source.getMigrated() );
        appDomain.setCreatedAt( source.getCreatedAt() );
        appDomain.setUpdatedAt( source.getUpdatedAt() );
        appDomain.setDeletedAt( source.getDeletedAt() );
        appDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        appDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        appDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        appDomain.setIdentifierName( source.getIdentifierName() );
        appDomain.setTenant( source.getTenant() );
        appDomain.setName( source.getName() );
        appDomain.setDescription( source.getDescription() );
        appDomain.setIdentify( source.getIdentify() );
        appDomain.setCode( source.getCode() );

        return appDomain;
    }

    @Override
    public AppEntity toEntity(AppDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AppEntity appEntity = new AppEntity();

        appEntity.setId( source.getId() );
        appEntity.setDeleted( source.getDeleted() );
        appEntity.setEnabled( source.getEnabled() );
        appEntity.setMigrated( source.getMigrated() );
        appEntity.setCreatedAt( source.getCreatedAt() );
        appEntity.setUpdatedAt( source.getUpdatedAt() );
        appEntity.setDeletedAt( source.getDeletedAt() );
        appEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        appEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        appEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        appEntity.setIdentifierName( source.getIdentifierName() );
        appEntity.setTenant( source.getTenant() );
        appEntity.setName( source.getName() );
        appEntity.setDescription( source.getDescription() );
        appEntity.setIdentify( source.getIdentify() );
        appEntity.setCode( source.getCode() );

        return appEntity;
    }

    public IdBaseDomain toDomainIdBase(AppEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(AppEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<AppEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
