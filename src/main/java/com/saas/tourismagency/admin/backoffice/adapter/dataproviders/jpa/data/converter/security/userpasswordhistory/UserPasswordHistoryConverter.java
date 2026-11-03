package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpasswordhistory;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordHistoryEntity;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordHistoryConverter implements Converter<UserPasswordHistoryDomain, UserPasswordHistoryEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserPasswordHistoryDomain toDomain(UserPasswordHistoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        UserPasswordHistoryDomain userPasswordHistoryDomain = new UserPasswordHistoryDomain();

        userPasswordHistoryDomain.setId( source.getId() );
        userPasswordHistoryDomain.setDeleted( source.getDeleted() );
        userPasswordHistoryDomain.setEnabled( source.getEnabled() );
        userPasswordHistoryDomain.setMigrated( source.getMigrated() );
        userPasswordHistoryDomain.setCreatedAt( source.getCreatedAt() );
        userPasswordHistoryDomain.setUpdatedAt( source.getUpdatedAt() );
        userPasswordHistoryDomain.setDeletedAt( source.getDeletedAt() );
        userPasswordHistoryDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        userPasswordHistoryDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        userPasswordHistoryDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        userPasswordHistoryDomain.setIdentifierName( source.getIdentifierName() );
        userPasswordHistoryDomain.setTenant( source.getTenant() );
        userPasswordHistoryDomain.setSalt( source.getSalt() );
        userPasswordHistoryDomain.setPasswordHash( source.getPasswordHash() );
        userPasswordHistoryDomain.setUser( userConverter.toDomain( source.getUser() ) );

        return userPasswordHistoryDomain;
    }

    @Override
    public UserPasswordHistoryEntity toEntity(UserPasswordHistoryDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        UserPasswordHistoryEntity userPasswordHistoryEntity = new UserPasswordHistoryEntity();

        userPasswordHistoryEntity.setId( source.getId() );
        userPasswordHistoryEntity.setDeleted( source.getDeleted() );
        userPasswordHistoryEntity.setEnabled( source.getEnabled() );
        userPasswordHistoryEntity.setMigrated( source.getMigrated() );
        userPasswordHistoryEntity.setCreatedAt( source.getCreatedAt() );
        userPasswordHistoryEntity.setUpdatedAt( source.getUpdatedAt() );
        userPasswordHistoryEntity.setDeletedAt( source.getDeletedAt() );
        userPasswordHistoryEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        userPasswordHistoryEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        userPasswordHistoryEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        userPasswordHistoryEntity.setIdentifierName( source.getIdentifierName() );
        userPasswordHistoryEntity.setTenant( source.getTenant() );
        userPasswordHistoryEntity.setSalt( source.getSalt() );
        userPasswordHistoryEntity.setPasswordHash( source.getPasswordHash() );
        userPasswordHistoryEntity.setUser( userConverter.toEntity( source.getUser() ) );

        return userPasswordHistoryEntity;
    }

}
