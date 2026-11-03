package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.entry;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.EntryEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryConverter implements Converter<EntryDomain, EntryEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public EntryDomain toDomain(EntryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EntryDomain entryDomain = new EntryDomain();

        entryDomain.setId( source.getId() );
        entryDomain.setDeleted( source.getDeleted() );
        entryDomain.setEnabled( source.getEnabled() );
        entryDomain.setMigrated( source.getMigrated() );
        entryDomain.setCreatedAt( source.getCreatedAt() );
        entryDomain.setUpdatedAt( source.getUpdatedAt() );
        entryDomain.setDeletedAt( source.getDeletedAt() );
        entryDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        entryDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        entryDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        entryDomain.setIdentifierName( source.getIdentifierName() );
        entryDomain.setTenant( source.getTenant() );
        entryDomain.setName( source.getName() );
        entryDomain.setDescription( source.getDescription() );
        entryDomain.setIdentify( source.getIdentify() );
        entryDomain.setAffectIgv( source.getAffectIgv() );

        return entryDomain;
    }

    @Override
    public EntryEntity toEntity(EntryDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EntryEntity entryEntity = new EntryEntity();

        entryEntity.setId( source.getId() );
        entryEntity.setDeleted( source.getDeleted() );
        entryEntity.setEnabled( source.getEnabled() );
        entryEntity.setMigrated( source.getMigrated() );
        entryEntity.setCreatedAt( source.getCreatedAt() );
        entryEntity.setUpdatedAt( source.getUpdatedAt() );
        entryEntity.setDeletedAt( source.getDeletedAt() );
        entryEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        entryEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        entryEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        entryEntity.setIdentifierName( source.getIdentifierName() );
        entryEntity.setTenant( source.getTenant() );
        entryEntity.setName( source.getName() );
        entryEntity.setDescription( source.getDescription() );
        entryEntity.setIdentify( source.getIdentify() );
        entryEntity.setAffectIgv( source.getAffectIgv() );

        return entryEntity;
    }

    public IdBaseDomain toDomainIdBase(EntryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(EntryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<EntryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public EntryDomain toDomainSimpleDocumentFinance(EntryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EntryDomain entryDomain = new EntryDomain();

        entryDomain.setId( source.getId() );
        entryDomain.setName( source.getName() );
        entryDomain.setDescription( source.getDescription() );
        entryDomain.setAffectIgv( source.getAffectIgv() );

        return entryDomain;
    }

    public List<EntryDomain> toDomainAllSimpleDocumentFinance(List<EntryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleDocumentFinance(entity)).toList();
    }

    public List<EntryDomain> toDomainAllSimpleToDomainCaseListEntry(List<EntryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            EntryDomain entryDomain = new EntryDomain();
            entryDomain.setId(entity.getId());
            entryDomain.setIdentify(entity.getIdentify());
            entryDomain.setName(entity.getName());
            entryDomain.setAffectIgv(entity.getAffectIgv());
            return entryDomain;
        }).toList();
    }

    public PageDTO<EntryDomain> toDomainPageCaseListEntry(Page<EntryEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<EntryDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListEntry(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
