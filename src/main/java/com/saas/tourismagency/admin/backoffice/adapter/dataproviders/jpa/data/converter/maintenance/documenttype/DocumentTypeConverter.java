package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeConverter implements Converter<DocumentTypeDomain, DocumentTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public DocumentTypeDomain toDomain(DocumentTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentTypeDomain documentTypeDomain = new DocumentTypeDomain();

        documentTypeDomain.setId( source.getId() );
        documentTypeDomain.setDeleted( source.getDeleted() );
        documentTypeDomain.setEnabled( source.getEnabled() );
        documentTypeDomain.setMigrated( source.getMigrated() );
        documentTypeDomain.setCreatedAt( source.getCreatedAt() );
        documentTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        documentTypeDomain.setDeletedAt( source.getDeletedAt() );
        documentTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        documentTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        documentTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        documentTypeDomain.setIdentifierName( source.getIdentifierName() );
        documentTypeDomain.setTenant( source.getTenant() );
        documentTypeDomain.setName( source.getName() );
        documentTypeDomain.setDescription( source.getDescription() );
        documentTypeDomain.setIdentify( source.getIdentify() );

        return documentTypeDomain;
    }

    @Override
    public DocumentTypeEntity toEntity(DocumentTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentTypeEntity documentTypeEntity = new DocumentTypeEntity();

        documentTypeEntity.setId( source.getId() );
        documentTypeEntity.setDeleted( source.getDeleted() );
        documentTypeEntity.setEnabled( source.getEnabled() );
        documentTypeEntity.setMigrated( source.getMigrated() );
        documentTypeEntity.setCreatedAt( source.getCreatedAt() );
        documentTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        documentTypeEntity.setDeletedAt( source.getDeletedAt() );
        documentTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        documentTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        documentTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        documentTypeEntity.setIdentifierName( source.getIdentifierName() );
        documentTypeEntity.setTenant( source.getTenant() );
        documentTypeEntity.setName( source.getName() );
        documentTypeEntity.setDescription( source.getDescription() );
        documentTypeEntity.setIdentify( source.getIdentify() );

        return documentTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(DocumentTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DocumentTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DocumentTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DocumentTypeDomain> toDomainAllSimpleToDomainCaseListDocumentType(List<DocumentTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DocumentTypeDomain documentTypeDomain = new DocumentTypeDomain();
            documentTypeDomain.setId(entity.getId());
            documentTypeDomain.setIdentify(entity.getIdentify());
            documentTypeDomain.setName(entity.getName());
            return documentTypeDomain;
        }).toList();
    }

    public PageDTO<DocumentTypeDomain> toDomainPageCaseListDocumentType(Page<DocumentTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DocumentTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDocumentType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
