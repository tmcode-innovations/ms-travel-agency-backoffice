package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgerEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingLodgerConverter implements Converter<RegisterBookingLodgerDomain, RegisterBookingLodgerEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private NationalityConverter nationalityConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private SupplierOperatorConverter supplierOperatorConverter;

    @Autowired
    private SupplierLodgingConverter supplierLodgingConverter;

    @Override
    public RegisterBookingLodgerDomain toDomain(RegisterBookingLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = new RegisterBookingLodgerDomain();

        registerBookingLodgerDomain.setId( source.getId() );
        registerBookingLodgerDomain.setDeleted( source.getDeleted() );
        registerBookingLodgerDomain.setEnabled( source.getEnabled() );
        registerBookingLodgerDomain.setMigrated( source.getMigrated() );
        registerBookingLodgerDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingLodgerDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingLodgerDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingLodgerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingLodgerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingLodgerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingLodgerDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingLodgerDomain.setTenant( source.getTenant() );
        registerBookingLodgerDomain.setName( source.getName() );
        registerBookingLodgerDomain.setDescription( source.getDescription() );
        registerBookingLodgerDomain.setIdentify( source.getIdentify() );
        registerBookingLodgerDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingLodgerDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingLodgerDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingLodgerDomain.setArriveAir( source.getArriveAir() );
        registerBookingLodgerDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingLodgerDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingLodgerDomain.setDateAperture( source.getDateAperture() );
        registerBookingLodgerDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingLodgerDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingLodgerDomain.setConfirmation( source.getConfirmation() );
        registerBookingLodgerDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingLodgerDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingLodgerDomain.setPrePago( source.getPrePago() );
        registerBookingLodgerDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingLodgerDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingLodgerDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingLodgerDomain.setObservation( source.getObservation() );
        registerBookingLodgerDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingLodgerDomain.setCantPax( source.getCantPax() );
        registerBookingLodgerDomain.setCantFree( source.getCantFree() );
        registerBookingLodgerDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingLodgerDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingLodgerDomain.setTc( source.getTc() );
        registerBookingLodgerDomain.setTcLocal( source.getTcLocal() );
        registerBookingLodgerDomain.setGuide( source.getGuide() );
        registerBookingLodgerDomain.setTotalPax( source.getTotalPax() );
        registerBookingLodgerDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingLodgerDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingLodgerDomain.setStateBooking( source.getStateBooking() );
        registerBookingLodgerDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingLodgerDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingLodgerDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingLodgerDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingLodgerDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingLodgerDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingLodgerDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingLodgerDomain.setNumberService( source.getNumberService() );
        registerBookingLodgerDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingLodgerDomain.setDateEndActivity( source.getDateEndActivity() );

        registerBookingLodgerDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingLodgerDomain.setNoCupon(source.getNoCupon() );
        registerBookingLodgerDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingLodgerDomain.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingLodgerDomain.setCuponTc( source.getCuponTc() );
        registerBookingLodgerDomain.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingLodgerDomain.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingLodgerDomain.setOperatorArrive( supplierOperatorConverter.toDomain( source.getOperatorArrive() ) );
        }else{
            registerBookingLodgerDomain.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingLodgerDomain.setOperatorDeparture( supplierOperatorConverter.toDomain( source.getOperatorDeparture() ) );
        }else{
            registerBookingLodgerDomain.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingLodgerDomain.setSupplierLodging( supplierLodgingConverter.toDomain( source.getSupplierLodging() ) );
        }else{
            registerBookingLodgerDomain.setSupplierLodging( null );
        }

        registerBookingLodgerDomain.setInitTour( source.getInitTour() );
        registerBookingLodgerDomain.setEndTour( source.getEndTour() );

        registerBookingLodgerDomain.setInitHourTour( source.getInitHourTour() );
        registerBookingLodgerDomain.setEndHourTour( source.getEndHourTour() );

        registerBookingLodgerDomain.setCantSwb( source.getCantSwb() );
        registerBookingLodgerDomain.setCantSwbTcGuide( source.getCantSwbTcGuide() );
        registerBookingLodgerDomain.setCantSwbTcLocalGuide( source.getCantSwbTcLocalGuide() );
        registerBookingLodgerDomain.setCantSwbGuide( source.getCantSwbGuide() );
        registerBookingLodgerDomain.setCantTrp( source.getCantTrp() );
        registerBookingLodgerDomain.setCantCdp(source.getCantCdp() );
        registerBookingLodgerDomain.setCantQtp(source.getCantQtp() );
        registerBookingLodgerDomain.setCantSxtp( source.getCantSxtp() );
        registerBookingLodgerDomain.setCantTwin( source.getCantTwin() );
        registerBookingLodgerDomain.setCantTwinTcGuide( source.getCantTwinTcGuide() );
        registerBookingLodgerDomain.setCantTwinTcLocalGuide(source.getCantTwinTcLocalGuide() );
        registerBookingLodgerDomain.setCantTwinGuide(source.getCantTwinGuide() );
        registerBookingLodgerDomain.setCantMat( source.getCantMat() );
        registerBookingLodgerDomain.setNumberIngress( source.getNumberIngress() );

        return registerBookingLodgerDomain;
    }

    @Override
    public RegisterBookingLodgerEntity toEntity(RegisterBookingLodgerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgerEntity registerBookingLodgerEntity = new RegisterBookingLodgerEntity();

        registerBookingLodgerEntity.setId( source.getId() );
        registerBookingLodgerEntity.setDeleted( source.getDeleted() );
        registerBookingLodgerEntity.setEnabled( source.getEnabled() );
        registerBookingLodgerEntity.setMigrated( source.getMigrated() );
        registerBookingLodgerEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingLodgerEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingLodgerEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingLodgerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingLodgerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingLodgerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingLodgerEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingLodgerEntity.setTenant( source.getTenant() );
        registerBookingLodgerEntity.setName( source.getName() );
        registerBookingLodgerEntity.setDescription( source.getDescription() );
        registerBookingLodgerEntity.setIdentify( source.getIdentify() );
        registerBookingLodgerEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingLodgerEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingLodgerEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingLodgerEntity.setArriveAir( source.getArriveAir() );
        registerBookingLodgerEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingLodgerEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingLodgerEntity.setDateAperture( source.getDateAperture() );
        registerBookingLodgerEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingLodgerEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingLodgerEntity.setConfirmation( source.getConfirmation() );
        registerBookingLodgerEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingLodgerEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingLodgerEntity.setPrePago( source.getPrePago() );
        registerBookingLodgerEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingLodgerEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingLodgerEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingLodgerEntity.setObservation( source.getObservation() );
        registerBookingLodgerEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingLodgerEntity.setCantPax( source.getCantPax() );
        registerBookingLodgerEntity.setCantFree( source.getCantFree() );
        registerBookingLodgerEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingLodgerEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingLodgerEntity.setTc( source.getTc() );
        registerBookingLodgerEntity.setTcLocal( source.getTcLocal() );
        registerBookingLodgerEntity.setGuide( source.getGuide() );
        registerBookingLodgerEntity.setTotalPax( source.getTotalPax() );
        registerBookingLodgerEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingLodgerEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingLodgerEntity.setStateBooking( source.getStateBooking() );
        registerBookingLodgerEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingLodgerEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingLodgerEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingLodgerEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingLodgerEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingLodgerEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingLodgerEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingLodgerEntity.setNumberService( source.getNumberService() );
        registerBookingLodgerEntity.setDateInitActivity( source.getDateInitActivity() );
        registerBookingLodgerEntity.setDateEndActivity( source.getDateEndActivity() );

        registerBookingLodgerEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingLodgerEntity.setNoCupon(source.getNoCupon() );
        registerBookingLodgerEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingLodgerEntity.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingLodgerEntity.setCuponTc( source.getCuponTc() );
        registerBookingLodgerEntity.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingLodgerEntity.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingLodgerEntity.setOperatorArrive( supplierOperatorConverter.toEntity( source.getOperatorArrive() ) );
        }else{
            registerBookingLodgerEntity.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingLodgerEntity.setOperatorDeparture( supplierOperatorConverter.toEntity( source.getOperatorDeparture() ) );
        }else{
            registerBookingLodgerEntity.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingLodgerEntity.setSupplierLodging( supplierLodgingConverter.toEntity( source.getSupplierLodging() ) );
        }else{
            registerBookingLodgerEntity.setSupplierLodging( null );
        }

        registerBookingLodgerEntity.setInitTour( source.getInitTour() );
        registerBookingLodgerEntity.setEndTour( source.getEndTour() );

        registerBookingLodgerEntity.setInitHourTour( source.getInitHourTour() );
        registerBookingLodgerEntity.setEndHourTour( source.getEndHourTour() );

        registerBookingLodgerEntity.setCantSwb( source.getCantSwb() );
        registerBookingLodgerEntity.setCantSwbTcGuide( source.getCantSwbTcGuide() );
        registerBookingLodgerEntity.setCantSwbTcLocalGuide( source.getCantSwbTcLocalGuide() );
        registerBookingLodgerEntity.setCantSwbGuide( source.getCantSwbGuide() );
        registerBookingLodgerEntity.setCantTrp( source.getCantTrp() );
        registerBookingLodgerEntity.setCantCdp(source.getCantCdp() );
        registerBookingLodgerEntity.setCantQtp(source.getCantQtp() );
        registerBookingLodgerEntity.setCantSxtp( source.getCantSxtp() );
        registerBookingLodgerEntity.setCantTwin( source.getCantTwin() );
        registerBookingLodgerEntity.setCantTwinTcGuide( source.getCantTwinTcGuide() );
        registerBookingLodgerEntity.setCantTwinTcLocalGuide(source.getCantTwinTcLocalGuide() );
        registerBookingLodgerEntity.setCantTwinGuide(source.getCantTwinGuide() );
        registerBookingLodgerEntity.setCantMat( source.getCantMat() );
        registerBookingLodgerEntity.setNumberIngress( source.getNumberIngress() );


        return registerBookingLodgerEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingLodgerDomain> toDomainAllSimpleToDomainCaseListRegisterBookingLodger(List<RegisterBookingLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingLodgerDomain registerBookingLodgerDomain = new RegisterBookingLodgerDomain();
            registerBookingLodgerDomain.setId(entity.getId());
            registerBookingLodgerDomain.setIdentify(entity.getIdentify());
            registerBookingLodgerDomain.setCreatedAt(entity.getCreatedAt());
            registerBookingLodgerDomain.setStateBooking(entity.getStateBooking());
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                registerBookingLodgerDomain.setSupplierType(new SupplierTypeDomain());
                registerBookingLodgerDomain.getSupplierType().setId(entity.getSupplierType().getId());
                registerBookingLodgerDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                registerBookingLodgerDomain.setSupplier(new SupplierDomain());
                registerBookingLodgerDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingLodgerDomain.getSupplier().setName(entity.getSupplier().getName());
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()){
                    registerBookingLodgerDomain.getSupplier().setPartner(new PartnerDomain());
                    registerBookingLodgerDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        registerBookingLodgerDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                            registerBookingLodgerDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                registerBookingLodgerDomain.setRegisterGroup(new RegisterGroupDomain());
                registerBookingLodgerDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingLodgerDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                registerBookingLodgerDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return registerBookingLodgerDomain;
        }).toList();
    }

    public PageDTO<RegisterBookingLodgerDomain> toDomainPageCaseListRegisterBookingLodger(Page<RegisterBookingLodgerEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingLodgerDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterBookingLodger(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public RegisterBookingLodgerDomain toDomainIdSimpleDomainCaseValidationExistBooking(RegisterBookingLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = new RegisterBookingLodgerDomain();

        registerBookingLodgerDomain.setId( source.getId() );
        return registerBookingLodgerDomain;
    }

    public RegisterBookingLodgerDomain toDomainAllSimpleToDomainCaseCaseAllCupon(RegisterBookingLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = new RegisterBookingLodgerDomain();

        registerBookingLodgerDomain.setId( source.getId() );
        registerBookingLodgerDomain.setNoCupon( source.getNoCupon() );
        registerBookingLodgerDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingLodgerDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingLodgerDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingLodgerDomain.setCuponCantPaxAdult(source.getCuponCantPaxAdult());
        registerBookingLodgerDomain.setCuponCantPaxChildren(source.getCuponCantPaxChildren());
        registerBookingLodgerDomain.setCuponTc(source.getCuponTc());
        registerBookingLodgerDomain.setCuponTcLocal(source.getCuponTcLocal());
        registerBookingLodgerDomain.setGuide(source.getGuide());

        registerBookingLodgerDomain.setCantPaxAdult(source.getCantPaxAdult());
        registerBookingLodgerDomain.setCantPaxChildren(source.getCantPaxChildren());
        registerBookingLodgerDomain.setTc(source.getTc());
        registerBookingLodgerDomain.setTcLocal(source.getTcLocal());

        registerBookingLodgerDomain.setGastosExtrasNoIncluidos(source.getGastosExtrasNoIncluidos());

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            registerBookingLodgerDomain.setSupplierType( new SupplierTypeDomain() );
            registerBookingLodgerDomain.getSupplierType().setId(source.getSupplierType().getId());
        }
        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            registerBookingLodgerDomain.setSupplier( new SupplierDomain() );
            registerBookingLodgerDomain.getSupplier().setId(source.getSupplier().getId());
            registerBookingLodgerDomain.getSupplier().setName(source.getSupplier().getName());
            registerBookingLodgerDomain.getSupplier().setTypeInheritance( source.getSupplier().getTypeInheritance() );
            if (source.getSupplier().getPartner() != null &&
                    source.getSupplier().getPartner().getId() != null &&
                    !source.getSupplier().getPartner().getId().isEmpty()) {
                registerBookingLodgerDomain.getSupplier().setPartner( new PartnerDomain( ));
                registerBookingLodgerDomain.getSupplier().getPartner().setId(source.getSupplier().getPartner().getId());
                if (source.getSupplier().getPartner().getAddress() != null) {
                    registerBookingLodgerDomain.getSupplier().getPartner().setAddress( new AddressEmbeddedDomain() );
                    registerBookingLodgerDomain.getSupplier().getPartner().getAddress().setStreet(source.getSupplier().getPartner().getAddress().getStreet());
                    registerBookingLodgerDomain.getSupplier().getPartner().getAddress().setStreet2(source.getSupplier().getPartner().getAddress().getStreet2());
                    if (source.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                            source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                            !source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                        registerBookingLodgerDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                        registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                        registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                    }
                }
            }
        }
        if (source.getEmployee() != null &&
                source.getEmployee().getId() != null &&
                !source.getEmployee().getId().isEmpty()) {
            registerBookingLodgerDomain.setEmployee( new EmployeeDomain() );
            registerBookingLodgerDomain.getEmployee().setId(source.getEmployee().getId());
            if (source.getEmployee().getPerson() != null &&
                    source.getEmployee().getPerson().getId() != null &&
                    !source.getEmployee().getPerson().getId().isEmpty()) {
                registerBookingLodgerDomain.getEmployee().setPerson( new PersonDomain() );
                registerBookingLodgerDomain.getEmployee().getPerson().setId(source.getEmployee().getPerson().getId());
                registerBookingLodgerDomain.getEmployee().getPerson().setLastName(source.getEmployee().getPerson().getLastName());
                registerBookingLodgerDomain.getEmployee().getPerson().setFirstName(source.getEmployee().getPerson().getFirstName());
            }
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            registerBookingLodgerDomain.setRegisterGroup( new RegisterGroupDomain() );
            registerBookingLodgerDomain.getRegisterGroup().setId(source.getRegisterGroup().getId());
            registerBookingLodgerDomain.getRegisterGroup().setName(source.getRegisterGroup().getName());
        }
        return registerBookingLodgerDomain;
    }
}
