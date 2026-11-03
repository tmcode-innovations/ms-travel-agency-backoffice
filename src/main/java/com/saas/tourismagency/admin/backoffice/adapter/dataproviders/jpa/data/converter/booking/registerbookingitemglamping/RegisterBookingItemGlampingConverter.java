package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemglamping;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemGlampingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingItemGlampingConverter implements Converter<RegisterBookingItemGlampingDomain, RegisterBookingItemGlampingEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private LodgingCategoryConverter lodgingCategoryConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingGlampingConverter registerBookingGlampingConverter;

    @Override
    public RegisterBookingItemGlampingDomain toDomain(RegisterBookingItemGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = new RegisterBookingItemGlampingDomain();

        registerBookingItemGlampingDomain.setId( source.getId() );
        registerBookingItemGlampingDomain.setDeleted( source.getDeleted() );
        registerBookingItemGlampingDomain.setEnabled( source.getEnabled() );
        registerBookingItemGlampingDomain.setMigrated( source.getMigrated() );
        registerBookingItemGlampingDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingItemGlampingDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemGlampingDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingItemGlampingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingItemGlampingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingItemGlampingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingItemGlampingDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingItemGlampingDomain.setTenant( source.getTenant() );
        registerBookingItemGlampingDomain.setName( source.getName() );
        registerBookingItemGlampingDomain.setDescription( source.getDescription() );
        registerBookingItemGlampingDomain.setIdentify( source.getIdentify() );

        registerBookingItemGlampingDomain.setNoCupon( source.getNoCupon() );
        registerBookingItemGlampingDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );

        registerBookingItemGlampingDomain.setDateFrom( source.getDateFrom() );
        registerBookingItemGlampingDomain.setDateTo( source.getDateTo() );

        registerBookingItemGlampingDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingItemGlampingDomain.setNoNight( source.getNoNight() );
        registerBookingItemGlampingDomain.setNoNightTcGuide( source.getNoNightTcGuide() );
        registerBookingItemGlampingDomain.setNoNightTcLocalGuide( source.getNoNightTcLocalGuide() );
        registerBookingItemGlampingDomain.setCantCb( source.getCantCb() );
        registerBookingItemGlampingDomain.setCantAb( source.getCantAb() );
        registerBookingItemGlampingDomain.setCantBreakfastBuffet( source.getCantBreakfastBuffet() );
        registerBookingItemGlampingDomain.setCantDinner( source.getCantDinner() );
        registerBookingItemGlampingDomain.setCantLunch( source.getCantLunch() );
        registerBookingItemGlampingDomain.setCantTcGuideBreakfastBuffet( source.getCantTcGuideBreakfastBuffet() );
        registerBookingItemGlampingDomain.setCantTcGuideDinner( source.getCantTcGuideDinner() );
        registerBookingItemGlampingDomain.setCantTcGuideLunch( source.getCantTcGuideLunch() );
        registerBookingItemGlampingDomain.setCantTcLocalGuideBreakfastBuffet( source.getCantTcLocalGuideBreakfastBuffet() );
        registerBookingItemGlampingDomain.setCantTcLocalGuideDinner( source.getCantTcLocalGuideDinner() );
        registerBookingItemGlampingDomain.setCantTcLocalGuideLunch( source.getCantTcLocalGuideLunch() );
        registerBookingItemGlampingDomain.setCantGuideBreakfastBuffet( source.getCantGuideBreakfastBuffet() );
        registerBookingItemGlampingDomain.setCantGuideDinner( source.getCantGuideDinner() );
        registerBookingItemGlampingDomain.setCantGuideLunch( source.getCantGuideLunch() );

        registerBookingItemGlampingDomain.setObservation( source.getObservation() );
        registerBookingItemGlampingDomain.setCategory( source.getCategory() );


        registerBookingItemGlampingDomain.setTcLocal( source.getTcLocal() );
        registerBookingItemGlampingDomain.setTc( source.getTc() );
        registerBookingItemGlampingDomain.setGuide( source.getGuide() );
        registerBookingItemGlampingDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemGlampingDomain.setCantPaxAdult( source.getCantPaxAdult() );

        registerBookingItemGlampingDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        registerBookingItemGlampingDomain.setRegisterBookingGlamping( registerBookingGlampingConverter.toDomain( source.getRegisterBookingGlamping() ) );
        registerBookingItemGlampingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return registerBookingItemGlampingDomain;
    }

    @Override
    public RegisterBookingItemGlampingEntity toEntity(RegisterBookingItemGlampingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingItemGlampingEntity registerBookingItemGlampingEntity = new RegisterBookingItemGlampingEntity();

        registerBookingItemGlampingEntity.setId( source.getId() );
        registerBookingItemGlampingEntity.setDeleted( source.getDeleted() );
        registerBookingItemGlampingEntity.setEnabled( source.getEnabled() );
        registerBookingItemGlampingEntity.setMigrated( source.getMigrated() );
        registerBookingItemGlampingEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingItemGlampingEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingItemGlampingEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingItemGlampingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingItemGlampingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingItemGlampingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingItemGlampingEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingItemGlampingEntity.setTenant( source.getTenant() );
        registerBookingItemGlampingEntity.setName( source.getName() );
        registerBookingItemGlampingEntity.setDescription( source.getDescription() );
        registerBookingItemGlampingEntity.setIdentify( source.getIdentify() );

        registerBookingItemGlampingEntity.setNoCupon( source.getNoCupon() );
        registerBookingItemGlampingEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );

        registerBookingItemGlampingEntity.setDateFrom( source.getDateFrom() );
        registerBookingItemGlampingEntity.setDateTo( source.getDateTo() );

        registerBookingItemGlampingEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingItemGlampingEntity.setNoNight( source.getNoNight() );
        registerBookingItemGlampingEntity.setNoNightTcGuide( source.getNoNightTcGuide() );
        registerBookingItemGlampingEntity.setNoNightTcLocalGuide( source.getNoNightTcLocalGuide() );
        registerBookingItemGlampingEntity.setCantCb( source.getCantCb() );
        registerBookingItemGlampingEntity.setCantAb( source.getCantAb() );
        registerBookingItemGlampingEntity.setCantBreakfastBuffet( source.getCantBreakfastBuffet() );
        registerBookingItemGlampingEntity.setCantDinner( source.getCantDinner() );
        registerBookingItemGlampingEntity.setCantLunch( source.getCantLunch() );
        registerBookingItemGlampingEntity.setCantTcGuideBreakfastBuffet( source.getCantTcGuideBreakfastBuffet() );
        registerBookingItemGlampingEntity.setCantTcGuideDinner( source.getCantTcGuideDinner() );
        registerBookingItemGlampingEntity.setCantTcGuideLunch( source.getCantTcGuideLunch() );
        registerBookingItemGlampingEntity.setCantTcLocalGuideBreakfastBuffet( source.getCantTcLocalGuideBreakfastBuffet() );
        registerBookingItemGlampingEntity.setCantTcLocalGuideDinner( source.getCantTcLocalGuideDinner() );
        registerBookingItemGlampingEntity.setCantTcLocalGuideLunch( source.getCantTcLocalGuideLunch() );
        registerBookingItemGlampingEntity.setCantGuideBreakfastBuffet( source.getCantGuideBreakfastBuffet() );
        registerBookingItemGlampingEntity.setCantGuideDinner( source.getCantGuideDinner() );
        registerBookingItemGlampingEntity.setCantGuideLunch( source.getCantGuideLunch() );

        registerBookingItemGlampingEntity.setObservation( source.getObservation() );
        registerBookingItemGlampingEntity.setCategory( source.getCategory() );


        registerBookingItemGlampingEntity.setTcLocal( source.getTcLocal() );
        registerBookingItemGlampingEntity.setTc( source.getTc() );
        registerBookingItemGlampingEntity.setGuide( source.getGuide() );
        registerBookingItemGlampingEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingItemGlampingEntity.setCantPaxAdult( source.getCantPaxAdult() );

        registerBookingItemGlampingEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        registerBookingItemGlampingEntity.setRegisterBookingGlamping( registerBookingGlampingConverter.toEntity( source.getRegisterBookingGlamping() ) );
        registerBookingItemGlampingEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return registerBookingItemGlampingEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingItemGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingItemGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingItemGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
