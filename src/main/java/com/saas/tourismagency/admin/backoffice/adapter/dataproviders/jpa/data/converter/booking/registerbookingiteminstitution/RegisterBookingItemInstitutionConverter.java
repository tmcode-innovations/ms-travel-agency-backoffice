package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingiteminstitution;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinginstitution.RegisterBookingInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemInstitutionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemInstitutionConverter implements Converter<RegisterBookingItemInstitutionDomain, RegisterBookingItemInstitutionEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingInstitutionConverter registerBookingInstitutionConverter;

    @Override
    public RegisterBookingItemInstitutionDomain toDomain(RegisterBookingItemInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain = new RegisterBookingItemInstitutionDomain();

        registerBookingItemInstitutionDomain.setId( source.getId() );
        registerBookingItemInstitutionDomain.setDeleted( source.getDeleted() );
        registerBookingItemInstitutionDomain.setEnabled( source.getEnabled() );
        registerBookingItemInstitutionDomain.setMigrated( source.getMigrated() );
        registerBookingItemInstitutionDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemInstitutionDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemInstitutionDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemInstitutionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemInstitutionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemInstitutionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemInstitutionDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemInstitutionDomain.setTenant( source.getTenant() );
        registerBookingItemInstitutionDomain.setName( source.getName() );
        registerBookingItemInstitutionDomain.setDescription( source.getDescription() );
        registerBookingItemInstitutionDomain.setIdentify( source.getIdentify() );

        registerBookingItemInstitutionDomain.setDateService( source.getDateService() );
        registerBookingItemInstitutionDomain.setCantPax( source.getCantPax() );
        registerBookingItemInstitutionDomain.setSequence( source.getSequence() );

        registerBookingItemInstitutionDomain.setObservation( source.getObservation() );
        registerBookingItemInstitutionDomain.setOtherService( source.getOtherService() );

        registerBookingItemInstitutionDomain.setPvt( source.getPvt() );
        registerBookingItemInstitutionDomain.setSic( source.getSic() );
        registerBookingItemInstitutionDomain.setDate( source.getDate() );
        registerBookingItemInstitutionDomain.setDateTime( source.getDateTime() );
        registerBookingItemInstitutionDomain.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemInstitutionDomain.setService( serviceConverter.toDomain( source.getService() ) );
        registerBookingItemInstitutionDomain.setRegisterBookingInstitution( registerBookingInstitutionConverter.toDomain( source.getRegisterBookingInstitution() ) );
        registerBookingItemInstitutionDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingItemInstitutionDomain;
    }

    @Override
    public RegisterBookingItemInstitutionEntity toEntity(RegisterBookingItemInstitutionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemInstitutionEntity registerBookingItemInstitutionEntity = new RegisterBookingItemInstitutionEntity();

        registerBookingItemInstitutionEntity.setId( source.getId() );
        registerBookingItemInstitutionEntity.setDeleted( source.getDeleted() );
        registerBookingItemInstitutionEntity.setEnabled( source.getEnabled() );
        registerBookingItemInstitutionEntity.setMigrated( source.getMigrated() );
        registerBookingItemInstitutionEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemInstitutionEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemInstitutionEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemInstitutionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemInstitutionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemInstitutionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemInstitutionEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemInstitutionEntity.setTenant( source.getTenant() );
        registerBookingItemInstitutionEntity.setName( source.getName() );
        registerBookingItemInstitutionEntity.setDescription( source.getDescription() );
        registerBookingItemInstitutionEntity.setIdentify( source.getIdentify() );

        registerBookingItemInstitutionEntity.setDateService( source.getDateService() );
        registerBookingItemInstitutionEntity.setCantPax( source.getCantPax() );
        registerBookingItemInstitutionEntity.setSequence( source.getSequence() );

        registerBookingItemInstitutionEntity.setObservation( source.getObservation() );
        registerBookingItemInstitutionEntity.setOtherService( source.getOtherService() );

        registerBookingItemInstitutionEntity.setPvt( source.getPvt() );
        registerBookingItemInstitutionEntity.setSic( source.getSic() );
        registerBookingItemInstitutionEntity.setDate( source.getDate() );
        registerBookingItemInstitutionEntity.setDateTime( source.getDateTime() );
        registerBookingItemInstitutionEntity.setIdItineraryItemIdentify( source.getIdItineraryItemIdentify() );

        registerBookingItemInstitutionEntity.setService( serviceConverter.toEntity( source.getService() ) );
        registerBookingItemInstitutionEntity.setRegisterBookingInstitution( registerBookingInstitutionConverter.toEntity( source.getRegisterBookingInstitution() ) );
        registerBookingItemInstitutionEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemInstitutionEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemInstitutionDomain> toDomainAllSimpleToDomain(List<RegisterBookingItemInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain = new RegisterBookingItemInstitutionDomain();
            registerBookingItemInstitutionDomain.setId(entity.getId());
            return registerBookingItemInstitutionDomain;
        }).toList();
    }

    public List<RegisterBookingItemInstitutionDomain> toDomainAllSimpleToDomainCaseOperationAvailable(List<RegisterBookingItemInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain = new RegisterBookingItemInstitutionDomain();
            registerBookingItemInstitutionDomain.setId(entity.getId());
            registerBookingItemInstitutionDomain.setDate(entity.getDate());
            registerBookingItemInstitutionDomain.setName(entity.getName());
            registerBookingItemInstitutionDomain.setDescription(entity.getDescription());
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                registerBookingItemInstitutionDomain.setService( new ServiceDomain() );
                registerBookingItemInstitutionDomain.getService().setId( entity.getService().getId() );
                registerBookingItemInstitutionDomain.getService().setName( entity.getService().getName() );
            }
            return registerBookingItemInstitutionDomain;
        }).toList();
    }
}
