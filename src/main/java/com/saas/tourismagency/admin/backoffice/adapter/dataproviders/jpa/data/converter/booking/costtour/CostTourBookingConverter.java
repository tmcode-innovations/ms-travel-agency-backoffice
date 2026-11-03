package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtour;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate.ItineraryDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.CostTourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CostTourBookingConverter implements Converter<CostTourDomain, CostTourEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private ItineraryDateConverter itineraryDateConverter;

    @Override
    public CostTourDomain toDomain(CostTourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostTourDomain costTourDomain = new CostTourDomain();

        costTourDomain.setId( source.getId() );
        costTourDomain.setDeleted( source.getDeleted() );
        costTourDomain.setEnabled( source.getEnabled() );
        costTourDomain.setMigrated( source.getMigrated() );
        costTourDomain.setCreatedAt( source.getCreatedAt() );
        costTourDomain.setUpdatedAt( source.getUpdatedAt() );
        costTourDomain.setDeletedAt( source.getDeletedAt() );
        costTourDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        costTourDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        costTourDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        costTourDomain.setIdentifierName( source.getIdentifierName() );
        costTourDomain.setTenant( source.getTenant() );
        costTourDomain.setName( source.getName() );
        costTourDomain.setDescription( source.getDescription() );
        costTourDomain.setIdentify( source.getIdentify() );
        costTourDomain.setNumMaxPax( source.getNumMaxPax() );
        costTourDomain.setSubTotalAmount( source.getSubTotalAmount() );
        costTourDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        costTourDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        costTourDomain.setItineraryDate( itineraryDateConverter.toDomain( source.getItineraryDate() ) );
        return costTourDomain;
    }

    @Override
    public CostTourEntity toEntity(CostTourDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostTourEntity costTourEntity = new CostTourEntity();

        costTourEntity.setId( source.getId() );
        costTourEntity.setDeleted( source.getDeleted() );
        costTourEntity.setEnabled( source.getEnabled() );
        costTourEntity.setMigrated( source.getMigrated() );
        costTourEntity.setCreatedAt( source.getCreatedAt() );
        costTourEntity.setUpdatedAt( source.getUpdatedAt() );
        costTourEntity.setDeletedAt( source.getDeletedAt() );
        costTourEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        costTourEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        costTourEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        costTourEntity.setIdentifierName( source.getIdentifierName() );
        costTourEntity.setTenant( source.getTenant() );
        costTourEntity.setName( source.getName() );
        costTourEntity.setDescription( source.getDescription() );
        costTourEntity.setIdentify( source.getIdentify() );
        costTourEntity.setNumMaxPax( source.getNumMaxPax() );
        costTourEntity.setSubTotalAmount( source.getSubTotalAmount() );
        costTourEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        costTourEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        costTourEntity.setItineraryDate( itineraryDateConverter.toEntity( source.getItineraryDate() ) );
        return costTourEntity;
    }

    public IdBaseDomain toDomainIdBase(CostTourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CostTourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CostTourDomain> toDomainAllSimpleToDomainCaseShowCostTour(List<CostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourDomain costTourDomain = new CostTourDomain();
            costTourDomain.setId(entity.getId());
            costTourDomain.setNumMaxPax(entity.getNumMaxPax());
            costTourDomain.setSubTotalAmount(entity.getSubTotalAmount());
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                costTourDomain.setItineraryDate(new ItineraryDateDomain());
                costTourDomain.getItineraryDate().setId(entity.getItineraryDate().getId());
                costTourDomain.getItineraryDate().setName(entity.getItineraryDate().getName());
                costTourDomain.getItineraryDate().setIndex(entity.getItineraryDate().getIndex());
                costTourDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                costTourDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
                if (entity.getItineraryDate().getTours() != null && !entity.getItineraryDate().getTours().isEmpty()){
                    costTourDomain.getItineraryDate().setTours(new HashSet<>());
                    for ( TourEntity tourEntity : entity.getItineraryDate().getTours() ) {
                        TourDomain tourDomain = new TourDomain();
                        tourDomain.setId(tourEntity.getId());
                        tourDomain.setName(tourEntity.getName());
                        costTourDomain.getItineraryDate().getTours().add(tourDomain);
                    }
                }
            }

            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                costTourDomain.setTour(new TourDomain());
                costTourDomain.getTour().setId(entity.getTour().getId());
                costTourDomain.getTour().setName(entity.getTour().getName());
                costTourDomain.getTour().setCantDay(entity.getTour().getCantDay());
                costTourDomain.getTour().setWithoutCost(entity.getTour().getWithoutCost());
                costTourDomain.getTour().setUniqueDay(entity.getTour().getUniqueDay());
            }

            return costTourDomain;
        }).toList();
    }

    public List<CostTourDomain> toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(List<CostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourDomain costTourDomain = new CostTourDomain();
            costTourDomain.setId( entity.getId() );
            return costTourDomain;
        }).toList();
    }

    public List<CostTourDomain> toDomainAllSimpleToDomainCaseDeleteByRegisterGroupAnditineraryDate(List<CostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourDomain costTourDomain = new CostTourDomain();
            costTourDomain.setId( entity.getId() );
            return costTourDomain;
        }).toList();
    }

    public List<CostTourDomain> toDomainAllSimpleToDomainCaseResendFileToSale(List<CostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourDomain costTourDomain = new CostTourDomain();
            costTourDomain.setId( entity.getId() );
            costTourDomain.setName( entity.getName() );
            costTourDomain.setDescription( entity.getDescription() );
            costTourDomain.setIdentify( entity.getIdentify() );
            costTourDomain.setNumMaxPax(entity.getNumMaxPax());
            costTourDomain.setSubTotalAmount(entity.getSubTotalAmount());
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                costTourDomain.setItineraryDate(new ItineraryDateDomain());
                costTourDomain.getItineraryDate().setId(entity.getItineraryDate().getId());
                costTourDomain.getItineraryDate().setName(entity.getItineraryDate().getName());
                costTourDomain.getItineraryDate().setIndex(entity.getItineraryDate().getIndex());
                costTourDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                costTourDomain.setTour(new TourDomain());
                costTourDomain.getTour().setId(entity.getTour().getId());
            }
            return costTourDomain;
        }).toList();
    }
}
