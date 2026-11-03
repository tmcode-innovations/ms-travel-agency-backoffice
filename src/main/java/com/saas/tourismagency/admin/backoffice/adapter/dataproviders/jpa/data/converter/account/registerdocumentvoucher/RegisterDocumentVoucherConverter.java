package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.registerdocumentvoucher;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.RegisterDocumentVoucherEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterDocumentVoucherConverter implements Converter<RegisterDocumentVoucherDomain, RegisterDocumentVoucherEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public RegisterDocumentVoucherDomain toDomain(RegisterDocumentVoucherEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterDocumentVoucherDomain registerDocumentVoucherDomain = new RegisterDocumentVoucherDomain();

        registerDocumentVoucherDomain.setId( source.getId() );
        registerDocumentVoucherDomain.setDeleted( source.getDeleted() );
        registerDocumentVoucherDomain.setEnabled( source.getEnabled() );
        registerDocumentVoucherDomain.setMigrated( source.getMigrated() );
        registerDocumentVoucherDomain.setCreatedAt( source.getCreatedAt() );
        registerDocumentVoucherDomain.setUpdatedAt( source.getUpdatedAt() );
        registerDocumentVoucherDomain.setDeletedAt( source.getDeletedAt() );
        registerDocumentVoucherDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerDocumentVoucherDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerDocumentVoucherDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerDocumentVoucherDomain.setIdentifierName( source.getIdentifierName() );
        registerDocumentVoucherDomain.setTenant( source.getTenant() );
        registerDocumentVoucherDomain.setName( source.getName() );
        registerDocumentVoucherDomain.setDescription( source.getDescription() );
        registerDocumentVoucherDomain.setIdentify( source.getIdentify() );

        return registerDocumentVoucherDomain;
    }

    @Override
    public RegisterDocumentVoucherEntity toEntity(RegisterDocumentVoucherDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterDocumentVoucherEntity registerDocumentVoucherEntity = new RegisterDocumentVoucherEntity();

        registerDocumentVoucherEntity.setId( source.getId() );
        registerDocumentVoucherEntity.setDeleted( source.getDeleted() );
        registerDocumentVoucherEntity.setEnabled( source.getEnabled() );
        registerDocumentVoucherEntity.setMigrated( source.getMigrated() );
        registerDocumentVoucherEntity.setCreatedAt( source.getCreatedAt() );
        registerDocumentVoucherEntity.setUpdatedAt( source.getUpdatedAt() );
        registerDocumentVoucherEntity.setDeletedAt( source.getDeletedAt() );
        registerDocumentVoucherEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerDocumentVoucherEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerDocumentVoucherEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerDocumentVoucherEntity.setIdentifierName( source.getIdentifierName() );
        registerDocumentVoucherEntity.setTenant( source.getTenant() );
        registerDocumentVoucherEntity.setName( source.getName() );
        registerDocumentVoucherEntity.setDescription( source.getDescription() );
        registerDocumentVoucherEntity.setIdentify( source.getIdentify() );

        return registerDocumentVoucherEntity;
    }

}
