package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.organization;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.OrganizationEntity;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationConverter implements Converter<OrganizationDomain, OrganizationEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public OrganizationDomain toDomain(OrganizationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        OrganizationDomain organizationDomain = new OrganizationDomain();

        organizationDomain.setId( source.getId() );
        organizationDomain.setDeleted( source.getDeleted() );
        organizationDomain.setEnabled( source.getEnabled() );
        organizationDomain.setMigrated( source.getMigrated() );
        organizationDomain.setCreatedAt( source.getCreatedAt() );
        organizationDomain.setUpdatedAt( source.getUpdatedAt() );
        organizationDomain.setDeletedAt( source.getDeletedAt() );
        organizationDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        organizationDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        organizationDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        organizationDomain.setIdentifierName( source.getIdentifierName() );
        organizationDomain.setTenant( source.getTenant() );
        organizationDomain.setName( source.getName() );
        organizationDomain.setDescription( source.getDescription() );
        organizationDomain.setIdentify( source.getIdentify() );
        organizationDomain.setTenantId( source.getTenantId() );

        return organizationDomain;
    }

    @Override
    public OrganizationEntity toEntity(OrganizationDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        OrganizationEntity organizationEntity = new OrganizationEntity();

        organizationEntity.setId( source.getId() );
        organizationEntity.setDeleted( source.getDeleted() );
        organizationEntity.setEnabled( source.getEnabled() );
        organizationEntity.setMigrated( source.getMigrated() );
        organizationEntity.setCreatedAt( source.getCreatedAt() );
        organizationEntity.setUpdatedAt( source.getUpdatedAt() );
        organizationEntity.setDeletedAt( source.getDeletedAt() );
        organizationEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        organizationEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        organizationEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        organizationEntity.setIdentifierName( source.getIdentifierName() );
        organizationEntity.setTenant( source.getTenant() );
        organizationEntity.setName( source.getName() );
        organizationEntity.setDescription( source.getDescription() );
        organizationEntity.setIdentify( source.getIdentify() );
        organizationEntity.setTenantId( source.getTenantId() );

        return organizationEntity;
    }

}
