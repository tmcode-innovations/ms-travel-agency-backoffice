package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.LodgingCategoryEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LodgingCategoryConverter implements Converter<LodgingCategoryDomain, LodgingCategoryEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public LodgingCategoryDomain toDomain(LodgingCategoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        LodgingCategoryDomain lodgingCategoryDomain = new LodgingCategoryDomain();

        lodgingCategoryDomain.setId( source.getId() );
        lodgingCategoryDomain.setDeleted( source.getDeleted() );
        lodgingCategoryDomain.setEnabled( source.getEnabled() );
        lodgingCategoryDomain.setMigrated( source.getMigrated() );
        lodgingCategoryDomain.setCreatedAt( source.getCreatedAt() );
        lodgingCategoryDomain.setUpdatedAt( source.getUpdatedAt() );
        lodgingCategoryDomain.setDeletedAt( source.getDeletedAt() );
        lodgingCategoryDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        lodgingCategoryDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        lodgingCategoryDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        lodgingCategoryDomain.setIdentifierName( source.getIdentifierName() );
        lodgingCategoryDomain.setTenant( source.getTenant() );
        lodgingCategoryDomain.setName( source.getName() );
        lodgingCategoryDomain.setDescription( source.getDescription() );
        lodgingCategoryDomain.setIdentify( source.getIdentify() );

        return lodgingCategoryDomain;
    }

    @Override
    public LodgingCategoryEntity toEntity(LodgingCategoryDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        LodgingCategoryEntity lodgingCategoryEntity = new LodgingCategoryEntity();

        lodgingCategoryEntity.setId( source.getId() );
        lodgingCategoryEntity.setDeleted( source.getDeleted() );
        lodgingCategoryEntity.setEnabled( source.getEnabled() );
        lodgingCategoryEntity.setMigrated( source.getMigrated() );
        lodgingCategoryEntity.setCreatedAt( source.getCreatedAt() );
        lodgingCategoryEntity.setUpdatedAt( source.getUpdatedAt() );
        lodgingCategoryEntity.setDeletedAt( source.getDeletedAt() );
        lodgingCategoryEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        lodgingCategoryEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        lodgingCategoryEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        lodgingCategoryEntity.setIdentifierName( source.getIdentifierName() );
        lodgingCategoryEntity.setTenant( source.getTenant() );
        lodgingCategoryEntity.setName( source.getName() );
        lodgingCategoryEntity.setDescription( source.getDescription() );
        lodgingCategoryEntity.setIdentify( source.getIdentify() );

        return lodgingCategoryEntity;
    }

    public IdBaseDomain toDomainIdBase(LodgingCategoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(LodgingCategoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<LodgingCategoryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<LodgingCategoryDomain> toDomainAllSimpleToDomainCaseListLodgingCategory(List<LodgingCategoryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            LodgingCategoryDomain lodgingCategoryDomain = new LodgingCategoryDomain();
            lodgingCategoryDomain.setId(entity.getId());
            lodgingCategoryDomain.setIdentify(entity.getIdentify());
            lodgingCategoryDomain.setName(entity.getName());
            return lodgingCategoryDomain;
        }).toList();
    }

    public PageDTO<LodgingCategoryDomain> toDomainPageCaseListLodgingCategory(Page<LodgingCategoryEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<LodgingCategoryDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListLodgingCategory(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
