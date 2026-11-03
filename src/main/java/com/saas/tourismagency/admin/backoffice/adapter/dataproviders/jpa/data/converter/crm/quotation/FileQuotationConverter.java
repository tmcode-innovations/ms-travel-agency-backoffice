package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileQuotationEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileQuotationDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileQuotationConverter implements Converter<FileQuotationDomain, FileQuotationEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Override
    public FileQuotationDomain toDomain(FileQuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        FileQuotationDomain fileQuotationDomain = new FileQuotationDomain();

        fileQuotationDomain.setId( source.getId() );
        fileQuotationDomain.setDeleted( source.getDeleted() );
        fileQuotationDomain.setEnabled( source.getEnabled() );
        fileQuotationDomain.setMigrated( source.getMigrated() );
        fileQuotationDomain.setCreatedAt( source.getCreatedAt() );
        fileQuotationDomain.setUpdatedAt( source.getUpdatedAt() );
        fileQuotationDomain.setDeletedAt( source.getDeletedAt() );
        fileQuotationDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        fileQuotationDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        fileQuotationDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        fileQuotationDomain.setIdentifierName( source.getIdentifierName() );
        fileQuotationDomain.setTenant( source.getTenant() );
        fileQuotationDomain.setName( source.getName() );
        fileQuotationDomain.setDescription( source.getDescription() );
        fileQuotationDomain.setIdentify( source.getIdentify() );
        fileQuotationDomain.setFileName( source.getFileName() );
        fileQuotationDomain.setExtension( source.getExtension() );
        fileQuotationDomain.setPath( source.getPath() );
        fileQuotationDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );

        return fileQuotationDomain;
    }

    @Override
    public FileQuotationEntity toEntity(FileQuotationDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        FileQuotationEntity fileQuotationEntity = new FileQuotationEntity();

        fileQuotationEntity.setId( source.getId() );
        fileQuotationEntity.setDeleted( source.getDeleted() );
        fileQuotationEntity.setEnabled( source.getEnabled() );
        fileQuotationEntity.setMigrated( source.getMigrated() );
        fileQuotationEntity.setCreatedAt( source.getCreatedAt() );
        fileQuotationEntity.setUpdatedAt( source.getUpdatedAt() );
        fileQuotationEntity.setDeletedAt( source.getDeletedAt() );
        fileQuotationEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        fileQuotationEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        fileQuotationEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        fileQuotationEntity.setIdentifierName( source.getIdentifierName() );
        fileQuotationEntity.setTenant( source.getTenant() );
        fileQuotationEntity.setName( source.getName() );
        fileQuotationEntity.setDescription( source.getDescription() );
        fileQuotationEntity.setIdentify( source.getIdentify() );
        fileQuotationEntity.setFileName( source.getFileName() );
        fileQuotationEntity.setExtension( source.getExtension() );
        fileQuotationEntity.setPath( source.getPath() );
        fileQuotationEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );

        return fileQuotationEntity;
    }

    public IdBaseDomain toDomainIdBase(FileQuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(FileQuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<FileQuotationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
