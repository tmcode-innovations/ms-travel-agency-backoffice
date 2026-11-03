package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingroom;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRoomEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRoomDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingRoomConverter implements Converter<RegisterBookingRoomDomain, RegisterBookingRoomEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingLodgingConverter registerBookingLodgingConverter;

    @Autowired
    private RegisterBookingLodgerConverter registerBookingLodgerConverter;

    @Autowired
    private RegisterBookingGlampingConverter registerBookingGlampingConverter;

    @Override
    public RegisterBookingRoomDomain toDomain(RegisterBookingRoomEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingRoomDomain registerBookingRoomDomain = new RegisterBookingRoomDomain();

        registerBookingRoomDomain.setId( source.getId() );
        registerBookingRoomDomain.setDeleted( source.getDeleted() );
        registerBookingRoomDomain.setEnabled( source.getEnabled() );
        registerBookingRoomDomain.setMigrated( source.getMigrated() );
        registerBookingRoomDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingRoomDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingRoomDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingRoomDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingRoomDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingRoomDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingRoomDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingRoomDomain.setTenant( source.getTenant() );
        registerBookingRoomDomain.setName( source.getName() );
        registerBookingRoomDomain.setDescription( source.getDescription() );
        registerBookingRoomDomain.setIdentify( source.getIdentify() );

        registerBookingRoomDomain.setTypeRoomBooking( source.getTypeRoomBooking() );

        registerBookingRoomDomain.setRegisterBookingLodging( registerBookingLodgingConverter.toDomain( source.getRegisterBookingLodging() ) );
        registerBookingRoomDomain.setRegisterBookingGlamping( registerBookingGlampingConverter.toDomain( source.getRegisterBookingGlamping() ) );
        registerBookingRoomDomain.setRegisterBookingLodger( registerBookingLodgerConverter.toDomain( source.getRegisterBookingLodger() ) );
        registerBookingRoomDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingRoomDomain;
    }

    @Override
    public RegisterBookingRoomEntity toEntity(RegisterBookingRoomDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingRoomEntity registerBookingRoomEntity = new RegisterBookingRoomEntity();

        registerBookingRoomEntity.setId( source.getId() );
        registerBookingRoomEntity.setDeleted( source.getDeleted() );
        registerBookingRoomEntity.setEnabled( source.getEnabled() );
        registerBookingRoomEntity.setMigrated( source.getMigrated() );
        registerBookingRoomEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingRoomEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingRoomEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingRoomEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingRoomEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingRoomEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingRoomEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingRoomEntity.setTenant( source.getTenant() );
        registerBookingRoomEntity.setName( source.getName() );
        registerBookingRoomEntity.setDescription( source.getDescription() );
        registerBookingRoomEntity.setIdentify( source.getIdentify() );

        registerBookingRoomEntity.setTypeRoomBooking( source.getTypeRoomBooking() );

        registerBookingRoomEntity.setRegisterBookingLodging( registerBookingLodgingConverter.toEntity( source.getRegisterBookingLodging() ) );
        registerBookingRoomEntity.setRegisterBookingGlamping( registerBookingGlampingConverter.toEntity( source.getRegisterBookingGlamping() ) );
        registerBookingRoomEntity.setRegisterBookingLodger( registerBookingLodgerConverter.toEntity( source.getRegisterBookingLodger() ) );
        registerBookingRoomEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingRoomEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingRoomEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingRoomEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingRoomEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
