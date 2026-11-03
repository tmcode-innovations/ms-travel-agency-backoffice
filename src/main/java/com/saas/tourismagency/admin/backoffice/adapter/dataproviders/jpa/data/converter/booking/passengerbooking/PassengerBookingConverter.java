package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengerbooking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengergroup.PassengerGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinginstitution.RegisterBookingInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingrestaurant.RegisterBookingRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingroom.RegisterBookingRoomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerBookingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerBookingConverter implements Converter<PassengerBookingDomain, PassengerBookingEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PassengerGroupConverter passengerGroupConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private RegisterBookingOperatorConverter registerBookingOperatorConverter;

    @Autowired
    private RegisterBookingLodgingConverter registerBookingLodgingConverter;

    @Autowired
    private RegisterBookingGlampingConverter registerBookingGlampingConverter;

    @Autowired
    private RegisterBookingRestaurantConverter registerBookingRestaurantConverter;

    @Autowired
    private RegisterBookingLodgerConverter registerBookingLodgerConverter;

    @Autowired
    private RegisterBookingInstitutionConverter registerBookingInstitutionConverter;

    @Autowired
    private RegisterBookingRoomConverter registerBookingRoomConverter;

    @Override
    public PassengerBookingDomain toDomain(PassengerBookingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerBookingDomain passengerBookingDomain = new PassengerBookingDomain();

        passengerBookingDomain.setId( source.getId() );
        passengerBookingDomain.setDeleted( source.getDeleted() );
        passengerBookingDomain.setEnabled( source.getEnabled() );
        passengerBookingDomain.setMigrated( source.getMigrated() );
        passengerBookingDomain.setCreatedAt( source.getCreatedAt() );
        passengerBookingDomain.setUpdatedAt( source.getUpdatedAt() );
        passengerBookingDomain.setDeletedAt( source.getDeletedAt() );
        passengerBookingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        passengerBookingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        passengerBookingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        passengerBookingDomain.setIdentifierName( source.getIdentifierName() );
        passengerBookingDomain.setTenant( source.getTenant() );
        passengerBookingDomain.setName( source.getName() );
        passengerBookingDomain.setDescription( source.getDescription() );
        passengerBookingDomain.setIdentify( source.getIdentify() );
        passengerBookingDomain.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );
        passengerBookingDomain.setTypeRoomBooking( source.getTypeRoomBooking() );
        if (source.getPassengerGroup() != null &&
                source.getPassengerGroup().getId() != null &&
                !source.getPassengerGroup().getId().isEmpty()){
            passengerBookingDomain.setPassengerGroup( passengerGroupConverter.toDomain( source.getPassengerGroup() ) );
        }else{
            passengerBookingDomain.setPassengerGroup( null );
        }
        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingOperator( registerBookingOperatorConverter.toDomain( source.getRegisterBookingOperator() ) );
        }else{
            passengerBookingDomain.setRegisterBookingOperator( null );
        }
        if (source.getRegisterBookingRestaurant() != null &&
                source.getRegisterBookingRestaurant().getId() != null &&
                !source.getRegisterBookingRestaurant().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingRestaurant( registerBookingRestaurantConverter.toDomain( source.getRegisterBookingRestaurant() ) );
        }else{
            passengerBookingDomain.setRegisterBookingRestaurant( null );
        }
        if (source.getRegisterBookingLodging() != null &&
                source.getRegisterBookingLodging().getId() != null &&
                !source.getRegisterBookingLodging().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingLodging( registerBookingLodgingConverter.toDomain( source.getRegisterBookingLodging() ) );
        }else{
            passengerBookingDomain.setRegisterBookingLodging( null );
        }
        if (source.getRegisterBookingGlamping() != null &&
                source.getRegisterBookingGlamping().getId() != null &&
                !source.getRegisterBookingGlamping().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingGlamping( registerBookingGlampingConverter.toDomain( source.getRegisterBookingGlamping() ) );
        }else{
            passengerBookingDomain.setRegisterBookingGlamping( null );
        }
        if (source.getRegisterBookingLodger() != null &&
                source.getRegisterBookingLodger().getId() != null &&
                !source.getRegisterBookingLodger().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingLodger( registerBookingLodgerConverter.toDomain( source.getRegisterBookingLodger() ) );
        }else{
            passengerBookingDomain.setRegisterBookingLodger( null );
        }
        if (source.getRegisterBookingInstitution() != null &&
                source.getRegisterBookingInstitution().getId() != null &&
                !source.getRegisterBookingInstitution().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingInstitution( registerBookingInstitutionConverter.toDomain( source.getRegisterBookingInstitution() ) );
        }else{
            passengerBookingDomain.setRegisterBookingInstitution( null );
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()){
            passengerBookingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        }else{
            passengerBookingDomain.setRegisterGroup( null );
        }
        if (source.getRegisterBookingRoom() != null &&
                source.getRegisterBookingRoom().getId() != null &&
                !source.getRegisterBookingRoom().getId().isEmpty()){
            passengerBookingDomain.setRegisterBookingRoom( registerBookingRoomConverter.toDomain( source.getRegisterBookingRoom() ) );
        }else{
            passengerBookingDomain.setRegisterBookingRoom( null );
        }
        return passengerBookingDomain;
    }

    @Override
    public PassengerBookingEntity toEntity(PassengerBookingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerBookingEntity passengerBookingEntity = new PassengerBookingEntity();

        passengerBookingEntity.setId( source.getId() );
        passengerBookingEntity.setDeleted( source.getDeleted() );
        passengerBookingEntity.setEnabled( source.getEnabled() );
        passengerBookingEntity.setMigrated( source.getMigrated() );
        passengerBookingEntity.setCreatedAt( source.getCreatedAt() );
        passengerBookingEntity.setUpdatedAt( source.getUpdatedAt() );
        passengerBookingEntity.setDeletedAt( source.getDeletedAt() );
        passengerBookingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        passengerBookingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        passengerBookingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        passengerBookingEntity.setIdentifierName( source.getIdentifierName() );
        passengerBookingEntity.setTenant( source.getTenant() );
        passengerBookingEntity.setName( source.getName() );
        passengerBookingEntity.setDescription( source.getDescription() );
        passengerBookingEntity.setIdentify( source.getIdentify() );
        passengerBookingEntity.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );
        passengerBookingEntity.setTypeRoomBooking( source.getTypeRoomBooking() );
        if ( source.getPassengerGroup() != null ){
            passengerBookingEntity.setPassengerGroup( passengerGroupConverter.toEntity( source.getPassengerGroup() ) );
        }else{
            passengerBookingEntity.setPassengerGroup( null );
        }
        if ( source.getRegisterBookingOperator() != null ){
            passengerBookingEntity.setRegisterBookingOperator( registerBookingOperatorConverter.toEntity( source.getRegisterBookingOperator() ) );
        }else{
            passengerBookingEntity.setRegisterBookingOperator( null );
        }
        if ( source.getRegisterBookingRestaurant() != null ){
            passengerBookingEntity.setRegisterBookingRestaurant( registerBookingRestaurantConverter.toEntity( source.getRegisterBookingRestaurant() ) );
        }else{
            passengerBookingEntity.setRegisterBookingRestaurant( null );
        }
        if ( source.getRegisterBookingLodging() != null ){
            passengerBookingEntity.setRegisterBookingLodging( registerBookingLodgingConverter.toEntity( source.getRegisterBookingLodging() ) );
        }else{
            passengerBookingEntity.setRegisterBookingLodging( null );
        }
        if ( source.getRegisterBookingGlamping() != null ){
            passengerBookingEntity.setRegisterBookingGlamping( registerBookingGlampingConverter.toEntity( source.getRegisterBookingGlamping() ) );
        }else{
            passengerBookingEntity.setRegisterBookingGlamping( null );
        }
        if ( source.getRegisterBookingLodger() != null ){
            passengerBookingEntity.setRegisterBookingLodger( registerBookingLodgerConverter.toEntity( source.getRegisterBookingLodger() ) );
        }else{
            passengerBookingEntity.setRegisterBookingLodger( null );
        }
        if ( source.getRegisterBookingInstitution() != null ){
            passengerBookingEntity.setRegisterBookingInstitution( registerBookingInstitutionConverter.toEntity( source.getRegisterBookingInstitution() ) );
        }else{
            passengerBookingEntity.setRegisterBookingInstitution( null );
        }
        if ( source.getRegisterGroup() != null ){
            passengerBookingEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        }else{
            passengerBookingEntity.setRegisterGroup( null );
        }
        if ( source.getRegisterBookingRoom() != null ){
            passengerBookingEntity.setRegisterBookingRoom( registerBookingRoomConverter.toEntity( source.getRegisterBookingRoom() ) );
        }else{
            passengerBookingEntity.setRegisterBookingRoom( null );
        }

        return passengerBookingEntity;
    }

    public IdBaseDomain toDomainIdBase(PassengerBookingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PassengerBookingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PassengerBookingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
