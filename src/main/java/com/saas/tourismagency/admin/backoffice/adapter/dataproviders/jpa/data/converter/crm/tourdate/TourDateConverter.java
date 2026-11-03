package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tourdate;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourDateEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourDateConverter implements Converter<TourDateDomain, TourDateEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TourConverter tourConverter;

    @Override
    public TourDateDomain toDomain(TourDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TourDateDomain tourDateDomain = new TourDateDomain();

        tourDateDomain.setId( source.getId() );
        tourDateDomain.setDeleted( source.getDeleted() );
        tourDateDomain.setEnabled( source.getEnabled() );
        tourDateDomain.setMigrated( source.getMigrated() );
        tourDateDomain.setCreatedAt( source.getCreatedAt() );
        tourDateDomain.setUpdatedAt( source.getUpdatedAt() );
        tourDateDomain.setDeletedAt( source.getDeletedAt() );
        tourDateDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        tourDateDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        tourDateDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        tourDateDomain.setIdentifierName( source.getIdentifierName() );
        tourDateDomain.setTenant( source.getTenant() );
        tourDateDomain.setName( source.getName() );
        tourDateDomain.setDescription( source.getDescription() );
        tourDateDomain.setIdentify( source.getIdentify() );
        tourDateDomain.setDay( source.getDay() );
        tourDateDomain.setTour( tourConverter.toDomain( source.getTour() ) );

        return tourDateDomain;
    }

    @Override
    public TourDateEntity toEntity(TourDateDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TourDateEntity tourDateEntity = new TourDateEntity();

        tourDateEntity.setId( source.getId() );
        tourDateEntity.setDeleted( source.getDeleted() );
        tourDateEntity.setEnabled( source.getEnabled() );
        tourDateEntity.setMigrated( source.getMigrated() );
        tourDateEntity.setCreatedAt( source.getCreatedAt() );
        tourDateEntity.setUpdatedAt( source.getUpdatedAt() );
        tourDateEntity.setDeletedAt( source.getDeletedAt() );
        tourDateEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        tourDateEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        tourDateEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        tourDateEntity.setIdentifierName( source.getIdentifierName() );
        tourDateEntity.setTenant( source.getTenant() );
        tourDateEntity.setName( source.getName() );
        tourDateEntity.setDescription( source.getDescription() );
        tourDateEntity.setIdentify( source.getIdentify() );
        tourDateEntity.setDay( source.getDay() );
        tourDateEntity.setTour( tourConverter.toEntity( source.getTour() ) );

        return tourDateEntity;
    }

    public IdBaseDomain toDomainIdBase(TourDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(TourDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<TourDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
