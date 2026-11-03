package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.position;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.PositionEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionConverter implements Converter<PositionDomain, PositionEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public PositionDomain toDomain(PositionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PositionDomain positionDomain = new PositionDomain();

        positionDomain.setId( source.getId() );
        positionDomain.setDeleted( source.getDeleted() );
        positionDomain.setEnabled( source.getEnabled() );
        positionDomain.setMigrated( source.getMigrated() );
        positionDomain.setCreatedAt( source.getCreatedAt() );
        positionDomain.setUpdatedAt( source.getUpdatedAt() );
        positionDomain.setDeletedAt( source.getDeletedAt() );
        positionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        positionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        positionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        positionDomain.setIdentifierName( source.getIdentifierName() );
        positionDomain.setTenant( source.getTenant() );
        positionDomain.setName( source.getName() );
        positionDomain.setDescription( source.getDescription() );
        positionDomain.setIdentify( source.getIdentify() );

        return positionDomain;
    }

    @Override
    public PositionEntity toEntity(PositionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PositionEntity positionEntity = new PositionEntity();

        positionEntity.setId( source.getId() );
        positionEntity.setDeleted( source.getDeleted() );
        positionEntity.setEnabled( source.getEnabled() );
        positionEntity.setMigrated( source.getMigrated() );
        positionEntity.setCreatedAt( source.getCreatedAt() );
        positionEntity.setUpdatedAt( source.getUpdatedAt() );
        positionEntity.setDeletedAt( source.getDeletedAt() );
        positionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        positionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        positionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        positionEntity.setIdentifierName( source.getIdentifierName() );
        positionEntity.setTenant( source.getTenant() );
        positionEntity.setName( source.getName() );
        positionEntity.setDescription( source.getDescription() );
        positionEntity.setIdentify( source.getIdentify() );

        return positionEntity;
    }

    public IdBaseDomain toDomainIdBase(PositionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PositionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PositionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<PositionDomain> toDomainAllSimpleToDomainCaseListPosition(List<PositionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PositionDomain positionDomain = new PositionDomain();
            positionDomain.setId(entity.getId());
            positionDomain.setIdentify(entity.getIdentify());
            positionDomain.setName(entity.getName());
            return positionDomain;
        }).toList();
    }

    public PageDTO<PositionDomain> toDomainPageCaseListPosition(Page<PositionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<PositionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListPosition(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
