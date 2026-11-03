package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemrestaurant;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingrestaurant.RegisterBookingRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemRestaurantConverter implements Converter<RegisterBookingItemRestaurantDomain, RegisterBookingItemRestaurantEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingRestaurantConverter registerBookingRestaurantConverter;


    @Override
    public RegisterBookingItemRestaurantDomain toDomain(RegisterBookingItemRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain = new RegisterBookingItemRestaurantDomain();

        registerBookingItemRestaurantDomain.setId( source.getId() );
        registerBookingItemRestaurantDomain.setDeleted( source.getDeleted() );
        registerBookingItemRestaurantDomain.setEnabled( source.getEnabled() );
        registerBookingItemRestaurantDomain.setMigrated( source.getMigrated() );
        registerBookingItemRestaurantDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemRestaurantDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemRestaurantDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemRestaurantDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemRestaurantDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemRestaurantDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemRestaurantDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemRestaurantDomain.setTenant( source.getTenant() );
        registerBookingItemRestaurantDomain.setName( source.getName() );
        registerBookingItemRestaurantDomain.setDescription( source.getDescription() );
        registerBookingItemRestaurantDomain.setIdentify( source.getIdentify() );

        registerBookingItemRestaurantDomain.setDateService( source.getDateService() );
        registerBookingItemRestaurantDomain.setCantPax( source.getCantPax() );
        registerBookingItemRestaurantDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingItemRestaurantDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemRestaurantDomain.setTc( source.getTc() );
        registerBookingItemRestaurantDomain.setTcLocal( source.getTcLocal() );
        registerBookingItemRestaurantDomain.setGuide( source.getGuide() );
        registerBookingItemRestaurantDomain.setSequence( source.getSequence() );

        registerBookingItemRestaurantDomain.setObservation( source.getObservation() );
        registerBookingItemRestaurantDomain.setOtherService( source.getOtherService() );

        registerBookingItemRestaurantDomain.setPvt( source.getPvt() );
        registerBookingItemRestaurantDomain.setSic( source.getSic() );
        registerBookingItemRestaurantDomain.setDate( source.getDate() );
        registerBookingItemRestaurantDomain.setDateTime( source.getDateTime() );
        registerBookingItemRestaurantDomain.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemRestaurantDomain.setCant( source.getCant() );
        registerBookingItemRestaurantDomain.setCantGuideTc( source.getCantGuideTc() );

        registerBookingItemRestaurantDomain.setService( serviceConverter.toDomain( source.getService() ) );
        registerBookingItemRestaurantDomain.setRegisterBookingRestaurant( registerBookingRestaurantConverter.toDomain( source.getRegisterBookingRestaurant() ) );
        registerBookingItemRestaurantDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingItemRestaurantDomain;
    }

    @Override
    public RegisterBookingItemRestaurantEntity toEntity(RegisterBookingItemRestaurantDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemRestaurantEntity registerBookingItemRestaurantEntity = new RegisterBookingItemRestaurantEntity();

        registerBookingItemRestaurantEntity.setId( source.getId() );
        registerBookingItemRestaurantEntity.setDeleted( source.getDeleted() );
        registerBookingItemRestaurantEntity.setEnabled( source.getEnabled() );
        registerBookingItemRestaurantEntity.setMigrated( source.getMigrated() );
        registerBookingItemRestaurantEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemRestaurantEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemRestaurantEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemRestaurantEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemRestaurantEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemRestaurantEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemRestaurantEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemRestaurantEntity.setTenant( source.getTenant() );
        registerBookingItemRestaurantEntity.setName( source.getName() );
        registerBookingItemRestaurantEntity.setDescription( source.getDescription() );
        registerBookingItemRestaurantEntity.setIdentify( source.getIdentify() );

        registerBookingItemRestaurantEntity.setDateService( source.getDateService() );
        registerBookingItemRestaurantEntity.setCantPax( source.getCantPax() );
        registerBookingItemRestaurantEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingItemRestaurantEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemRestaurantEntity.setTc( source.getTc() );
        registerBookingItemRestaurantEntity.setTcLocal( source.getTcLocal() );
        registerBookingItemRestaurantEntity.setGuide( source.getGuide() );
        registerBookingItemRestaurantEntity.setSequence( source.getSequence() );

        registerBookingItemRestaurantEntity.setObservation( source.getObservation() );
        registerBookingItemRestaurantEntity.setOtherService( source.getOtherService() );

        registerBookingItemRestaurantEntity.setPvt( source.getPvt() );
        registerBookingItemRestaurantEntity.setSic( source.getSic() );
        registerBookingItemRestaurantEntity.setDate( source.getDate() );
        registerBookingItemRestaurantEntity.setDateTime( source.getDateTime() );
        registerBookingItemRestaurantEntity.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemRestaurantEntity.setCant( source.getCant() );
        registerBookingItemRestaurantEntity.setCantGuideTc( source.getCantGuideTc() );

        registerBookingItemRestaurantEntity.setService( serviceConverter.toEntity( source.getService() ) );
        registerBookingItemRestaurantEntity.setRegisterBookingRestaurant( registerBookingRestaurantConverter.toEntity( source.getRegisterBookingRestaurant() ) );
        registerBookingItemRestaurantEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemRestaurantEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemRestaurantDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingItemRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain = new RegisterBookingItemRestaurantDomain();
            registerBookingItemRestaurantDomain.setId( entity.getId() );
            registerBookingItemRestaurantDomain.setGuide( entity.getGuide() );
            registerBookingItemRestaurantDomain.setDateService( entity.getDateService() );
            registerBookingItemRestaurantDomain.setDate( entity.getDate() );
            registerBookingItemRestaurantDomain.setService( new ServiceDomain());
            registerBookingItemRestaurantDomain.getService().setId(entity.getService().getId());
            registerBookingItemRestaurantDomain.getService().setName(entity.getService().getName());
            registerBookingItemRestaurantDomain.getService().setAbbrev(entity.getService().getAbbrev());
            registerBookingItemRestaurantDomain.setObservation( entity.getObservation() );
            registerBookingItemRestaurantDomain.setSequence(entity.getSequence());
            return registerBookingItemRestaurantDomain;
        }).toList();
    }
}
