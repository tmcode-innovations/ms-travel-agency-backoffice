package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileItineraryEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileItineraryConverter implements Converter<FileItineraryDomain, FileItineraryEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Override
    public FileItineraryDomain toDomain(FileItineraryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        FileItineraryDomain fileItineraryDomain = new FileItineraryDomain();

        fileItineraryDomain.setId( source.getId() );
        fileItineraryDomain.setDeleted( source.getDeleted() );
        fileItineraryDomain.setEnabled( source.getEnabled() );
        fileItineraryDomain.setMigrated( source.getMigrated() );
        fileItineraryDomain.setCreatedAt( source.getCreatedAt() );
        fileItineraryDomain.setUpdatedAt( source.getUpdatedAt() );
        fileItineraryDomain.setDeletedAt( source.getDeletedAt() );
        fileItineraryDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        fileItineraryDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        fileItineraryDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        fileItineraryDomain.setIdentifierName( source.getIdentifierName() );
        fileItineraryDomain.setTenant( source.getTenant() );
        fileItineraryDomain.setName( source.getName() );
        fileItineraryDomain.setDescription( source.getDescription() );
        fileItineraryDomain.setIdentify( source.getIdentify() );
        fileItineraryDomain.setFileName( source.getFileName() );
        fileItineraryDomain.setExtension( source.getExtension() );
        fileItineraryDomain.setPath( source.getPath() );
        fileItineraryDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );

        return fileItineraryDomain;
    }

    @Override
    public FileItineraryEntity toEntity(FileItineraryDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        FileItineraryEntity fileItineraryEntity = new FileItineraryEntity();

        fileItineraryEntity.setId( source.getId() );
        fileItineraryEntity.setDeleted( source.getDeleted() );
        fileItineraryEntity.setEnabled( source.getEnabled() );
        fileItineraryEntity.setMigrated( source.getMigrated() );
        fileItineraryEntity.setCreatedAt( source.getCreatedAt() );
        fileItineraryEntity.setUpdatedAt( source.getUpdatedAt() );
        fileItineraryEntity.setDeletedAt( source.getDeletedAt() );
        fileItineraryEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        fileItineraryEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        fileItineraryEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        fileItineraryEntity.setIdentifierName( source.getIdentifierName() );
        fileItineraryEntity.setTenant( source.getTenant() );
        fileItineraryEntity.setName( source.getName() );
        fileItineraryEntity.setDescription( source.getDescription() );
        fileItineraryEntity.setIdentify( source.getIdentify() );
        fileItineraryEntity.setFileName( source.getFileName() );
        fileItineraryEntity.setExtension( source.getExtension() );
        fileItineraryEntity.setPath( source.getPath() );
        fileItineraryEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );

        return fileItineraryEntity;
    }

    public IdBaseDomain toDomainIdBase(FileItineraryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(FileItineraryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<FileItineraryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
