package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
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
public class RegisterBookingLodgingConverter implements Converter<RegisterBookingLodgingDomain, RegisterBookingLodgingEntity> {

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


    @Override
    public RegisterBookingLodgingDomain toDomain(RegisterBookingLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = new RegisterBookingLodgingDomain();

        registerBookingLodgingDomain.setId( source.getId() );
        registerBookingLodgingDomain.setDeleted( source.getDeleted() );
        registerBookingLodgingDomain.setEnabled( source.getEnabled() );
        registerBookingLodgingDomain.setMigrated( source.getMigrated() );
        registerBookingLodgingDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingLodgingDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingLodgingDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingLodgingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingLodgingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingLodgingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingLodgingDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingLodgingDomain.setTenant( source.getTenant() );
        registerBookingLodgingDomain.setName( source.getName() );
        registerBookingLodgingDomain.setDescription( source.getDescription() );
        registerBookingLodgingDomain.setIdentify( source.getIdentify() );
        registerBookingLodgingDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingLodgingDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingLodgingDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingLodgingDomain.setArriveAir( source.getArriveAir() );
        registerBookingLodgingDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingLodgingDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingLodgingDomain.setDateAperture( source.getDateAperture() );
        registerBookingLodgingDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingLodgingDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingLodgingDomain.setConfirmation( source.getConfirmation() );
        registerBookingLodgingDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingLodgingDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingLodgingDomain.setPrePago( source.getPrePago() );
        registerBookingLodgingDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingLodgingDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingLodgingDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingLodgingDomain.setObservation( source.getObservation() );
        registerBookingLodgingDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingLodgingDomain.setCantPax( source.getCantPax() );
        registerBookingLodgingDomain.setCantFree( source.getCantFree() );
        registerBookingLodgingDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingLodgingDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingLodgingDomain.setTc( source.getTc() );
        registerBookingLodgingDomain.setTcLocal( source.getTcLocal() );
        registerBookingLodgingDomain.setGuide( source.getGuide() );
        registerBookingLodgingDomain.setTotalPax( source.getTotalPax() );
        registerBookingLodgingDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingLodgingDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingLodgingDomain.setStateBooking( source.getStateBooking() );
        registerBookingLodgingDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingLodgingDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingLodgingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingLodgingDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingLodgingDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingLodgingDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingLodgingDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingLodgingDomain.setInitTour( source.getInitTour() );
        registerBookingLodgingDomain.setEndTour( source.getEndTour() );

        registerBookingLodgingDomain.setInitHourTour( source.getInitHourTour() );
        registerBookingLodgingDomain.setEndHourTour( source.getEndHourTour() );

        registerBookingLodgingDomain.setCantSwb( source.getCantSwb() );
        registerBookingLodgingDomain.setCantSwbTcGuide( source.getCantSwbTcGuide() );
        registerBookingLodgingDomain.setCantSwbTcLocalGuide( source.getCantSwbTcLocalGuide() );
        registerBookingLodgingDomain.setCantSwbGuide( source.getCantSwbGuide() );
        registerBookingLodgingDomain.setCantTrp( source.getCantTrp() );
        registerBookingLodgingDomain.setCantCdp(source.getCantCdp() );
        registerBookingLodgingDomain.setCantQtp(source.getCantQtp() );
        registerBookingLodgingDomain.setCantSxtp( source.getCantSxtp() );
        registerBookingLodgingDomain.setCantTwin( source.getCantTwin() );
        registerBookingLodgingDomain.setCantTwinTcGuide( source.getCantTwinTcGuide() );
        registerBookingLodgingDomain.setCantTwinGuide( source.getCantTwinGuide() );
        registerBookingLodgingDomain.setCantTwinTcLocalGuide(source.getCantTwinTcLocalGuide() );
        registerBookingLodgingDomain.setCantMat( source.getCantMat() );
        registerBookingLodgingDomain.setNumberIngress( source.getNumberIngress() );

        return registerBookingLodgingDomain;
    }

    @Override
    public RegisterBookingLodgingEntity toEntity(RegisterBookingLodgingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgingEntity registerBookingLodgingEntity = new RegisterBookingLodgingEntity();

        registerBookingLodgingEntity.setId( source.getId() );
        registerBookingLodgingEntity.setDeleted( source.getDeleted() );
        registerBookingLodgingEntity.setEnabled( source.getEnabled() );
        registerBookingLodgingEntity.setMigrated( source.getMigrated() );
        registerBookingLodgingEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingLodgingEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingLodgingEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingLodgingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingLodgingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingLodgingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingLodgingEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingLodgingEntity.setTenant( source.getTenant() );
        registerBookingLodgingEntity.setName( source.getName() );
        registerBookingLodgingEntity.setDescription( source.getDescription() );
        registerBookingLodgingEntity.setIdentify( source.getIdentify() );
        registerBookingLodgingEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingLodgingEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingLodgingEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingLodgingEntity.setArriveAir( source.getArriveAir() );
        registerBookingLodgingEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingLodgingEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingLodgingEntity.setDateAperture( source.getDateAperture() );
        registerBookingLodgingEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingLodgingEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingLodgingEntity.setConfirmation( source.getConfirmation() );
        registerBookingLodgingEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingLodgingEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingLodgingEntity.setPrePago( source.getPrePago() );
        registerBookingLodgingEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingLodgingEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingLodgingEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingLodgingEntity.setObservation( source.getObservation() );
        registerBookingLodgingEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingLodgingEntity.setCantPax( source.getCantPax() );
        registerBookingLodgingEntity.setCantFree( source.getCantFree() );
        registerBookingLodgingEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingLodgingEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingLodgingEntity.setTc( source.getTc() );
        registerBookingLodgingEntity.setTcLocal( source.getTcLocal() );
        registerBookingLodgingEntity.setGuide( source.getGuide() );
        registerBookingLodgingEntity.setTotalPax( source.getTotalPax() );
        registerBookingLodgingEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingLodgingEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingLodgingEntity.setStateBooking( source.getStateBooking() );
        registerBookingLodgingEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingLodgingEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingLodgingEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingLodgingEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingLodgingEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingLodgingEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingLodgingEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingLodgingEntity.setInitTour( source.getInitTour() );
        registerBookingLodgingEntity.setEndTour( source.getEndTour() );

        registerBookingLodgingEntity.setInitHourTour( source.getInitHourTour() );
        registerBookingLodgingEntity.setEndHourTour( source.getEndHourTour() );

        registerBookingLodgingEntity.setCantSwb( source.getCantSwb() );
        registerBookingLodgingEntity.setCantSwbTcGuide( source.getCantSwbTcGuide() );
        registerBookingLodgingEntity.setCantSwbTcLocalGuide( source.getCantSwbTcLocalGuide() );
        registerBookingLodgingEntity.setCantSwbGuide( source.getCantSwbGuide() );
        registerBookingLodgingEntity.setCantTrp( source.getCantTrp() );
        registerBookingLodgingEntity.setCantCdp(source.getCantCdp() );
        registerBookingLodgingEntity.setCantQtp(source.getCantQtp() );
        registerBookingLodgingEntity.setCantSxtp( source.getCantSxtp() );
        registerBookingLodgingEntity.setCantTwin( source.getCantTwin() );
        registerBookingLodgingEntity.setCantTwinTcGuide( source.getCantTwinTcGuide() );
        registerBookingLodgingEntity.setCantTwinTcLocalGuide(source.getCantTwinTcLocalGuide() );
        registerBookingLodgingEntity.setCantTwinGuide(source.getCantTwinGuide() );
        registerBookingLodgingEntity.setCantMat( source.getCantMat() );
        registerBookingLodgingEntity.setNumberIngress( source.getNumberIngress() );

        return registerBookingLodgingEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingLodgingDomain> toDomainAllSimpleToDomainCaseListRegisterBookingLodging(List<RegisterBookingLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingLodgingDomain registerBookingLodgingDomain = new RegisterBookingLodgingDomain();
            registerBookingLodgingDomain.setId(entity.getId());
            registerBookingLodgingDomain.setIdentify(entity.getIdentify());
            registerBookingLodgingDomain.setCreatedAt(entity.getCreatedAt());
            registerBookingLodgingDomain.setStateBooking(entity.getStateBooking());
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                registerBookingLodgingDomain.setSupplierType(new SupplierTypeDomain());
                registerBookingLodgingDomain.getSupplierType().setId(entity.getSupplierType().getId());
                registerBookingLodgingDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                registerBookingLodgingDomain.setSupplier(new SupplierDomain());
                registerBookingLodgingDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingLodgingDomain.getSupplier().setName(entity.getSupplier().getName());
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()){
                    registerBookingLodgingDomain.getSupplier().setPartner(new PartnerDomain());
                    registerBookingLodgingDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        registerBookingLodgingDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                            registerBookingLodgingDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingLodgingDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingLodgingDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                registerBookingLodgingDomain.setRegisterGroup(new RegisterGroupDomain());
                registerBookingLodgingDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingLodgingDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                registerBookingLodgingDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return registerBookingLodgingDomain;
        }).toList();
    }

    public PageDTO<RegisterBookingLodgingDomain> toDomainPageCaseListRegisterBookingLodging(Page<RegisterBookingLodgingEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingLodgingDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterBookingLodging(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public RegisterBookingLodgingDomain toDomainIdSimpleDomainCaseValidationExistBooking(RegisterBookingLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = new RegisterBookingLodgingDomain();

        registerBookingLodgingDomain.setId( source.getId() );
        return registerBookingLodgingDomain;
    }

    public RegisterBookingLodgingDomain toDomainAllSimpleToDomainCaseCaseAllCupon(RegisterBookingLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = new RegisterBookingLodgingDomain();

        registerBookingLodgingDomain.setId( source.getId() );

        registerBookingLodgingDomain.setGuide(source.getGuide());
        registerBookingLodgingDomain.setCantPaxAdult(source.getCantPaxAdult());
        registerBookingLodgingDomain.setCantPaxChildren(source.getCantPaxChildren());
        registerBookingLodgingDomain.setTc(source.getTc());
        registerBookingLodgingDomain.setTcLocal(source.getTcLocal());

        registerBookingLodgingDomain.setGastosExtrasNoIncluidos(source.getGastosExtrasNoIncluidos());

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            registerBookingLodgingDomain.setSupplierType( new SupplierTypeDomain() );
            registerBookingLodgingDomain.getSupplierType().setId(source.getSupplierType().getId());
        }
        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            registerBookingLodgingDomain.setSupplier( new SupplierDomain() );
            registerBookingLodgingDomain.getSupplier().setId(source.getSupplier().getId());
            registerBookingLodgingDomain.getSupplier().setName(source.getSupplier().getName());
            registerBookingLodgingDomain.getSupplier().setTypeInheritance( source.getSupplier().getTypeInheritance() );
            if (source.getSupplier().getPartner() != null &&
                    source.getSupplier().getPartner().getId() != null &&
                    !source.getSupplier().getPartner().getId().isEmpty()) {
                registerBookingLodgingDomain.getSupplier().setPartner( new PartnerDomain( ));
                registerBookingLodgingDomain.getSupplier().getPartner().setId(source.getSupplier().getPartner().getId());
                if (source.getSupplier().getPartner().getAddress() != null) {
                    registerBookingLodgingDomain.getSupplier().getPartner().setAddress( new AddressEmbeddedDomain() );
                    registerBookingLodgingDomain.getSupplier().getPartner().getAddress().setStreet(source.getSupplier().getPartner().getAddress().getStreet());
                    registerBookingLodgingDomain.getSupplier().getPartner().getAddress().setStreet2(source.getSupplier().getPartner().getAddress().getStreet2());
                    if (source.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                            source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                            !source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                        registerBookingLodgingDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                        registerBookingLodgingDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                        registerBookingLodgingDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                    }
                }
            }
        }
        if (source.getEmployee() != null &&
                source.getEmployee().getId() != null &&
                !source.getEmployee().getId().isEmpty()) {
            registerBookingLodgingDomain.setEmployee( new EmployeeDomain() );
            registerBookingLodgingDomain.getEmployee().setId(source.getEmployee().getId());
            if (source.getEmployee().getPerson() != null &&
                    source.getEmployee().getPerson().getId() != null &&
                    !source.getEmployee().getPerson().getId().isEmpty()) {
                registerBookingLodgingDomain.getEmployee().setPerson( new PersonDomain() );
                registerBookingLodgingDomain.getEmployee().getPerson().setId(source.getEmployee().getPerson().getId());
                registerBookingLodgingDomain.getEmployee().getPerson().setLastName(source.getEmployee().getPerson().getLastName());
                registerBookingLodgingDomain.getEmployee().getPerson().setFirstName(source.getEmployee().getPerson().getFirstName());
            }
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            registerBookingLodgingDomain.setRegisterGroup( new RegisterGroupDomain() );
            registerBookingLodgingDomain.getRegisterGroup().setId(source.getRegisterGroup().getId());
            registerBookingLodgingDomain.getRegisterGroup().setName(source.getRegisterGroup().getName());
        }

        return registerBookingLodgingDomain;
    }
}
