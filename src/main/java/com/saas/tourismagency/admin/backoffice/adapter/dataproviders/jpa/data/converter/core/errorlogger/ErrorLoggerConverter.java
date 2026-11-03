package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.errorlogger;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.ErrorLoggerEntity;
import com.saas.tourismagency.admin.backoffice.domain.core.ErrorLoggerDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorLoggerConverter implements Converter<ErrorLoggerDomain, ErrorLoggerEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public ErrorLoggerDomain toDomain(ErrorLoggerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ErrorLoggerDomain errorLoggerDomain = new ErrorLoggerDomain();

        errorLoggerDomain.setId( source.getId() );
        errorLoggerDomain.setDeleted( source.getDeleted() );
        errorLoggerDomain.setEnabled( source.getEnabled() );
        errorLoggerDomain.setMigrated( source.getMigrated() );
        errorLoggerDomain.setCreatedAt( source.getCreatedAt() );
        errorLoggerDomain.setUpdatedAt( source.getUpdatedAt() );
        errorLoggerDomain.setDeletedAt( source.getDeletedAt() );
        errorLoggerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        errorLoggerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        errorLoggerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        errorLoggerDomain.setIdentifierName( source.getIdentifierName() );
        errorLoggerDomain.setTenant( source.getTenant() );
        errorLoggerDomain.setName( source.getName() );
        errorLoggerDomain.setDescription( source.getDescription() );

        errorLoggerDomain.setClazz( source.getClazz() );
        errorLoggerDomain.setStackTrace( source.getStackTrace() );
        errorLoggerDomain.setImportance( source.getImportance() );
        errorLoggerDomain.setMessage( source.getMessage() );
        errorLoggerDomain.setStatusCode( source.getStatusCode() );
        errorLoggerDomain.setExtraData( source.getExtraData() );

        return errorLoggerDomain;
    }

    @Override
    public ErrorLoggerEntity toEntity(ErrorLoggerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ErrorLoggerEntity errorLoggerEntity = new ErrorLoggerEntity();

        errorLoggerEntity.setId( source.getId() );
        errorLoggerEntity.setDeleted( source.getDeleted() );
        errorLoggerEntity.setEnabled( source.getEnabled() );
        errorLoggerEntity.setMigrated( source.getMigrated() );
        errorLoggerEntity.setCreatedAt( source.getCreatedAt() );
        errorLoggerEntity.setUpdatedAt( source.getUpdatedAt() );
        errorLoggerEntity.setDeletedAt( source.getDeletedAt() );
        errorLoggerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        errorLoggerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        errorLoggerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        errorLoggerEntity.setIdentifierName( source.getIdentifierName() );
        errorLoggerEntity.setTenant( source.getTenant() );
        errorLoggerEntity.setName( source.getName() );
        errorLoggerEntity.setDescription( source.getDescription() );

        errorLoggerEntity.setClazz( source.getClazz() );
        errorLoggerEntity.setStackTrace( source.getStackTrace() );
        errorLoggerEntity.setImportance( source.getImportance() );
        errorLoggerEntity.setMessage( source.getMessage() );
        errorLoggerEntity.setStatusCode( source.getStatusCode() );
        errorLoggerEntity.setExtraData( source.getExtraData() );

        return errorLoggerEntity;
    }

}
