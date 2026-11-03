package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.igv;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IgvConverter implements Converter<IgvDomain, IgvEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public IgvDomain toDomain(IgvEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IgvDomain igvDomain = new IgvDomain();

        igvDomain.setId( source.getId() );
        igvDomain.setDeleted( source.getDeleted() );
        igvDomain.setEnabled( source.getEnabled() );
        igvDomain.setMigrated( source.getMigrated() );
        igvDomain.setCreatedAt( source.getCreatedAt() );
        igvDomain.setUpdatedAt( source.getUpdatedAt() );
        igvDomain.setDeletedAt( source.getDeletedAt() );
        igvDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        igvDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        igvDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        igvDomain.setIdentifierName( source.getIdentifierName() );
        igvDomain.setTenant( source.getTenant() );
        igvDomain.setName( source.getName() );
        igvDomain.setDescription( source.getDescription() );
        igvDomain.setAvailable( source.getAvailable() );
        igvDomain.setPercent( source.getPercent() );
        igvDomain.setDate( source.getDate() );
        igvDomain.setObservation( source.getObservation() );

        return igvDomain;
    }

    @Override
    public IgvEntity toEntity(IgvDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IgvEntity igvEntity = new IgvEntity();

        igvEntity.setId( source.getId() );
        igvEntity.setDeleted( source.getDeleted() );
        igvEntity.setEnabled( source.getEnabled() );
        igvEntity.setMigrated( source.getMigrated() );
        igvEntity.setCreatedAt( source.getCreatedAt() );
        igvEntity.setUpdatedAt( source.getUpdatedAt() );
        igvEntity.setDeletedAt( source.getDeletedAt() );
        igvEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        igvEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        igvEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        igvEntity.setIdentifierName( source.getIdentifierName() );
        igvEntity.setTenant( source.getTenant() );
        igvEntity.setName( source.getName() );
        igvEntity.setDescription( source.getDescription() );
        igvEntity.setAvailable( source.getAvailable() );
        igvEntity.setPercent( source.getPercent() );
        igvEntity.setDate( source.getDate() );
        igvEntity.setObservation( source.getObservation() );

        return igvEntity;
    }

    public IdBaseDomain toDomainIdBase(IgvEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(IgvEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<IgvEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<IgvDomain> toDomainAllSimpleToDomainCaseListIgv(List<IgvEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            IgvDomain igvDomain = new IgvDomain();
            igvDomain.setId(entity.getId());
            igvDomain.setName(entity.getName());
            igvDomain.setPercent(entity.getPercent());
            igvDomain.setAvailable(entity.getAvailable());
            return igvDomain;
        }).toList();
    }

    public PageDTO<IgvDomain> toDomainPageCaseListIgv(Page<IgvEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<IgvDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListIgv(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
