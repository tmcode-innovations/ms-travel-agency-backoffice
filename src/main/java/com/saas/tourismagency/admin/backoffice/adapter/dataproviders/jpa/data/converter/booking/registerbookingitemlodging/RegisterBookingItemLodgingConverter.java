package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodging;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemLodgingConverter implements Converter<RegisterBookingItemLodgingDomain, RegisterBookingItemLodgingEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private LodgingCategoryConverter lodgingCategoryConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingLodgingConverter registerBookingLodgingConverter;

    @Override
    public RegisterBookingItemLodgingDomain toDomain(RegisterBookingItemLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = new RegisterBookingItemLodgingDomain();

        registerBookingItemLodgingDomain.setId( source.getId() );
        registerBookingItemLodgingDomain.setDeleted( source.getDeleted() );
        registerBookingItemLodgingDomain.setEnabled( source.getEnabled() );
        registerBookingItemLodgingDomain.setMigrated( source.getMigrated() );
        registerBookingItemLodgingDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgingDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgingDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemLodgingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemLodgingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemLodgingDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgingDomain.setTenant( source.getTenant() );
        registerBookingItemLodgingDomain.setName( source.getName() );
        registerBookingItemLodgingDomain.setDescription( source.getDescription() );
        registerBookingItemLodgingDomain.setIdentify( source.getIdentify() );

        registerBookingItemLodgingDomain.setNoCupon( source.getNoCupon() );
        registerBookingItemLodgingDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );

        registerBookingItemLodgingDomain.setDateFrom( source.getDateFrom() );
        registerBookingItemLodgingDomain.setDateTo( source.getDateTo() );

        registerBookingItemLodgingDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingItemLodgingDomain.setNoNight( source.getNoNight() );
        registerBookingItemLodgingDomain.setNoNightTcGuide( source.getNoNightTcGuide() );
        registerBookingItemLodgingDomain.setNoNightTcLocalGuide( source.getNoNightTcLocalGuide() );
        registerBookingItemLodgingDomain.setNoNightGuide( source.getNoNightGuide() );
        registerBookingItemLodgingDomain.setCantCb( source.getCantCb() );
        registerBookingItemLodgingDomain.setCantAb( source.getCantAb() );
        registerBookingItemLodgingDomain.setCantBreakfastBuffet( source.getCantBreakfastBuffet() );
        registerBookingItemLodgingDomain.setCantDinner( source.getCantDinner() );
        registerBookingItemLodgingDomain.setCantLunch( source.getCantLunch() );
        registerBookingItemLodgingDomain.setCantBoxLunch( source.getCantBoxLunch() );
        registerBookingItemLodgingDomain.setCantTcGuideBreakfastBuffet( source.getCantTcGuideBreakfastBuffet() );
        registerBookingItemLodgingDomain.setCantTcGuideDinner( source.getCantTcGuideDinner() );
        registerBookingItemLodgingDomain.setCantTcGuideLunch( source.getCantTcGuideLunch() );
        registerBookingItemLodgingDomain.setCantTcGuideBoxLunch( source.getCantTcGuideBoxLunch() );
        registerBookingItemLodgingDomain.setCantTcLocalGuideBreakfastBuffet( source.getCantTcLocalGuideBreakfastBuffet() );
        registerBookingItemLodgingDomain.setCantTcLocalGuideDinner( source.getCantTcLocalGuideDinner() );
        registerBookingItemLodgingDomain.setCantTcLocalGuideLunch( source.getCantTcLocalGuideLunch() );
        registerBookingItemLodgingDomain.setCantTcLocalGuideBoxLunch( source.getCantTcLocalGuideBoxLunch() );
        registerBookingItemLodgingDomain.setCantGuideBreakfastBuffet( source.getCantGuideBreakfastBuffet() );
        registerBookingItemLodgingDomain.setCantGuideDinner( source.getCantGuideDinner() );
        registerBookingItemLodgingDomain.setCantGuideLunch( source.getCantGuideLunch() );
        registerBookingItemLodgingDomain.setCantGuideBoxLunch( source.getCantGuideBoxLunch() );

        registerBookingItemLodgingDomain.setObservation( source.getObservation() );
        registerBookingItemLodgingDomain.setCategory( source.getCategory() );


        registerBookingItemLodgingDomain.setTcLocal( source.getTcLocal() );
        registerBookingItemLodgingDomain.setTc( source.getTc() );
        registerBookingItemLodgingDomain.setGuide( source.getGuide() );
        registerBookingItemLodgingDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemLodgingDomain.setCantPaxAdult( source.getCantPaxAdult() );

        registerBookingItemLodgingDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        registerBookingItemLodgingDomain.setRegisterBookingLodging( registerBookingLodgingConverter.toDomain( source.getRegisterBookingLodging() ) );
        registerBookingItemLodgingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingItemLodgingDomain;
    }

    @Override
    public RegisterBookingItemLodgingEntity toEntity(RegisterBookingItemLodgingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemLodgingEntity registerBookingItemLodgingEntity = new RegisterBookingItemLodgingEntity();

        registerBookingItemLodgingEntity.setId( source.getId() );
        registerBookingItemLodgingEntity.setDeleted( source.getDeleted() );
        registerBookingItemLodgingEntity.setEnabled( source.getEnabled() );
        registerBookingItemLodgingEntity.setMigrated( source.getMigrated() );
        registerBookingItemLodgingEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemLodgingEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemLodgingEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemLodgingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemLodgingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemLodgingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemLodgingEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemLodgingEntity.setTenant( source.getTenant() );
        registerBookingItemLodgingEntity.setName( source.getName() );
        registerBookingItemLodgingEntity.setDescription( source.getDescription() );
        registerBookingItemLodgingEntity.setIdentify( source.getIdentify() );

        registerBookingItemLodgingEntity.setNoCupon( source.getNoCupon() );
        registerBookingItemLodgingEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );

        registerBookingItemLodgingEntity.setDateFrom( source.getDateFrom() );
        registerBookingItemLodgingEntity.setDateTo( source.getDateTo() );

        registerBookingItemLodgingEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingItemLodgingEntity.setNoNight( source.getNoNight() );
        registerBookingItemLodgingEntity.setNoNightTcGuide( source.getNoNightTcGuide() );
        registerBookingItemLodgingEntity.setNoNightTcLocalGuide( source.getNoNightTcLocalGuide() );
        registerBookingItemLodgingEntity.setNoNightGuide( source.getNoNightGuide() );
        registerBookingItemLodgingEntity.setCantCb( source.getCantCb() );
        registerBookingItemLodgingEntity.setCantAb( source.getCantAb() );
        registerBookingItemLodgingEntity.setCantBreakfastBuffet( source.getCantBreakfastBuffet() );
        registerBookingItemLodgingEntity.setCantDinner( source.getCantDinner() );
        registerBookingItemLodgingEntity.setCantLunch( source.getCantLunch() );
        registerBookingItemLodgingEntity.setCantBoxLunch( source.getCantBoxLunch() );
        registerBookingItemLodgingEntity.setCantTcGuideBreakfastBuffet( source.getCantTcGuideBreakfastBuffet() );
        registerBookingItemLodgingEntity.setCantTcGuideDinner( source.getCantTcGuideDinner() );
        registerBookingItemLodgingEntity.setCantTcGuideLunch( source.getCantTcGuideLunch() );
        registerBookingItemLodgingEntity.setCantTcGuideBoxLunch( source.getCantTcGuideBoxLunch() );
        registerBookingItemLodgingEntity.setCantTcLocalGuideBreakfastBuffet( source.getCantTcLocalGuideBreakfastBuffet() );
        registerBookingItemLodgingEntity.setCantTcLocalGuideDinner( source.getCantTcLocalGuideDinner() );
        registerBookingItemLodgingEntity.setCantTcLocalGuideLunch( source.getCantTcLocalGuideLunch() );
        registerBookingItemLodgingEntity.setCantTcLocalGuideBoxLunch( source.getCantTcLocalGuideBoxLunch() );
        registerBookingItemLodgingEntity.setCantGuideBreakfastBuffet( source.getCantGuideBreakfastBuffet() );
        registerBookingItemLodgingEntity.setCantGuideDinner( source.getCantGuideDinner() );
        registerBookingItemLodgingEntity.setCantGuideLunch( source.getCantGuideLunch() );
        registerBookingItemLodgingEntity.setCantGuideBoxLunch( source.getCantGuideBoxLunch() );

        registerBookingItemLodgingEntity.setObservation( source.getObservation() );
        registerBookingItemLodgingEntity.setCategory( source.getCategory() );


        registerBookingItemLodgingEntity.setTcLocal( source.getTcLocal() );
        registerBookingItemLodgingEntity.setTc( source.getTc() );
        registerBookingItemLodgingEntity.setGuide( source.getGuide() );
        registerBookingItemLodgingEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemLodgingEntity.setCantPaxAdult( source.getCantPaxAdult() );

        registerBookingItemLodgingEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        registerBookingItemLodgingEntity.setRegisterBookingLodging( registerBookingLodgingConverter.toEntity( source.getRegisterBookingLodging() ) );
        registerBookingItemLodgingEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemLodgingEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingItemLodgingDomain> toDomainAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(List<RegisterBookingItemLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = new RegisterBookingItemLodgingDomain();
            registerBookingItemLodgingDomain.setId( entity.getId() );
            return registerBookingItemLodgingDomain;
        }).toList();
    }

    public List<RegisterBookingItemLodgingDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingItemLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = new RegisterBookingItemLodgingDomain();
            registerBookingItemLodgingDomain.setId( entity.getId() );
            registerBookingItemLodgingDomain.setNoCupon( entity.getNoCupon() );
            registerBookingItemLodgingDomain.setNoCuponPreImpress( entity.getNoCuponPreImpress() );
            registerBookingItemLodgingDomain.setDateFrom( entity.getDateFrom() );
            registerBookingItemLodgingDomain.setDateTo(entity.getDateTo());
            registerBookingItemLodgingDomain.setDateTimeInitService( entity.getDateTimeInitService() );

            registerBookingItemLodgingDomain.setTcLocal(entity.getTcLocal());
            registerBookingItemLodgingDomain.setCantPaxAdult(entity.getCantPaxAdult());
            registerBookingItemLodgingDomain.setCantPaxChildren(entity.getCantPaxChildren());
            registerBookingItemLodgingDomain.setTc(entity.getTc());
            registerBookingItemLodgingDomain.setGuide(entity.getGuide());

            registerBookingItemLodgingDomain.setNoNight(entity.getNoNight());
            registerBookingItemLodgingDomain.setNoNightGuide(entity.getNoNight());
            registerBookingItemLodgingDomain.setNoNightTcGuide(entity.getNoNightTcGuide());
            registerBookingItemLodgingDomain.setNoNightTcLocalGuide(entity.getNoNightTcLocalGuide());

            registerBookingItemLodgingDomain.setCantCb(entity.getCantCb());
            registerBookingItemLodgingDomain.setCantAb(entity.getCantAb());
            registerBookingItemLodgingDomain.setCantBreakfastBuffet(entity.getCantBreakfastBuffet());
            registerBookingItemLodgingDomain.setCantDinner(entity.getCantDinner());
            registerBookingItemLodgingDomain.setCantLunch(entity.getCantLunch());
            registerBookingItemLodgingDomain.setCantBoxLunch(entity.getCantBoxLunch());
            registerBookingItemLodgingDomain.setCantTcGuideBreakfastBuffet(entity.getCantTcGuideBreakfastBuffet());
            registerBookingItemLodgingDomain.setCantTcGuideDinner(entity.getCantTcGuideDinner());
            registerBookingItemLodgingDomain.setCantTcGuideLunch(entity.getCantTcGuideLunch());
            registerBookingItemLodgingDomain.setCantTcGuideBoxLunch(entity.getCantTcGuideBoxLunch());
            registerBookingItemLodgingDomain.setCantTcLocalGuideBreakfastBuffet(entity.getCantTcLocalGuideBreakfastBuffet());
            registerBookingItemLodgingDomain.setCantTcLocalGuideDinner(entity.getCantTcLocalGuideDinner());
            registerBookingItemLodgingDomain.setCantTcLocalGuideLunch(entity.getCantTcLocalGuideLunch());
            registerBookingItemLodgingDomain.setCantTcLocalGuideBoxLunch(entity.getCantTcLocalGuideBoxLunch());
            registerBookingItemLodgingDomain.setCantGuideBreakfastBuffet(entity.getCantGuideBreakfastBuffet());
            registerBookingItemLodgingDomain.setCantGuideDinner(entity.getCantGuideDinner());
            registerBookingItemLodgingDomain.setCantGuideLunch(entity.getCantGuideLunch());
            registerBookingItemLodgingDomain.setCantGuideBoxLunch(entity.getCantGuideBoxLunch());
            registerBookingItemLodgingDomain.setObservation(entity.getObservation());
            registerBookingItemLodgingDomain.setCategory(entity.getCategory());

            if (entity.getRegisterBookingLodging() != null &&
                    entity.getRegisterBookingLodging().getId() != null &&
                    !entity.getRegisterBookingLodging().getId().isEmpty()) {
                registerBookingItemLodgingDomain.setRegisterBookingLodging( new RegisterBookingLodgingDomain() );
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setId(entity.getRegisterBookingLodging().getId());

                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantSwb(entity.getRegisterBookingLodging().getCantSwb());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantSwbTcGuide(entity.getRegisterBookingLodging().getCantSwbTcGuide());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantSwbTcLocalGuide(entity.getRegisterBookingLodging().getCantSwbTcLocalGuide());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantSwbGuide(entity.getRegisterBookingLodging().getCantSwbGuide());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantTrp(entity.getRegisterBookingLodging().getCantTrp());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantCdp(entity.getRegisterBookingLodging().getCantCdp());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantQtp(entity.getRegisterBookingLodging().getCantQtp());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantSxtp(entity.getRegisterBookingLodging().getCantSxtp());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantTwin(entity.getRegisterBookingLodging().getCantTwin());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantTwinTcGuide(entity.getRegisterBookingLodging().getCantTwinTcGuide());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantTwinTcLocalGuide(entity.getRegisterBookingLodging().getCantTwinTcLocalGuide());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantTwinGuide(entity.getRegisterBookingLodging().getCantTwinGuide());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setCantMat(entity.getRegisterBookingLodging().getCantMat());
                registerBookingItemLodgingDomain.getRegisterBookingLodging().setNumberIngress(entity.getRegisterBookingLodging().getNumberIngress());
            }

            return registerBookingItemLodgingDomain;
        }).toList();
    }
}
