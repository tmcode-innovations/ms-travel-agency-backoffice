package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgerlodging;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerLodgingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemLodgerLodgingConverter implements Converter<RegisterBookingItemLodgerLodgingDomain, RegisterBookingItemLodgerLodgingEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private LodgingCategoryConverter lodgingCategoryConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingLodgerConverter registerBookingLodgerConverter;

    @Override
    public RegisterBookingItemLodgerLodgingDomain toDomain(RegisterBookingItemLodgerLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = new RegisterBookingItemLodgerLodgingDomain();

        registerBookingItemLodgerLodgingDomain.setId( source.getId() );
        registerBookingItemLodgerLodgingDomain.setDeleted( source.getDeleted() );
        registerBookingItemLodgerLodgingDomain.setEnabled( source.getEnabled() );
        registerBookingItemLodgerLodgingDomain.setMigrated( source.getMigrated() );
        registerBookingItemLodgerLodgingDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgerLodgingDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgerLodgingDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgerLodgingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemLodgerLodgingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemLodgerLodgingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemLodgerLodgingDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgerLodgingDomain.setTenant( source.getTenant() );
        registerBookingItemLodgerLodgingDomain.setName( source.getName() );
        registerBookingItemLodgerLodgingDomain.setDescription( source.getDescription() );
        registerBookingItemLodgerLodgingDomain.setIdentify( source.getIdentify() );

        registerBookingItemLodgerLodgingDomain.setNoCupon( source.getNoCupon() );
        registerBookingItemLodgerLodgingDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );

        registerBookingItemLodgerLodgingDomain.setDateFrom( source.getDateFrom() );
        registerBookingItemLodgerLodgingDomain.setDateTo( source.getDateTo() );

        registerBookingItemLodgerLodgingDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingItemLodgerLodgingDomain.setNoNight( source.getNoNight() );
        registerBookingItemLodgerLodgingDomain.setNoNightTcGuide( source.getNoNightTcGuide() );
        registerBookingItemLodgerLodgingDomain.setNoNightTcLocalGuide( source.getNoNightTcLocalGuide() );
        registerBookingItemLodgerLodgingDomain.setNoNightGuide( source.getNoNightGuide() );
        registerBookingItemLodgerLodgingDomain.setCantCb( source.getCantCb() );
        registerBookingItemLodgerLodgingDomain.setCantAb( source.getCantAb() );
        registerBookingItemLodgerLodgingDomain.setCantBreakfastBuffet( source.getCantBreakfastBuffet() );
        registerBookingItemLodgerLodgingDomain.setCantDinner( source.getCantDinner() );
        registerBookingItemLodgerLodgingDomain.setCantLunch( source.getCantLunch() );
        registerBookingItemLodgerLodgingDomain.setCantBoxLunch( source.getCantBoxLunch() );
        registerBookingItemLodgerLodgingDomain.setCantTcGuideBreakfastBuffet( source.getCantTcGuideBreakfastBuffet() );
        registerBookingItemLodgerLodgingDomain.setCantTcGuideDinner( source.getCantTcGuideDinner() );
        registerBookingItemLodgerLodgingDomain.setCantTcGuideLunch( source.getCantTcGuideLunch() );
        registerBookingItemLodgerLodgingDomain.setCantTcGuideBoxLunch( source.getCantTcGuideBoxLunch() );
        registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBreakfastBuffet( source.getCantTcLocalGuideBreakfastBuffet() );
        registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideDinner( source.getCantTcLocalGuideDinner() );
        registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideLunch( source.getCantTcLocalGuideLunch() );
        registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBoxLunch( source.getCantTcLocalGuideBoxLunch() );
        registerBookingItemLodgerLodgingDomain.setCantGuideBreakfastBuffet( source.getCantGuideBreakfastBuffet() );
        registerBookingItemLodgerLodgingDomain.setCantGuideDinner( source.getCantGuideDinner() );
        registerBookingItemLodgerLodgingDomain.setCantGuideLunch( source.getCantGuideLunch() );
        registerBookingItemLodgerLodgingDomain.setCantGuideBoxLunch( source.getCantGuideBoxLunch() );

        registerBookingItemLodgerLodgingDomain.setObservation( source.getObservation() );
        registerBookingItemLodgerLodgingDomain.setCategory( source.getCategory() );


        registerBookingItemLodgerLodgingDomain.setTcLocal( source.getTcLocal() );
        registerBookingItemLodgerLodgingDomain.setTc( source.getTc() );
        registerBookingItemLodgerLodgingDomain.setGuide( source.getGuide() );
        registerBookingItemLodgerLodgingDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemLodgerLodgingDomain.setCantPaxAdult( source.getCantPaxAdult() );

        registerBookingItemLodgerLodgingDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        registerBookingItemLodgerLodgingDomain.setRegisterBookingLodger( registerBookingLodgerConverter.toDomain( source.getRegisterBookingLodger() ) );
        registerBookingItemLodgerLodgingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingItemLodgerLodgingDomain;
    }

    @Override
    public RegisterBookingItemLodgerLodgingEntity toEntity(RegisterBookingItemLodgerLodgingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgerLodgingEntity registerBookingItemLodgerLodgingEntity = new RegisterBookingItemLodgerLodgingEntity();

        registerBookingItemLodgerLodgingEntity.setId( source.getId() );
        registerBookingItemLodgerLodgingEntity.setDeleted( source.getDeleted() );
        registerBookingItemLodgerLodgingEntity.setEnabled( source.getEnabled() );
        registerBookingItemLodgerLodgingEntity.setMigrated( source.getMigrated() );
        registerBookingItemLodgerLodgingEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgerLodgingEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgerLodgingEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgerLodgingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemLodgerLodgingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemLodgerLodgingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemLodgerLodgingEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgerLodgingEntity.setTenant( source.getTenant() );
        registerBookingItemLodgerLodgingEntity.setName( source.getName() );
        registerBookingItemLodgerLodgingEntity.setDescription( source.getDescription() );
        registerBookingItemLodgerLodgingEntity.setIdentify( source.getIdentify() );

        registerBookingItemLodgerLodgingEntity.setNoCupon( source.getNoCupon() );
        registerBookingItemLodgerLodgingEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );

        registerBookingItemLodgerLodgingEntity.setDateFrom( source.getDateFrom() );
        registerBookingItemLodgerLodgingEntity.setDateTo( source.getDateTo() );

        registerBookingItemLodgerLodgingEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingItemLodgerLodgingEntity.setNoNight( source.getNoNight() );
        registerBookingItemLodgerLodgingEntity.setNoNightTcGuide( source.getNoNightTcGuide() );
        registerBookingItemLodgerLodgingEntity.setNoNightTcLocalGuide( source.getNoNightTcLocalGuide() );
        registerBookingItemLodgerLodgingEntity.setNoNightGuide( source.getNoNightGuide() );
        registerBookingItemLodgerLodgingEntity.setCantCb( source.getCantCb() );
        registerBookingItemLodgerLodgingEntity.setCantAb( source.getCantAb() );
        registerBookingItemLodgerLodgingEntity.setCantBreakfastBuffet( source.getCantBreakfastBuffet() );
        registerBookingItemLodgerLodgingEntity.setCantDinner( source.getCantDinner() );
        registerBookingItemLodgerLodgingEntity.setCantLunch( source.getCantLunch() );
        registerBookingItemLodgerLodgingEntity.setCantBoxLunch( source.getCantBoxLunch() );
        registerBookingItemLodgerLodgingEntity.setCantTcGuideBreakfastBuffet( source.getCantTcGuideBreakfastBuffet() );
        registerBookingItemLodgerLodgingEntity.setCantTcGuideDinner( source.getCantTcGuideDinner() );
        registerBookingItemLodgerLodgingEntity.setCantTcGuideLunch( source.getCantTcGuideLunch() );
        registerBookingItemLodgerLodgingEntity.setCantTcGuideBoxLunch( source.getCantTcGuideBoxLunch() );
        registerBookingItemLodgerLodgingEntity.setCantTcLocalGuideBreakfastBuffet( source.getCantTcLocalGuideBreakfastBuffet() );
        registerBookingItemLodgerLodgingEntity.setCantTcLocalGuideDinner( source.getCantTcLocalGuideDinner() );
        registerBookingItemLodgerLodgingEntity.setCantTcLocalGuideLunch( source.getCantTcLocalGuideLunch() );
        registerBookingItemLodgerLodgingEntity.setCantTcLocalGuideBoxLunch( source.getCantTcLocalGuideBoxLunch() );
        registerBookingItemLodgerLodgingEntity.setCantGuideBreakfastBuffet( source.getCantGuideBreakfastBuffet() );
        registerBookingItemLodgerLodgingEntity.setCantGuideDinner( source.getCantGuideDinner() );
        registerBookingItemLodgerLodgingEntity.setCantGuideLunch( source.getCantGuideLunch() );
        registerBookingItemLodgerLodgingEntity.setCantGuideBoxLunch( source.getCantGuideBoxLunch() );

        registerBookingItemLodgerLodgingEntity.setObservation( source.getObservation() );
        registerBookingItemLodgerLodgingEntity.setCategory( source.getCategory() );


        registerBookingItemLodgerLodgingEntity.setTcLocal( source.getTcLocal() );
        registerBookingItemLodgerLodgingEntity.setTc( source.getTc() );
        registerBookingItemLodgerLodgingEntity.setGuide( source.getGuide() );
        registerBookingItemLodgerLodgingEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemLodgerLodgingEntity.setCantPaxAdult( source.getCantPaxAdult() );

        registerBookingItemLodgerLodgingEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        registerBookingItemLodgerLodgingEntity.setRegisterBookingLodger( registerBookingLodgerConverter.toEntity( source.getRegisterBookingLodger() ) );
        registerBookingItemLodgerLodgingEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemLodgerLodgingEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemLodgerLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemLodgerLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemLodgerLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemLodgerLodgingDomain> toDomainAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(List<RegisterBookingItemLodgerLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = new RegisterBookingItemLodgerLodgingDomain();
            registerBookingItemLodgerLodgingDomain.setId( entity.getId() );
            return registerBookingItemLodgerLodgingDomain;
        }).toList();
    }

    public List<RegisterBookingItemLodgerLodgingDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingItemLodgerLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = new RegisterBookingItemLodgerLodgingDomain();
            registerBookingItemLodgerLodgingDomain.setId( entity.getId() );
            registerBookingItemLodgerLodgingDomain.setNoCupon( entity.getNoCupon() );
            registerBookingItemLodgerLodgingDomain.setNoCuponPreImpress( entity.getNoCuponPreImpress() );
            registerBookingItemLodgerLodgingDomain.setDateFrom( entity.getDateFrom() );
            registerBookingItemLodgerLodgingDomain.setDateTo(entity.getDateTo());
            registerBookingItemLodgerLodgingDomain.setDateTimeInitService( entity.getDateTimeInitService() );

            registerBookingItemLodgerLodgingDomain.setTcLocal(entity.getTcLocal());
            registerBookingItemLodgerLodgingDomain.setCantPaxAdult(entity.getCantPaxAdult());
            registerBookingItemLodgerLodgingDomain.setCantPaxChildren(entity.getCantPaxChildren());
            registerBookingItemLodgerLodgingDomain.setTc(entity.getTc());
            registerBookingItemLodgerLodgingDomain.setGuide(entity.getGuide());

            registerBookingItemLodgerLodgingDomain.setNoNight(entity.getNoNight());
            registerBookingItemLodgerLodgingDomain.setNoNightGuide(entity.getNoNight());
            registerBookingItemLodgerLodgingDomain.setNoNightTcGuide(entity.getNoNightTcGuide());
            registerBookingItemLodgerLodgingDomain.setNoNightTcLocalGuide(entity.getNoNightTcLocalGuide());

            registerBookingItemLodgerLodgingDomain.setCantCb(entity.getCantCb());
            registerBookingItemLodgerLodgingDomain.setCantAb(entity.getCantAb());
            registerBookingItemLodgerLodgingDomain.setCantBreakfastBuffet(entity.getCantBreakfastBuffet());
            registerBookingItemLodgerLodgingDomain.setCantDinner(entity.getCantDinner());
            registerBookingItemLodgerLodgingDomain.setCantLunch(entity.getCantLunch());
            registerBookingItemLodgerLodgingDomain.setCantBoxLunch(entity.getCantBoxLunch());
            registerBookingItemLodgerLodgingDomain.setCantTcGuideBreakfastBuffet(entity.getCantTcGuideBreakfastBuffet());
            registerBookingItemLodgerLodgingDomain.setCantTcGuideDinner(entity.getCantTcGuideDinner());
            registerBookingItemLodgerLodgingDomain.setCantTcGuideLunch(entity.getCantTcGuideLunch());
            registerBookingItemLodgerLodgingDomain.setCantTcGuideBoxLunch(entity.getCantTcGuideBoxLunch());
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBreakfastBuffet(entity.getCantTcLocalGuideBreakfastBuffet());
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideDinner(entity.getCantTcLocalGuideDinner());
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideLunch(entity.getCantTcLocalGuideLunch());
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBoxLunch(entity.getCantTcLocalGuideBoxLunch());
            registerBookingItemLodgerLodgingDomain.setCantGuideBreakfastBuffet(entity.getCantGuideBreakfastBuffet());
            registerBookingItemLodgerLodgingDomain.setCantGuideDinner(entity.getCantGuideDinner());
            registerBookingItemLodgerLodgingDomain.setCantGuideLunch(entity.getCantGuideLunch());
            registerBookingItemLodgerLodgingDomain.setCantGuideBoxLunch(entity.getCantGuideBoxLunch());
            registerBookingItemLodgerLodgingDomain.setObservation(entity.getObservation());
            registerBookingItemLodgerLodgingDomain.setCategory(entity.getCategory());

            if (entity.getRegisterBookingLodger() != null &&
                    entity.getRegisterBookingLodger().getId() != null &&
                    !entity.getRegisterBookingLodger().getId().isEmpty()) {
                registerBookingItemLodgerLodgingDomain.setRegisterBookingLodger( new RegisterBookingLodgerDomain() );
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setId(entity.getRegisterBookingLodger().getId());

                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantSwb(entity.getRegisterBookingLodger().getCantSwb());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantSwbTcGuide(entity.getRegisterBookingLodger().getCantSwbTcGuide());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantSwbTcLocalGuide(entity.getRegisterBookingLodger().getCantSwbTcLocalGuide());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantSwbGuide(entity.getRegisterBookingLodger().getCantSwbGuide());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantTrp(entity.getRegisterBookingLodger().getCantTrp());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantCdp(entity.getRegisterBookingLodger().getCantCdp());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantQtp(entity.getRegisterBookingLodger().getCantQtp());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantSxtp(entity.getRegisterBookingLodger().getCantSxtp());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantTwin(entity.getRegisterBookingLodger().getCantTwin());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantTwinTcGuide(entity.getRegisterBookingLodger().getCantTwinTcGuide());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantTwinTcLocalGuide(entity.getRegisterBookingLodger().getCantTwinTcLocalGuide());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantTwinGuide(entity.getRegisterBookingLodger().getCantTwinGuide());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setCantMat(entity.getRegisterBookingLodger().getCantMat());
                registerBookingItemLodgerLodgingDomain.getRegisterBookingLodger().setNumberIngress(entity.getRegisterBookingLodger().getNumberIngress());
            }

            return registerBookingItemLodgerLodgingDomain;
        }).toList();
    }
}
