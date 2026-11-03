package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.touritem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tourdate.TourDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourItemConverter implements Converter<TourItemDomain, TourItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private TourDateConverter tourDateConverter;

    @Override
    public TourItemDomain toDomain(TourItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TourItemDomain tourItemDomain = new TourItemDomain();

        tourItemDomain.setId( source.getId() );
        tourItemDomain.setDeleted( source.getDeleted() );
        tourItemDomain.setEnabled( source.getEnabled() );
        tourItemDomain.setMigrated( source.getMigrated() );
        tourItemDomain.setCreatedAt( source.getCreatedAt() );
        tourItemDomain.setUpdatedAt( source.getUpdatedAt() );
        tourItemDomain.setDeletedAt( source.getDeletedAt() );
        tourItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        tourItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        tourItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        tourItemDomain.setIdentifierName( source.getIdentifierName() );
        tourItemDomain.setTenant( source.getTenant() );
        tourItemDomain.setName( source.getName() );
        tourItemDomain.setDescription( source.getDescription() );
        tourItemDomain.setIdentify( source.getIdentify() );
        tourItemDomain.setObservation( source.getObservation() );
        tourItemDomain.setIndex( source.getIndex() );
        tourItemDomain.setTourDate( tourDateConverter.toDomain( source.getTourDate() ) );
        tourItemDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        tourItemDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        tourItemDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        tourItemDomain.setService( serviceConverter.toDomain( source.getService() ) );
        tourItemDomain.setServiceGroup( source.getServiceGroup() );
        return tourItemDomain;
    }

    @Override
    public TourItemEntity toEntity(TourItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TourItemEntity tourItemEntity = new TourItemEntity();

        tourItemEntity.setId( source.getId() );
        tourItemEntity.setDeleted( source.getDeleted() );
        tourItemEntity.setEnabled( source.getEnabled() );
        tourItemEntity.setMigrated( source.getMigrated() );
        tourItemEntity.setCreatedAt( source.getCreatedAt() );
        tourItemEntity.setUpdatedAt( source.getUpdatedAt() );
        tourItemEntity.setDeletedAt( source.getDeletedAt() );
        tourItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        tourItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        tourItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        tourItemEntity.setIdentifierName( source.getIdentifierName() );
        tourItemEntity.setTenant( source.getTenant() );
        tourItemEntity.setName( source.getName() );
        tourItemEntity.setDescription( source.getDescription() );
        tourItemEntity.setIdentify( source.getIdentify() );
        tourItemEntity.setObservation( source.getObservation() );
        tourItemEntity.setIndex( source.getIndex() );
        tourItemEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        tourItemEntity.setTourDate( tourDateConverter.toEntity( source.getTourDate() ) );
        tourItemEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        tourItemEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        tourItemEntity.setService( serviceConverter.toEntity( source.getService() ) );
        tourItemEntity.setServiceGroup( source.getServiceGroup() );

        return tourItemEntity;
    }

    public IdBaseDomain toDomainIdBase(TourItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(TourItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<TourItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
