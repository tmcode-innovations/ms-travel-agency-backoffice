package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemoperator;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemOperatorConverter implements Converter<RegisterBookingItemOperatorDomain, RegisterBookingItemOperatorEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingOperatorConverter registerBookingOperatorConverter;

    @Override
    public RegisterBookingItemOperatorDomain toDomain(RegisterBookingItemOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();

        registerBookingItemOperatorDomain.setId( source.getId() );
        registerBookingItemOperatorDomain.setDeleted( source.getDeleted() );
        registerBookingItemOperatorDomain.setEnabled( source.getEnabled() );
        registerBookingItemOperatorDomain.setMigrated( source.getMigrated() );
        registerBookingItemOperatorDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemOperatorDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemOperatorDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemOperatorDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemOperatorDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemOperatorDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemOperatorDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemOperatorDomain.setTenant( source.getTenant() );
        registerBookingItemOperatorDomain.setName( source.getName() );
        registerBookingItemOperatorDomain.setDescription( source.getDescription() );
        registerBookingItemOperatorDomain.setIdentify( source.getIdentify() );

        registerBookingItemOperatorDomain.setDateService( source.getDateService() );
        registerBookingItemOperatorDomain.setCantPax( source.getCantPax() );
        registerBookingItemOperatorDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingItemOperatorDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemOperatorDomain.setTc( source.getTc() );
        registerBookingItemOperatorDomain.setTcLocal( source.getTcLocal() );
        registerBookingItemOperatorDomain.setGuide( source.getGuide() );
        registerBookingItemOperatorDomain.setSequence( source.getSequence() );

        registerBookingItemOperatorDomain.setObservation( source.getObservation() );
        registerBookingItemOperatorDomain.setOtherService( source.getOtherService() );

        registerBookingItemOperatorDomain.setPvt( source.getPvt() );
        registerBookingItemOperatorDomain.setSic( source.getSic() );
        registerBookingItemOperatorDomain.setDate( source.getDate() );
        registerBookingItemOperatorDomain.setDateTime( source.getDateTime() );
        registerBookingItemOperatorDomain.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemOperatorDomain.setService( serviceConverter.toDomain( source.getService() ) );
        registerBookingItemOperatorDomain.setRegisterBookingOperator( registerBookingOperatorConverter.toDomain( source.getRegisterBookingOperator() ) );
        registerBookingItemOperatorDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        registerBookingItemOperatorDomain.setGeneratedBy( source.getGeneratedBy() );

        return registerBookingItemOperatorDomain;
    }

    @Override
    public RegisterBookingItemOperatorEntity toEntity(RegisterBookingItemOperatorDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemOperatorEntity registerBookingItemOperatorEntity = new RegisterBookingItemOperatorEntity();

        registerBookingItemOperatorEntity.setId( source.getId() );
        registerBookingItemOperatorEntity.setDeleted( source.getDeleted() );
        registerBookingItemOperatorEntity.setEnabled( source.getEnabled() );
        registerBookingItemOperatorEntity.setMigrated( source.getMigrated() );
        registerBookingItemOperatorEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemOperatorEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemOperatorEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemOperatorEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemOperatorEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemOperatorEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemOperatorEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemOperatorEntity.setTenant( source.getTenant() );
        registerBookingItemOperatorEntity.setName( source.getName() );
        registerBookingItemOperatorEntity.setDescription( source.getDescription() );
        registerBookingItemOperatorEntity.setIdentify( source.getIdentify() );

        registerBookingItemOperatorEntity.setDateService( source.getDateService() );
        registerBookingItemOperatorEntity.setCantPax( source.getCantPax() );
        registerBookingItemOperatorEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingItemOperatorEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemOperatorEntity.setTc( source.getTc() );
        registerBookingItemOperatorEntity.setTcLocal( source.getTcLocal() );
        registerBookingItemOperatorEntity.setGuide( source.getGuide() );
        registerBookingItemOperatorEntity.setSequence( source.getSequence() );

        registerBookingItemOperatorEntity.setObservation( source.getObservation() );
        registerBookingItemOperatorEntity.setOtherService( source.getOtherService() );

        registerBookingItemOperatorEntity.setPvt( source.getPvt() );
        registerBookingItemOperatorEntity.setSic( source.getSic() );
        registerBookingItemOperatorEntity.setDate( source.getDate() );
        registerBookingItemOperatorEntity.setDateTime( source.getDateTime() );
        registerBookingItemOperatorEntity.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemOperatorEntity.setService( serviceConverter.toEntity( source.getService() ) );
        registerBookingItemOperatorEntity.setRegisterBookingOperator( registerBookingOperatorConverter.toEntity( source.getRegisterBookingOperator() ) );
        registerBookingItemOperatorEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        registerBookingItemOperatorEntity.setGeneratedBy(source.getGeneratedBy() );

        return registerBookingItemOperatorEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemOperatorDomain> toDomainAllSimpleToDomain(List<RegisterBookingItemOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();
            registerBookingItemOperatorDomain.setId(entity.getId());
            registerBookingItemOperatorDomain.setSequence(entity.getSequence());
            return registerBookingItemOperatorDomain;
        }).toList();
    }

    public List<RegisterBookingItemOperatorDomain> toDomainAllSimpleToDomainCaseOperationAvailable(List<RegisterBookingItemOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();
            registerBookingItemOperatorDomain.setId(entity.getId());
            registerBookingItemOperatorDomain.setDate(entity.getDate());
            registerBookingItemOperatorDomain.setDateTime(entity.getDateTime());
            registerBookingItemOperatorDomain.setName(entity.getName());
            registerBookingItemOperatorDomain.setDescription(entity.getDescription());
            registerBookingItemOperatorDomain.setGeneratedBy(entity.getGeneratedBy());
            registerBookingItemOperatorDomain.setObservation(entity.getObservation());
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                registerBookingItemOperatorDomain.setService( new ServiceDomain() );
                registerBookingItemOperatorDomain.getService().setId( entity.getService().getId() );
                registerBookingItemOperatorDomain.getService().setName( entity.getService().getName() );
                registerBookingItemOperatorDomain.getService().setWithoutCost( entity.getService().getWithoutCost() );
            }
            return registerBookingItemOperatorDomain;
        }).toList();
    }

    public List<RegisterBookingItemOperatorDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingItemOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();
            registerBookingItemOperatorDomain.setId( entity.getId() );
            registerBookingItemOperatorDomain.setGuide( entity.getGuide() );
            registerBookingItemOperatorDomain.setDateService( entity.getDateService() );
            registerBookingItemOperatorDomain.setDate( entity.getDate() );
            registerBookingItemOperatorDomain.setService( new ServiceDomain());
            registerBookingItemOperatorDomain.getService().setId(entity.getService().getId());
            registerBookingItemOperatorDomain.getService().setName(entity.getService().getName());
            registerBookingItemOperatorDomain.getService().setAbbrev(entity.getService().getAbbrev());
            registerBookingItemOperatorDomain.setObservation( entity.getObservation() );
            registerBookingItemOperatorDomain.setPvt(entity.getPvt());
            registerBookingItemOperatorDomain.setSic(entity.getSic());
            registerBookingItemOperatorDomain.setSequence(entity.getSequence());
            return registerBookingItemOperatorDomain;
        }).toList();
    }
}
