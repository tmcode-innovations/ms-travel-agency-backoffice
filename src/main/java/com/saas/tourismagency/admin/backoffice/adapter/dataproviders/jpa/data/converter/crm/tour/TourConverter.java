package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourConverter implements Converter<TourDomain, TourEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public TourDomain toDomain(TourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TourDomain tourDomain = new TourDomain();

        tourDomain.setId( source.getId() );
        tourDomain.setDeleted( source.getDeleted() );
        tourDomain.setEnabled( source.getEnabled() );
        tourDomain.setMigrated( source.getMigrated() );
        tourDomain.setCreatedAt( source.getCreatedAt() );
        tourDomain.setUpdatedAt( source.getUpdatedAt() );
        tourDomain.setDeletedAt( source.getDeletedAt() );
        tourDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        tourDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        tourDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        tourDomain.setIdentifierName( source.getIdentifierName() );
        tourDomain.setTenant( source.getTenant() );
        tourDomain.setName( source.getName() );
        tourDomain.setDescription( source.getDescription() );
        tourDomain.setIdentify( source.getIdentify() );
        tourDomain.setCantDay( source.getCantDay() );
        tourDomain.setWithoutCost( source.getWithoutCost() );
        tourDomain.setUniqueDay( source.getUniqueDay() );
        return tourDomain;
    }

    @Override
    public TourEntity toEntity(TourDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TourEntity tourEntity = new TourEntity();

        tourEntity.setId( source.getId() );
        tourEntity.setDeleted( source.getDeleted() );
        tourEntity.setEnabled( source.getEnabled() );
        tourEntity.setMigrated( source.getMigrated() );
        tourEntity.setCreatedAt( source.getCreatedAt() );
        tourEntity.setUpdatedAt( source.getUpdatedAt() );
        tourEntity.setDeletedAt( source.getDeletedAt() );
        tourEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        tourEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        tourEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        tourEntity.setIdentifierName( source.getIdentifierName() );
        tourEntity.setTenant( source.getTenant() );
        tourEntity.setName( source.getName() );
        tourEntity.setDescription( source.getDescription() );
        tourEntity.setIdentify( source.getIdentify() );
        tourEntity.setCantDay( source.getCantDay() );
        tourEntity.setWithoutCost( source.getWithoutCost() );
        tourEntity.setUniqueDay( source.getUniqueDay() );
        return tourEntity;
    }

    public IdBaseDomain toDomainIdBase(TourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(TourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<TourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<TourDomain> toDomainAllSimpleToDomainCaseListTour(List<TourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            TourDomain tourDomain = new TourDomain();
            tourDomain.setId(entity.getId());
            tourDomain.setIdentify(entity.getIdentify());
            tourDomain.setName(entity.getName());
            tourDomain.setCantDay(entity.getCantDay());
            return tourDomain;
        }).toList();
    }

    public PageDTO<TourDomain> toDomainPageCaseListTour(Page<TourEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<TourDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListTour(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
