package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtourserviceitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtour.CostTourBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate.ItineraryDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.CostTourServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostTourServiceItemBookingConverter implements Converter<CostTourServiceItemDomain, CostTourServiceItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CostTourBookingConverter costTourConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private ItineraryDateConverter itineraryDateConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public CostTourServiceItemDomain toDomain(CostTourServiceItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostTourServiceItemDomain costTourServiceItemDomain = new CostTourServiceItemDomain();

        costTourServiceItemDomain.setId( source.getId() );
        costTourServiceItemDomain.setDeleted( source.getDeleted() );
        costTourServiceItemDomain.setEnabled( source.getEnabled() );
        costTourServiceItemDomain.setMigrated( source.getMigrated() );
        costTourServiceItemDomain.setCreatedAt( source.getCreatedAt() );
        costTourServiceItemDomain.setUpdatedAt( source.getUpdatedAt() );
        costTourServiceItemDomain.setDeletedAt( source.getDeletedAt() );
        costTourServiceItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        costTourServiceItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        costTourServiceItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        costTourServiceItemDomain.setIdentifierName( source.getIdentifierName() );
        costTourServiceItemDomain.setTenant( source.getTenant() );
        costTourServiceItemDomain.setName( source.getName() );
        costTourServiceItemDomain.setDescription( source.getDescription() );
        costTourServiceItemDomain.setIdentify( source.getIdentify() );
        costTourServiceItemDomain.setObservation( source.getObservation() );
        costTourServiceItemDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        costTourServiceItemDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        costTourServiceItemDomain.setItineraryDate( itineraryDateConverter.toDomain( source.getItineraryDate() ) );
        costTourServiceItemDomain.setCostTour( costTourConverter.toDomain( source.getCostTour() ) );
        costTourServiceItemDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        costTourServiceItemDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        costTourServiceItemDomain.setService( serviceConverter.toDomain( source.getService() ) );
        return costTourServiceItemDomain;
    }

    @Override
    public CostTourServiceItemEntity toEntity(CostTourServiceItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostTourServiceItemEntity costTourServiceItemEntity = new CostTourServiceItemEntity();

        costTourServiceItemEntity.setId( source.getId() );
        costTourServiceItemEntity.setDeleted( source.getDeleted() );
        costTourServiceItemEntity.setEnabled( source.getEnabled() );
        costTourServiceItemEntity.setMigrated( source.getMigrated() );
        costTourServiceItemEntity.setCreatedAt( source.getCreatedAt() );
        costTourServiceItemEntity.setUpdatedAt( source.getUpdatedAt() );
        costTourServiceItemEntity.setDeletedAt( source.getDeletedAt() );
        costTourServiceItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        costTourServiceItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        costTourServiceItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        costTourServiceItemEntity.setIdentifierName( source.getIdentifierName() );
        costTourServiceItemEntity.setTenant( source.getTenant() );
        costTourServiceItemEntity.setName( source.getName() );
        costTourServiceItemEntity.setDescription( source.getDescription() );
        costTourServiceItemEntity.setIdentify( source.getIdentify() );
        costTourServiceItemEntity.setObservation( source.getObservation() );
        costTourServiceItemEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        costTourServiceItemEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        costTourServiceItemEntity.setItineraryDate( itineraryDateConverter.toEntity( source.getItineraryDate() ) );
        costTourServiceItemEntity.setCostTour( costTourConverter.toEntity( source.getCostTour() ) );
        costTourServiceItemEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        costTourServiceItemEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        costTourServiceItemEntity.setService( serviceConverter.toEntity( source.getService() ) );
        return costTourServiceItemEntity;
    }

    public IdBaseDomain toDomainIdBase(CostTourServiceItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CostTourServiceItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CostTourServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CostTourServiceItemDomain> toDomainAllSimpleToDomainCaseShowCostTourServiceItem(List<CostTourServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourServiceItemDomain costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId(entity.getId());
            costTourServiceItemDomain.setObservation(entity.getObservation());
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                costTourServiceItemDomain.setService(new ServiceDomain());
                costTourServiceItemDomain.getService().setId(entity.getService().getId());
                costTourServiceItemDomain.getService().setName(entity.getService().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                costTourServiceItemDomain.setSupplier(new SupplierDomain());
                costTourServiceItemDomain.getSupplier().setId(entity.getSupplier().getId());
                costTourServiceItemDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                costTourServiceItemDomain.setItineraryDate(new ItineraryDateDomain());
                costTourServiceItemDomain.getItineraryDate().setId(entity.getItineraryDate().getId());
                costTourServiceItemDomain.getItineraryDate().setName(entity.getItineraryDate().getName());
                costTourServiceItemDomain.getItineraryDate().setIndex(entity.getItineraryDate().getIndex());
                costTourServiceItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                costTourServiceItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            return costTourServiceItemDomain;
        }).toList();
    }

    public List<CostTourServiceItemDomain> toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(List<CostTourServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourServiceItemDomain costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId( entity.getId() );
            return costTourServiceItemDomain;
        }).toList();
    }

    public List<CostTourServiceItemDomain> toDomainAllSimpleToDomainCaseResendFileToSaleByCostTourAndRegisterGroup(List<CostTourServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourServiceItemDomain costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId( entity.getId() );
            costTourServiceItemDomain.setName( entity.getName() );
            costTourServiceItemDomain.setDescription( entity.getDescription() );
            costTourServiceItemDomain.setIdentify( entity.getIdentify() );
            costTourServiceItemDomain.setObservation(entity.getObservation());
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                costTourServiceItemDomain.setService(new ServiceDomain());
                costTourServiceItemDomain.getService().setId(entity.getService().getId());
                costTourServiceItemDomain.getService().setName(entity.getService().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                costTourServiceItemDomain.setSupplier(new SupplierDomain());
                costTourServiceItemDomain.getSupplier().setId(entity.getSupplier().getId());
                costTourServiceItemDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                costTourServiceItemDomain.setSupplierType(new SupplierTypeDomain());
                costTourServiceItemDomain.getSupplierType().setId(entity.getSupplierType().getId());
                costTourServiceItemDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                costTourServiceItemDomain.setItineraryDate(new ItineraryDateDomain());
                costTourServiceItemDomain.getItineraryDate().setId(entity.getItineraryDate().getId());
                costTourServiceItemDomain.getItineraryDate().setName(entity.getItineraryDate().getName());
                costTourServiceItemDomain.getItineraryDate().setIndex(entity.getItineraryDate().getIndex());
                costTourServiceItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                costTourServiceItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            return costTourServiceItemDomain;
        }).toList();
    }
}
