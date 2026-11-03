package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.permission;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.object.ObjectConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.profile.ProfileConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.PermissionEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionConverter implements Converter<PermissionDomain, PermissionEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ObjectConverter objectConverter;

    @Override
    public PermissionDomain toDomain(PermissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PermissionDomain permissionDomain = new PermissionDomain();

        permissionDomain.setId( source.getId() );
        permissionDomain.setDeleted( source.getDeleted() );
        permissionDomain.setEnabled( source.getEnabled() );
        permissionDomain.setMigrated( source.getMigrated() );
        permissionDomain.setCreatedAt( source.getCreatedAt() );
        permissionDomain.setUpdatedAt( source.getUpdatedAt() );
        permissionDomain.setDeletedAt( source.getDeletedAt() );
        permissionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        permissionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        permissionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        permissionDomain.setIdentifierName( source.getIdentifierName() );
        permissionDomain.setTenant( source.getTenant() );
        permissionDomain.setName( source.getName() );
        permissionDomain.setDescription( source.getDescription() );
        permissionDomain.setIdentify( source.getIdentify() );
        permissionDomain.setPermCreate( source.getPermCreate() );
        permissionDomain.setPermDelete( source.getPermDelete() );
        permissionDomain.setPermRead( source.getPermRead() );
        permissionDomain.setPermUpdate( source.getPermUpdate() );
        permissionDomain.setEntity( objectConverter.toDomain( source.getEntity() ) );
        return permissionDomain;
    }

    @Override
    public PermissionEntity toEntity(PermissionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PermissionEntity permissionEntity = new PermissionEntity();

        permissionEntity.setId( source.getId() );
        permissionEntity.setDeleted( source.getDeleted() );
        permissionEntity.setEnabled( source.getEnabled() );
        permissionEntity.setMigrated( source.getMigrated() );
        permissionEntity.setCreatedAt( source.getCreatedAt() );
        permissionEntity.setUpdatedAt( source.getUpdatedAt() );
        permissionEntity.setDeletedAt( source.getDeletedAt() );
        permissionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        permissionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        permissionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        permissionEntity.setIdentifierName( source.getIdentifierName() );
        permissionEntity.setTenant( source.getTenant() );
        permissionEntity.setName( source.getName() );
        permissionEntity.setDescription( source.getDescription() );
        permissionEntity.setIdentify( source.getIdentify() );
        permissionEntity.setPermCreate( source.getPermCreate() );
        permissionEntity.setPermDelete( source.getPermDelete() );
        permissionEntity.setPermRead( source.getPermRead() );
        permissionEntity.setPermUpdate( source.getPermUpdate() );
        permissionEntity.setEntity( objectConverter.toEntity( source.getEntity() ) );
        return permissionEntity;
    }

    public IdBaseDomain toDomainIdBase(PermissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PermissionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PermissionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
