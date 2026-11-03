package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SectorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SectorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorConverter implements Converter<SectorDomain, SectorEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public SectorDomain toDomain(SectorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SectorDomain sectorDomain = new SectorDomain();

        sectorDomain.setId( source.getId() );
        sectorDomain.setDeleted( source.getDeleted() );
        sectorDomain.setEnabled( source.getEnabled() );
        sectorDomain.setMigrated( source.getMigrated() );
        sectorDomain.setCreatedAt( source.getCreatedAt() );
        sectorDomain.setUpdatedAt( source.getUpdatedAt() );
        sectorDomain.setDeletedAt( source.getDeletedAt() );
        sectorDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        sectorDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        sectorDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        sectorDomain.setIdentifierName( source.getIdentifierName() );
        sectorDomain.setTenant( source.getTenant() );
        sectorDomain.setName( source.getName() );
        sectorDomain.setDescription( source.getDescription() );
        sectorDomain.setIdentify( source.getIdentify() );

        return sectorDomain;
    }

    @Override
    public SectorEntity toEntity(SectorDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SectorEntity sectorEntity = new SectorEntity();

        sectorEntity.setId( source.getId() );
        sectorEntity.setDeleted( source.getDeleted() );
        sectorEntity.setEnabled( source.getEnabled() );
        sectorEntity.setMigrated( source.getMigrated() );
        sectorEntity.setCreatedAt( source.getCreatedAt() );
        sectorEntity.setUpdatedAt( source.getUpdatedAt() );
        sectorEntity.setDeletedAt( source.getDeletedAt() );
        sectorEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        sectorEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        sectorEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        sectorEntity.setIdentifierName( source.getIdentifierName() );
        sectorEntity.setTenant( source.getTenant() );
        sectorEntity.setName( source.getName() );
        sectorEntity.setDescription( source.getDescription() );
        sectorEntity.setIdentify( source.getIdentify() );

        return sectorEntity;
    }

    public IdBaseDomain toDomainIdBase(SectorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SectorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SectorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<SectorDomain> toDomainAllSimpleToDomainCaseListSector(List<SectorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SectorDomain sectorDomain = new SectorDomain();
            sectorDomain.setId(entity.getId());
            sectorDomain.setIdentify(entity.getIdentify());
            sectorDomain.setName(entity.getName());
            return sectorDomain;
        }).toList();
    }

    public PageDTO<SectorDomain> toDomainPageCaseListSector(Page<SectorEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SectorDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSector(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
