package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documentaccounttype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentAccountTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentAccountTypeConverter implements Converter<DocumentAccountTypeDomain, DocumentAccountTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public DocumentAccountTypeDomain toDomain(DocumentAccountTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentAccountTypeDomain documentAccountTypeDomain = new DocumentAccountTypeDomain();

        documentAccountTypeDomain.setId( source.getId() );
        documentAccountTypeDomain.setDeleted( source.getDeleted() );
        documentAccountTypeDomain.setEnabled( source.getEnabled() );
        documentAccountTypeDomain.setMigrated( source.getMigrated() );
        documentAccountTypeDomain.setCreatedAt( source.getCreatedAt() );
        documentAccountTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        documentAccountTypeDomain.setDeletedAt( source.getDeletedAt() );
        documentAccountTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        documentAccountTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        documentAccountTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        documentAccountTypeDomain.setIdentifierName( source.getIdentifierName() );
        documentAccountTypeDomain.setTenant( source.getTenant() );
        documentAccountTypeDomain.setName( source.getName() );
        documentAccountTypeDomain.setDescription( source.getDescription() );
        documentAccountTypeDomain.setIdentify( source.getIdentify() );
        documentAccountTypeDomain.setAbbrev( source.getAbbrev() );
        documentAccountTypeDomain.setDocumentAccountType( source.getDocumentAccountType() );
        return documentAccountTypeDomain;
    }

    @Override
    public DocumentAccountTypeEntity toEntity(DocumentAccountTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentAccountTypeEntity documentAccountTypeEntity = new DocumentAccountTypeEntity();

        documentAccountTypeEntity.setId( source.getId() );
        documentAccountTypeEntity.setDeleted( source.getDeleted() );
        documentAccountTypeEntity.setEnabled( source.getEnabled() );
        documentAccountTypeEntity.setMigrated( source.getMigrated() );
        documentAccountTypeEntity.setCreatedAt( source.getCreatedAt() );
        documentAccountTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        documentAccountTypeEntity.setDeletedAt( source.getDeletedAt() );
        documentAccountTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        documentAccountTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        documentAccountTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        documentAccountTypeEntity.setIdentifierName( source.getIdentifierName() );
        documentAccountTypeEntity.setTenant( source.getTenant() );
        documentAccountTypeEntity.setName( source.getName() );
        documentAccountTypeEntity.setDescription( source.getDescription() );
        documentAccountTypeEntity.setIdentify( source.getIdentify() );
        documentAccountTypeEntity.setAbbrev( source.getAbbrev() );
        documentAccountTypeEntity.setDocumentAccountType( source.getDocumentAccountType() );

        return documentAccountTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(DocumentAccountTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DocumentAccountTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DocumentAccountTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DocumentAccountTypeDomain> toDomainAllSimpleToDomainCaseListDocumentAccountType(List<DocumentAccountTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DocumentAccountTypeDomain documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(entity.getId());
            documentAccountTypeDomain.setIdentify(entity.getIdentify());
            documentAccountTypeDomain.setName(entity.getName());
            return documentAccountTypeDomain;
        }).toList();
    }

    public PageDTO<DocumentAccountTypeDomain> toDomainPageCaseListDocumentAccountType(Page<DocumentAccountTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DocumentAccountTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDocumentAccountType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
