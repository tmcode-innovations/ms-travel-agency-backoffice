package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItineraryDateConverter implements Converter<ItineraryDateDomain, ItineraryDateEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private TourConverter tourConverter;

    @Override
    public ItineraryDateDomain toDomain(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();

        itineraryDateDomain.setId( source.getId() );
        itineraryDateDomain.setDeleted( source.getDeleted() );
        itineraryDateDomain.setEnabled( source.getEnabled() );
        itineraryDateDomain.setMigrated( source.getMigrated() );
        itineraryDateDomain.setCreatedAt( source.getCreatedAt() );
        itineraryDateDomain.setUpdatedAt( source.getUpdatedAt() );
        itineraryDateDomain.setDeletedAt( source.getDeletedAt() );
        itineraryDateDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        itineraryDateDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        itineraryDateDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        itineraryDateDomain.setIdentifierName( source.getIdentifierName() );
        itineraryDateDomain.setTenant( source.getTenant() );
        itineraryDateDomain.setName( source.getName() );
        itineraryDateDomain.setDescription( source.getDescription() );
        itineraryDateDomain.setIdentify( source.getIdentify() );
        itineraryDateDomain.setItineraryDate( source.getItineraryDate() );
        itineraryDateDomain.setIndex( source.getIndex() );
        itineraryDateDomain.setItemItinerary( source.getItemItinerary() );
        itineraryDateDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        itineraryDateDomain.setIdItineraryDateQuotation( source.getIdItineraryDateQuotation() );
        itineraryDateDomain.setTours( tourEntitySetToTourDomainSet( source.getTours() ) );

        return itineraryDateDomain;
    }

    @Override
    public ItineraryDateEntity toEntity(ItineraryDateDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateEntity itineraryDateEntity = new ItineraryDateEntity();

        itineraryDateEntity.setId( source.getId() );
        itineraryDateEntity.setDeleted( source.getDeleted() );
        itineraryDateEntity.setEnabled( source.getEnabled() );
        itineraryDateEntity.setMigrated( source.getMigrated() );
        itineraryDateEntity.setCreatedAt( source.getCreatedAt() );
        itineraryDateEntity.setUpdatedAt( source.getUpdatedAt() );
        itineraryDateEntity.setDeletedAt( source.getDeletedAt() );
        itineraryDateEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        itineraryDateEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        itineraryDateEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        itineraryDateEntity.setIdentifierName( source.getIdentifierName() );
        itineraryDateEntity.setTenant( source.getTenant() );
        itineraryDateEntity.setName( source.getName() );
        itineraryDateEntity.setDescription( source.getDescription() );
        itineraryDateEntity.setIdentify( source.getIdentify() );
        itineraryDateEntity.setItineraryDate( source.getItineraryDate() );
        itineraryDateEntity.setIndex( source.getIndex() );
        itineraryDateEntity.setItemItinerary( source.getItemItinerary() );
        itineraryDateEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        itineraryDateEntity.setIdItineraryDateQuotation( source.getIdItineraryDateQuotation() );
        itineraryDateEntity.setTours( tourDomainSetToTourEntitySet( source.getTours() ) );

        return itineraryDateEntity;
    }

    public Set<TourDomain> tourEntitySetToTourDomainSet(Set<TourEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<TourDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TourEntity tourEntity : set ) {
            set1.add( tourConverter.toDomain( tourEntity ) );
        }
        return set1;
    }

    protected Set<TourEntity> tourDomainSetToTourEntitySet(Set<TourDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<TourEntity> set1 = new LinkedHashSet<TourEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TourDomain tourDomain : set ) {
            set1.add( tourConverter.toEntity( tourDomain ) );
        }
        return set1;
    }

    public IdBaseDomain toDomainIdBase(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseShowItinerary(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(entity.getId());
            itineraryDateDomain.setIndex(entity.getIndex());
            itineraryDateDomain.setItemItinerary(entity.getItemItinerary());
            itineraryDateDomain.setName(entity.getName());
            itineraryDateDomain.setItineraryDate(entity.getItineraryDate());
            if (entity.getTours() != null && !entity.getTours().isEmpty()){
                itineraryDateDomain.setTours(new HashSet<>());
                for ( TourEntity tourEntity : entity.getTours() ) {
                    TourDomain tourDomain = new TourDomain();
                    tourDomain.setId(tourEntity.getId());
                    tourDomain.setName(tourEntity.getName());
                    itineraryDateDomain.getTours().add(tourDomain);
                }
            }
            return itineraryDateDomain;
        }).toList();
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseUpdateOrderItinerary(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(entity.getId());
            itineraryDateDomain.setIndex(entity.getIndex());
            itineraryDateDomain.setName(entity.getName());
            itineraryDateDomain.setItineraryDate(entity.getItineraryDate());
            itineraryDateDomain.setItemItinerary(entity.getItemItinerary());
            return itineraryDateDomain;
        }).toList();
    }

    public ItineraryDateDomain toDomainIdSimpleDomainCaseUpdateOrderItinerary(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();

        itineraryDateDomain.setId( source.getId() );
        itineraryDateDomain.setIndex( source.getIndex() );
        itineraryDateDomain.setName(source.getName());
        itineraryDateDomain.setItineraryDate(source.getItineraryDate());
        itineraryDateDomain.setItemItinerary(source.getItemItinerary());
        return itineraryDateDomain;
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseGenerateBooking(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(entity.getId());
            itineraryDateDomain.setIndex(entity.getIndex());
            itineraryDateDomain.setName(entity.getName());
            return itineraryDateDomain;
        }).toList();
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseGenerateBookingAction(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(entity.getId());
            itineraryDateDomain.setIndex(entity.getIndex());
            itineraryDateDomain.setName(entity.getName());
            itineraryDateDomain.setItemItinerary(entity.getItemItinerary());
            return itineraryDateDomain;
        }).toList();
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseDeleteItineraryDate(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(entity.getId());
            itineraryDateDomain.setIndex(entity.getIndex());
            itineraryDateDomain.setName(entity.getName());
            itineraryDateDomain.setItineraryDate(entity.getItineraryDate());
            itineraryDateDomain.setItemItinerary(entity.getItemItinerary());
            return itineraryDateDomain;
        }).toList();
    }

    public ItineraryDateDomain toDomainIdSimpleDomainCaseDeleteItineraryDate(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();

        itineraryDateDomain.setId( source.getId() );
        itineraryDateDomain.setIndex( source.getIndex() );
        itineraryDateDomain.setName(source.getName());
        itineraryDateDomain.setItineraryDate(source.getItineraryDate());
        itineraryDateDomain.setItemItinerary(source.getItemItinerary());
        return itineraryDateDomain;
    }

    public ItineraryDateDomain toDomainItemItinerarySimpleDomainCaseGeneric(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();

        itineraryDateDomain.setId( source.getId() );
        itineraryDateDomain.setIndex( source.getIndex() );
        itineraryDateDomain.setName(source.getName());
        itineraryDateDomain.setItineraryDate(source.getItineraryDate());
        itineraryDateDomain.setItemItinerary(source.getItemItinerary());
        return itineraryDateDomain;
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseGeneric(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(entity.getId());
            itineraryDateDomain.setIndex(entity.getIndex());
            itineraryDateDomain.setName(entity.getName());
            itineraryDateDomain.setItineraryDate(entity.getItineraryDate());
            itineraryDateDomain.setItemItinerary(entity.getItemItinerary());
            return itineraryDateDomain;
        }).toList();
    }

    public ItineraryDateDomain toDomainIdSimpleDomainCaseDeleteTour(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();

        itineraryDateDomain.setId( source.getId() );
        itineraryDateDomain.setIndex( source.getIndex() );
        itineraryDateDomain.setName(source.getName());
        itineraryDateDomain.setItineraryDate(source.getItineraryDate());
        itineraryDateDomain.setItemItinerary(source.getItemItinerary());
        if (source.getTours() != null && !source.getTours().isEmpty()){
            itineraryDateDomain.setTours(new HashSet<>());
            for ( TourEntity tourEntity : source.getTours() ) {
                TourDomain tourDomain = new TourDomain();
                tourDomain.setId(tourEntity.getId());
                tourDomain.setName(tourEntity.getName());
                itineraryDateDomain.getTours().add(tourDomain);
            }
        }
        return itineraryDateDomain;
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseDeleteTour(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId( entity.getId() );
            itineraryDateDomain.setIndex( entity.getIndex() );
            itineraryDateDomain.setName(entity.getName());
            itineraryDateDomain.setItineraryDate(entity.getItineraryDate());
            itineraryDateDomain.setItemItinerary(entity.getItemItinerary());
            if (entity.getTours() != null && !entity.getTours().isEmpty()){
                itineraryDateDomain.setTours(new HashSet<>());
                for ( TourEntity tourEntity : entity.getTours() ) {
                    TourDomain tourDomain = new TourDomain();
                    tourDomain.setId(tourEntity.getId());
                    tourDomain.setName(tourEntity.getName());
                    itineraryDateDomain.getTours().add(tourDomain);
                }
            }
            return itineraryDateDomain;
        }).toList();
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId( entity.getId() );
            return itineraryDateDomain;
        }).toList();
    }

    public List<ItineraryDateDomain> toDomainAllSimpleToDomainCaseProgramByRegisterGroup(List<ItineraryDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId( entity.getId() );
            itineraryDateDomain.setIndex( entity.getIndex() );
            itineraryDateDomain.setItineraryDate( entity.getItineraryDate() );
            return itineraryDateDomain;
        }).toList();
    }

    public ItineraryDateDomain toDomainIdSimpleDomainCaseCommonBasicData(ItineraryDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();

        itineraryDateDomain.setId( source.getId() );
        itineraryDateDomain.setIndex( source.getIndex() );
        itineraryDateDomain.setName(source.getName());
        itineraryDateDomain.setItineraryDate(source.getItineraryDate());
        itineraryDateDomain.setItemItinerary(source.getItemItinerary());
        return itineraryDateDomain;
    }
}
