package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgingfeeding;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemglamping.RegisterBookingItemGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodging.RegisterBookingItemLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingFeedingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingFeedingDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemLodgingFeedingConverter implements Converter<RegisterBookingItemLodgingFeedingDomain, RegisterBookingItemLodgingFeedingEntity> {

    @Autowired
    private UserConverter userConverter;


    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingLodgingConverter registerBookingLodgingConverter;

    @Autowired
    private RegisterBookingItemLodgingConverter registerBookingItemLodgingConverter;

    @Autowired
    private RegisterBookingLodgerConverter registerBookingLodgerConverter;

    @Autowired
    private RegisterBookingItemLodgerLodgingConverter registerBookingItemLodgerLodgingConverter;

    @Autowired
    private RegisterBookingGlampingConverter registerBookingGlampingConverter;

    @Autowired
    private RegisterBookingItemGlampingConverter registerBookingItemGlampingConverter;

    @Override
    public RegisterBookingItemLodgingFeedingDomain toDomain(RegisterBookingItemLodgingFeedingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();

        registerBookingItemLodgingFeedingDomain.setId( source.getId() );
        registerBookingItemLodgingFeedingDomain.setDeleted( source.getDeleted() );
        registerBookingItemLodgingFeedingDomain.setEnabled( source.getEnabled() );
        registerBookingItemLodgingFeedingDomain.setMigrated( source.getMigrated() );
        registerBookingItemLodgingFeedingDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgingFeedingDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgingFeedingDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgingFeedingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemLodgingFeedingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemLodgingFeedingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemLodgingFeedingDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgingFeedingDomain.setTenant( source.getTenant() );
        registerBookingItemLodgingFeedingDomain.setName( source.getName() );
        registerBookingItemLodgingFeedingDomain.setDescription( source.getDescription() );
        registerBookingItemLodgingFeedingDomain.setIdentify( source.getIdentify() );

        registerBookingItemLodgingFeedingDomain.setDateFeeding( source.getDateFeeding() );
        registerBookingItemLodgingFeedingDomain.setDateTimeFeeding( source.getDateTimeFeeding() );
        registerBookingItemLodgingFeedingDomain.setCant( source.getCant() );
        registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking( source.getTypeFeedingBooking() );
        registerBookingItemLodgingFeedingDomain.setTypePassengerBooking( source.getTypePassengerBooking() );
        registerBookingItemLodgingFeedingDomain.setTypeBooking( source.getTypeBooking() );

        registerBookingItemLodgingFeedingDomain.setRegisterBookingLodger( registerBookingLodgerConverter.toDomain( source.getRegisterBookingLodger() ) );
        registerBookingItemLodgingFeedingDomain.setRegisterBookingItemLodgerLodging( registerBookingItemLodgerLodgingConverter.toDomain( source.getRegisterBookingItemLodgerLodging() ) );
        registerBookingItemLodgingFeedingDomain.setRegisterBookingLodging( registerBookingLodgingConverter.toDomain( source.getRegisterBookingLodging() ) );
        registerBookingItemLodgingFeedingDomain.setRegisterBookingItemLodging( registerBookingItemLodgingConverter.toDomain( source.getRegisterBookingItemLodging() ) );
        registerBookingItemLodgingFeedingDomain.setRegisterBookingGlamping( registerBookingGlampingConverter.toDomain( source.getRegisterBookingGlamping() ) );
        registerBookingItemLodgingFeedingDomain.setRegisterBookingItemGlamping( registerBookingItemGlampingConverter.toDomain( source.getRegisterBookingItemGlamping() ) );
        registerBookingItemLodgingFeedingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );


        return registerBookingItemLodgingFeedingDomain;
    }

    @Override
    public RegisterBookingItemLodgingFeedingEntity toEntity(RegisterBookingItemLodgingFeedingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgingFeedingEntity registerBookingItemLodgingFeedingEntity = new RegisterBookingItemLodgingFeedingEntity();

        registerBookingItemLodgingFeedingEntity.setId( source.getId() );
        registerBookingItemLodgingFeedingEntity.setDeleted( source.getDeleted() );
        registerBookingItemLodgingFeedingEntity.setEnabled( source.getEnabled() );
        registerBookingItemLodgingFeedingEntity.setMigrated( source.getMigrated() );
        registerBookingItemLodgingFeedingEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgingFeedingEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgingFeedingEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgingFeedingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemLodgingFeedingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemLodgingFeedingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemLodgingFeedingEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgingFeedingEntity.setTenant( source.getTenant() );
        registerBookingItemLodgingFeedingEntity.setName( source.getName() );
        registerBookingItemLodgingFeedingEntity.setDescription( source.getDescription() );
        registerBookingItemLodgingFeedingEntity.setIdentify( source.getIdentify() );

        registerBookingItemLodgingFeedingEntity.setDateFeeding( source.getDateFeeding() );
        registerBookingItemLodgingFeedingEntity.setDateTimeFeeding( source.getDateTimeFeeding() );
        registerBookingItemLodgingFeedingEntity.setCant( source.getCant() );
        registerBookingItemLodgingFeedingEntity.setTypeFeedingBooking( source.getTypeFeedingBooking() );
        registerBookingItemLodgingFeedingEntity.setTypePassengerBooking( source.getTypePassengerBooking() );
        registerBookingItemLodgingFeedingEntity.setTypeBooking( source.getTypeBooking() );

        registerBookingItemLodgingFeedingEntity.setRegisterBookingLodger( registerBookingLodgerConverter.toEntity( source.getRegisterBookingLodger() ) );
        registerBookingItemLodgingFeedingEntity.setRegisterBookingItemLodgerLodging( registerBookingItemLodgerLodgingConverter.toEntity( source.getRegisterBookingItemLodgerLodging() ) );
        registerBookingItemLodgingFeedingEntity.setRegisterBookingLodging( registerBookingLodgingConverter.toEntity( source.getRegisterBookingLodging() ) );
        registerBookingItemLodgingFeedingEntity.setRegisterBookingItemLodging( registerBookingItemLodgingConverter.toEntity( source.getRegisterBookingItemLodging() ) );
        registerBookingItemLodgingFeedingEntity.setRegisterBookingGlamping( registerBookingGlampingConverter.toEntity( source.getRegisterBookingGlamping() ) );
        registerBookingItemLodgingFeedingEntity.setRegisterBookingItemGlamping( registerBookingItemGlampingConverter.toEntity( source.getRegisterBookingItemGlamping() ) );
        registerBookingItemLodgingFeedingEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemLodgingFeedingEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemLodgingFeedingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemLodgingFeedingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemLodgingFeedingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemLodgingFeedingDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingItemLodgingFeedingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
            registerBookingItemLodgingFeedingDomain.setId( entity.getId() );

            registerBookingItemLodgingFeedingDomain.setDateFeeding( entity.getDateFeeding() );
            registerBookingItemLodgingFeedingDomain.setDateTimeFeeding( entity.getDateTimeFeeding() );
            registerBookingItemLodgingFeedingDomain.setCant( entity.getCant() );
            registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking( entity.getTypeFeedingBooking() );
            registerBookingItemLodgingFeedingDomain.setTypePassengerBooking( entity.getTypePassengerBooking() );
            registerBookingItemLodgingFeedingDomain.setTypeBooking( entity.getTypeBooking() );

            return registerBookingItemLodgingFeedingDomain;
        }).toList();
    }
}
