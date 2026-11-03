package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NationalityEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalityConverter implements Converter<NationalityDomain, NationalityEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public NationalityDomain toDomain(NationalityEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        NationalityDomain nationalityDomain = new NationalityDomain();

        nationalityDomain.setId( source.getId() );
        nationalityDomain.setDeleted( source.getDeleted() );
        nationalityDomain.setEnabled( source.getEnabled() );
        nationalityDomain.setMigrated( source.getMigrated() );
        nationalityDomain.setCreatedAt( source.getCreatedAt() );
        nationalityDomain.setUpdatedAt( source.getUpdatedAt() );
        nationalityDomain.setDeletedAt( source.getDeletedAt() );
        nationalityDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        nationalityDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        nationalityDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        nationalityDomain.setIdentifierName( source.getIdentifierName() );
        nationalityDomain.setTenant( source.getTenant() );
        nationalityDomain.setName( source.getName() );
        nationalityDomain.setDescription( source.getDescription() );

        return nationalityDomain;
    }

    @Override
    public NationalityEntity toEntity(NationalityDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        NationalityEntity nationalityEntity = new NationalityEntity();

        nationalityEntity.setId( source.getId() );
        nationalityEntity.setDeleted( source.getDeleted() );
        nationalityEntity.setEnabled( source.getEnabled() );
        nationalityEntity.setMigrated( source.getMigrated() );
        nationalityEntity.setCreatedAt( source.getCreatedAt() );
        nationalityEntity.setUpdatedAt( source.getUpdatedAt() );
        nationalityEntity.setDeletedAt( source.getDeletedAt() );
        nationalityEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        nationalityEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        nationalityEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        nationalityEntity.setIdentifierName( source.getIdentifierName() );
        nationalityEntity.setTenant( source.getTenant() );
        nationalityEntity.setName( source.getName() );
        nationalityEntity.setDescription( source.getDescription() );

        return nationalityEntity;
    }

    public IdBaseDomain toDomainIdBase(NationalityEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(NationalityEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<NationalityEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<NationalityDomain> toDomainAllSimpleToDomainCaseListNationality(List<NationalityEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            NationalityDomain nationalityDomain = new NationalityDomain();
            nationalityDomain.setId(entity.getId());
            nationalityDomain.setName(entity.getName());
            nationalityDomain.setDescription(entity.getDescription());
            return nationalityDomain;
        }).toList();
    }

    public PageDTO<NationalityDomain> toDomainPageCaseListNationality(Page<NationalityEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<NationalityDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListNationality(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
