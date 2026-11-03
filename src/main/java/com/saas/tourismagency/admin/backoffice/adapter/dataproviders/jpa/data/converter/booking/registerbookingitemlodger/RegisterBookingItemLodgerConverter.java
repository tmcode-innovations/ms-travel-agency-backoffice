package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodger;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemLodgerConverter implements Converter<RegisterBookingItemLodgerDomain, RegisterBookingItemLodgerEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingLodgerConverter registerBookingLodgerConverter;

    @Override
    public RegisterBookingItemLodgerDomain toDomain(RegisterBookingItemLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = new RegisterBookingItemLodgerDomain();

        registerBookingItemLodgerDomain.setId( source.getId() );
        registerBookingItemLodgerDomain.setDeleted( source.getDeleted() );
        registerBookingItemLodgerDomain.setEnabled( source.getEnabled() );
        registerBookingItemLodgerDomain.setMigrated( source.getMigrated() );
        registerBookingItemLodgerDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgerDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgerDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemLodgerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemLodgerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemLodgerDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgerDomain.setTenant( source.getTenant() );
        registerBookingItemLodgerDomain.setName( source.getName() );
        registerBookingItemLodgerDomain.setDescription( source.getDescription() );
        registerBookingItemLodgerDomain.setIdentify( source.getIdentify() );

        registerBookingItemLodgerDomain.setDateService( source.getDateService() );
        registerBookingItemLodgerDomain.setCantPax( source.getCantPax() );
        registerBookingItemLodgerDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingItemLodgerDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemLodgerDomain.setTc( source.getTc() );
        registerBookingItemLodgerDomain.setTcLocal( source.getTcLocal() );
        registerBookingItemLodgerDomain.setGuide( source.getGuide() );
        registerBookingItemLodgerDomain.setSequence( source.getSequence() );

        registerBookingItemLodgerDomain.setObservation( source.getObservation() );
        registerBookingItemLodgerDomain.setOtherService( source.getOtherService() );

        registerBookingItemLodgerDomain.setPvt( source.getPvt() );
        registerBookingItemLodgerDomain.setSic( source.getSic() );
        registerBookingItemLodgerDomain.setDate( source.getDate() );
        registerBookingItemLodgerDomain.setDateTime( source.getDateTime() );
        registerBookingItemLodgerDomain.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemLodgerDomain.setService( serviceConverter.toDomain( source.getService() ) );
        registerBookingItemLodgerDomain.setRegisterBookingLodger( registerBookingLodgerConverter.toDomain( source.getRegisterBookingLodger() ) );
        registerBookingItemLodgerDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingItemLodgerDomain;
    }

    @Override
    public RegisterBookingItemLodgerEntity toEntity(RegisterBookingItemLodgerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgerEntity registerBookingItemLodgerEntity = new RegisterBookingItemLodgerEntity();

        registerBookingItemLodgerEntity.setId( source.getId() );
        registerBookingItemLodgerEntity.setDeleted( source.getDeleted() );
        registerBookingItemLodgerEntity.setEnabled( source.getEnabled() );
        registerBookingItemLodgerEntity.setMigrated( source.getMigrated() );
        registerBookingItemLodgerEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgerEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgerEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemLodgerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemLodgerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemLodgerEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgerEntity.setTenant( source.getTenant() );
        registerBookingItemLodgerEntity.setName( source.getName() );
        registerBookingItemLodgerEntity.setDescription( source.getDescription() );
        registerBookingItemLodgerEntity.setIdentify( source.getIdentify() );

        registerBookingItemLodgerEntity.setDateService( source.getDateService() );
        registerBookingItemLodgerEntity.setCantPax( source.getCantPax() );
        registerBookingItemLodgerEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingItemLodgerEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemLodgerEntity.setTc( source.getTc() );
        registerBookingItemLodgerEntity.setTcLocal( source.getTcLocal() );
        registerBookingItemLodgerEntity.setGuide( source.getGuide() );
        registerBookingItemLodgerEntity.setSequence( source.getSequence() );

        registerBookingItemLodgerEntity.setObservation( source.getObservation() );
        registerBookingItemLodgerEntity.setOtherService( source.getOtherService() );

        registerBookingItemLodgerEntity.setPvt( source.getPvt() );
        registerBookingItemLodgerEntity.setSic( source.getSic() );
        registerBookingItemLodgerEntity.setDate( source.getDate() );
        registerBookingItemLodgerEntity.setDateTime( source.getDateTime() );
        registerBookingItemLodgerEntity.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemLodgerEntity.setService( serviceConverter.toEntity( source.getService() ) );
        registerBookingItemLodgerEntity.setRegisterBookingLodger( registerBookingLodgerConverter.toEntity( source.getRegisterBookingLodger() ) );
        registerBookingItemLodgerEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemLodgerEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemLodgerDomain> toDomainAllSimpleToDomain(List<RegisterBookingItemLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = new RegisterBookingItemLodgerDomain();
            registerBookingItemLodgerDomain.setId(entity.getId());
            registerBookingItemLodgerDomain.setSequence(entity.getSequence());
            return registerBookingItemLodgerDomain;
        }).toList();
    }

    public List<RegisterBookingItemLodgerDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingItemLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = new RegisterBookingItemLodgerDomain();
            registerBookingItemLodgerDomain.setId( entity.getId() );
            registerBookingItemLodgerDomain.setGuide( entity.getGuide() );
            registerBookingItemLodgerDomain.setDateService( entity.getDateService() );
            registerBookingItemLodgerDomain.setDate( entity.getDate() );
            registerBookingItemLodgerDomain.setService( new ServiceDomain());
            registerBookingItemLodgerDomain.getService().setId(entity.getService().getId());
            registerBookingItemLodgerDomain.getService().setName(entity.getService().getName());
            registerBookingItemLodgerDomain.getService().setAbbrev(entity.getService().getAbbrev());
            registerBookingItemLodgerDomain.setObservation( entity.getObservation() );
            registerBookingItemLodgerDomain.setPvt(entity.getPvt());
            registerBookingItemLodgerDomain.setSic(entity.getSic());
            registerBookingItemLodgerDomain.setSequence(entity.getSequence());
            return registerBookingItemLodgerDomain;
        }).toList();
    }
}
