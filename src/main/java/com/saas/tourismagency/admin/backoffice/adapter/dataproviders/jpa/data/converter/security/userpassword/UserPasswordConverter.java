package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpassword;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordEntity;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordConverter implements Converter<UserPasswordDomain, UserPasswordEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserPasswordDomain toDomain(UserPasswordEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        UserPasswordDomain userPasswordDomain = new UserPasswordDomain();

        userPasswordDomain.setId( source.getId() );
        userPasswordDomain.setDeleted( source.getDeleted() );
        userPasswordDomain.setEnabled( source.getEnabled() );
        userPasswordDomain.setMigrated( source.getMigrated() );
        userPasswordDomain.setCreatedAt( source.getCreatedAt() );
        userPasswordDomain.setUpdatedAt( source.getUpdatedAt() );
        userPasswordDomain.setDeletedAt( source.getDeletedAt() );
        userPasswordDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        userPasswordDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        userPasswordDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        userPasswordDomain.setIdentifierName( source.getIdentifierName() );
        userPasswordDomain.setTenant( source.getTenant() );
        userPasswordDomain.setSalt( source.getSalt() );
        userPasswordDomain.setPasswordHash( source.getPasswordHash() );
        userPasswordDomain.setUser( userConverter.toDomain( source.getUser() ) );

        return userPasswordDomain;
    }

    @Override
    public UserPasswordEntity toEntity(UserPasswordDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        UserPasswordEntity userPasswordEntity = new UserPasswordEntity();

        userPasswordEntity.setId( source.getId() );
        userPasswordEntity.setDeleted( source.getDeleted() );
        userPasswordEntity.setEnabled( source.getEnabled() );
        userPasswordEntity.setMigrated( source.getMigrated() );
        userPasswordEntity.setCreatedAt( source.getCreatedAt() );
        userPasswordEntity.setUpdatedAt( source.getUpdatedAt() );
        userPasswordEntity.setDeletedAt( source.getDeletedAt() );
        userPasswordEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        userPasswordEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        userPasswordEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        userPasswordEntity.setIdentifierName( source.getIdentifierName() );
        userPasswordEntity.setTenant( source.getTenant() );
        userPasswordEntity.setSalt( source.getSalt() );
        userPasswordEntity.setPasswordHash( source.getPasswordHash() );
        userPasswordEntity.setUser( userConverter.toEntity( source.getUser() ) );

        return userPasswordEntity;
    }

}
