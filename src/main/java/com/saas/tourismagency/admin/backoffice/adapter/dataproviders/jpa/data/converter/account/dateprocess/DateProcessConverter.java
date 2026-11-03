package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DateProcessEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateProcessConverter implements Converter<DateProcessDomain, DateProcessEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public DateProcessDomain toDomain(DateProcessEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DateProcessDomain dateProcessDomain = new DateProcessDomain();

        dateProcessDomain.setId( source.getId() );
        dateProcessDomain.setDeleted( source.getDeleted() );
        dateProcessDomain.setEnabled( source.getEnabled() );
        dateProcessDomain.setMigrated( source.getMigrated() );
        dateProcessDomain.setCreatedAt( source.getCreatedAt() );
        dateProcessDomain.setUpdatedAt( source.getUpdatedAt() );
        dateProcessDomain.setDeletedAt( source.getDeletedAt() );
        dateProcessDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        dateProcessDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        dateProcessDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        dateProcessDomain.setIdentifierName( source.getIdentifierName() );
        dateProcessDomain.setTenant( source.getTenant() );
        dateProcessDomain.setName( source.getName() );
        dateProcessDomain.setDescription( source.getDescription() );
        dateProcessDomain.setIdentify( source.getIdentify() );
        dateProcessDomain.setDate( source.getDate() );
        return dateProcessDomain;
    }

    @Override
    public DateProcessEntity toEntity(DateProcessDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DateProcessEntity dateProcessEntity = new DateProcessEntity();

        dateProcessEntity.setId( source.getId() );
        dateProcessEntity.setDeleted( source.getDeleted() );
        dateProcessEntity.setEnabled( source.getEnabled() );
        dateProcessEntity.setMigrated( source.getMigrated() );
        dateProcessEntity.setCreatedAt( source.getCreatedAt() );
        dateProcessEntity.setUpdatedAt( source.getUpdatedAt() );
        dateProcessEntity.setDeletedAt( source.getDeletedAt() );
        dateProcessEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        dateProcessEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        dateProcessEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        dateProcessEntity.setIdentifierName( source.getIdentifierName() );
        dateProcessEntity.setTenant( source.getTenant() );
        dateProcessEntity.setName( source.getName() );
        dateProcessEntity.setDescription( source.getDescription() );
        dateProcessEntity.setIdentify( source.getIdentify() );
        dateProcessEntity.setDate( source.getDate() );
        return dateProcessEntity;
    }

    public IdBaseDomain toDomainIdBase(DateProcessEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DateProcessEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DateProcessEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DateProcessDomain> toDomainAllSimpleToDomainCaseListEntity(List<DateProcessEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DateProcessDomain dateProcessDomain = new DateProcessDomain();

            return dateProcessDomain;
        }).toList();
    }

    public List<DateProcessDomain> toDomainAllSimpleToDomainCaseShowDateProcess(List<DateProcessEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DateProcessDomain dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(entity.getId());
            dateProcessDomain.setName(entity.getName());
            dateProcessDomain.setDescription(entity.getDescription());
            return dateProcessDomain;
        }).toList();
    }

    public List<DateProcessDomain> toDomainAllSimpleToDomainCaseListDateProcess(List<DateProcessEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DateProcessDomain dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(entity.getId());
            dateProcessDomain.setName(entity.getName());
            dateProcessDomain.setDescription(entity.getDescription());
            return dateProcessDomain;
        }).toList();
    }

    public PageDTO<DateProcessDomain> toDomainPageCaseListDateProcess(Page<DateProcessEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DateProcessDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDateProcess(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
