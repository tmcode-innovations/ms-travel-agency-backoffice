package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingrestaurant;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRestaurantDomain;
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
public class RegisterBookingRestaurantConverter implements Converter<RegisterBookingRestaurantDomain, RegisterBookingRestaurantEntity> {

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
    public RegisterBookingRestaurantDomain toDomain(RegisterBookingRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = new RegisterBookingRestaurantDomain();

        registerBookingRestaurantDomain.setId( source.getId() );
        registerBookingRestaurantDomain.setDeleted( source.getDeleted() );
        registerBookingRestaurantDomain.setEnabled( source.getEnabled() );
        registerBookingRestaurantDomain.setMigrated( source.getMigrated() );
        registerBookingRestaurantDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingRestaurantDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingRestaurantDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingRestaurantDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingRestaurantDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingRestaurantDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingRestaurantDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingRestaurantDomain.setTenant( source.getTenant() );
        registerBookingRestaurantDomain.setName( source.getName() );
        registerBookingRestaurantDomain.setDescription( source.getDescription() );
        registerBookingRestaurantDomain.setIdentify( source.getIdentify() );
        registerBookingRestaurantDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingRestaurantDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingRestaurantDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingRestaurantDomain.setArriveAir( source.getArriveAir() );
        registerBookingRestaurantDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingRestaurantDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingRestaurantDomain.setDateAperture( source.getDateAperture() );
        registerBookingRestaurantDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingRestaurantDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingRestaurantDomain.setConfirmation( source.getConfirmation() );
        registerBookingRestaurantDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingRestaurantDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingRestaurantDomain.setPrePago( source.getPrePago() );
        registerBookingRestaurantDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingRestaurantDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingRestaurantDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingRestaurantDomain.setObservation( source.getObservation() );
        registerBookingRestaurantDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingRestaurantDomain.setCantPax( source.getCantPax() );
        registerBookingRestaurantDomain.setCantFree( source.getCantFree() );
        registerBookingRestaurantDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingRestaurantDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingRestaurantDomain.setTc( source.getTc() );
        registerBookingRestaurantDomain.setTcLocal( source.getTcLocal() );
        registerBookingRestaurantDomain.setGuide( source.getGuide() );
        registerBookingRestaurantDomain.setTotalPax( source.getTotalPax() );
        registerBookingRestaurantDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingRestaurantDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingRestaurantDomain.setStateBooking( source.getStateBooking() );
        registerBookingRestaurantDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingRestaurantDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingRestaurantDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingRestaurantDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingRestaurantDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingRestaurantDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingRestaurantDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingRestaurantDomain.setNumberService( source.getNumberService() );
        registerBookingRestaurantDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingRestaurantDomain.setDateEndActivity( source.getDateEndActivity() );

        registerBookingRestaurantDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingRestaurantDomain.setNoCupon(source.getNoCupon() );
        registerBookingRestaurantDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingRestaurantDomain.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingRestaurantDomain.setCuponTc( source.getCuponTc() );
        registerBookingRestaurantDomain.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingRestaurantDomain.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingRestaurantDomain.setOperatorArrive( supplierOperatorConverter.toDomain( source.getOperatorArrive() ) );
        }else{
            registerBookingRestaurantDomain.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingRestaurantDomain.setOperatorDeparture( supplierOperatorConverter.toDomain( source.getOperatorDeparture() ) );
        }else{
            registerBookingRestaurantDomain.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingRestaurantDomain.setSupplierLodging( supplierLodgingConverter.toDomain( source.getSupplierLodging() ) );
        }else{
            registerBookingRestaurantDomain.setSupplierLodging( null );
        }

        return registerBookingRestaurantDomain;
    }

    @Override
    public RegisterBookingRestaurantEntity toEntity(RegisterBookingRestaurantDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingRestaurantEntity registerBookingRestaurantEntity = new RegisterBookingRestaurantEntity();

        registerBookingRestaurantEntity.setId( source.getId() );
        registerBookingRestaurantEntity.setDeleted( source.getDeleted() );
        registerBookingRestaurantEntity.setEnabled( source.getEnabled() );
        registerBookingRestaurantEntity.setMigrated( source.getMigrated() );
        registerBookingRestaurantEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingRestaurantEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingRestaurantEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingRestaurantEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingRestaurantEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingRestaurantEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingRestaurantEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingRestaurantEntity.setTenant( source.getTenant() );
        registerBookingRestaurantEntity.setName( source.getName() );
        registerBookingRestaurantEntity.setDescription( source.getDescription() );
        registerBookingRestaurantEntity.setIdentify( source.getIdentify() );
        registerBookingRestaurantEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingRestaurantEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingRestaurantEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingRestaurantEntity.setArriveAir( source.getArriveAir() );
        registerBookingRestaurantEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingRestaurantEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingRestaurantEntity.setDateAperture( source.getDateAperture() );
        registerBookingRestaurantEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingRestaurantEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingRestaurantEntity.setConfirmation( source.getConfirmation() );
        registerBookingRestaurantEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingRestaurantEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingRestaurantEntity.setPrePago( source.getPrePago() );
        registerBookingRestaurantEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingRestaurantEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingRestaurantEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingRestaurantEntity.setObservation( source.getObservation() );
        registerBookingRestaurantEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingRestaurantEntity.setCantPax( source.getCantPax() );
        registerBookingRestaurantEntity.setCantFree( source.getCantFree() );
        registerBookingRestaurantEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingRestaurantEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingRestaurantEntity.setTc( source.getTc() );
        registerBookingRestaurantEntity.setTcLocal( source.getTcLocal() );
        registerBookingRestaurantEntity.setGuide( source.getGuide() );
        registerBookingRestaurantEntity.setTotalPax( source.getTotalPax() );
        registerBookingRestaurantEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingRestaurantEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingRestaurantEntity.setStateBooking( source.getStateBooking() );
        registerBookingRestaurantEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingRestaurantEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingRestaurantEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingRestaurantEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingRestaurantEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingRestaurantEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingRestaurantEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingRestaurantEntity.setNumberService( source.getNumberService() );
        registerBookingRestaurantEntity.setDateInitActivity( source.getDateInitActivity() );
        registerBookingRestaurantEntity.setDateEndActivity( source.getDateEndActivity() );

        registerBookingRestaurantEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingRestaurantEntity.setNoCupon(source.getNoCupon() );
        registerBookingRestaurantEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingRestaurantEntity.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingRestaurantEntity.setCuponTc( source.getCuponTc() );
        registerBookingRestaurantEntity.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingRestaurantEntity.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingRestaurantEntity.setOperatorArrive( supplierOperatorConverter.toEntity( source.getOperatorArrive() ) );
        }else{
            registerBookingRestaurantEntity.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingRestaurantEntity.setOperatorDeparture( supplierOperatorConverter.toEntity( source.getOperatorDeparture() ) );
        }else{
            registerBookingRestaurantEntity.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingRestaurantEntity.setSupplierLodging( supplierLodgingConverter.toEntity( source.getSupplierLodging() ) );
        }else{
            registerBookingRestaurantEntity.setSupplierLodging( null );
        }

        return registerBookingRestaurantEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingRestaurantDomain> toDomainAllSimpleToDomainCaseListRegisterBookingRestaurant(List<RegisterBookingRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingRestaurantDomain registerBookingRestaurantDomain = new RegisterBookingRestaurantDomain();
            registerBookingRestaurantDomain.setId(entity.getId());
            registerBookingRestaurantDomain.setIdentify(entity.getIdentify());
            registerBookingRestaurantDomain.setCreatedAt(entity.getCreatedAt());
            registerBookingRestaurantDomain.setStateBooking(entity.getStateBooking());
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                registerBookingRestaurantDomain.setSupplierType(new SupplierTypeDomain());
                registerBookingRestaurantDomain.getSupplierType().setId(entity.getSupplierType().getId());
                registerBookingRestaurantDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                registerBookingRestaurantDomain.setSupplier(new SupplierDomain());
                registerBookingRestaurantDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingRestaurantDomain.getSupplier().setName(entity.getSupplier().getName());
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()){
                    registerBookingRestaurantDomain.getSupplier().setPartner(new PartnerDomain());
                    registerBookingRestaurantDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        registerBookingRestaurantDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                            registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                registerBookingRestaurantDomain.setRegisterGroup(new RegisterGroupDomain());
                registerBookingRestaurantDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingRestaurantDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                registerBookingRestaurantDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return registerBookingRestaurantDomain;
        }).toList();
    }

    public PageDTO<RegisterBookingRestaurantDomain> toDomainPageCaseListRegisterBookingRestaurant(Page<RegisterBookingRestaurantEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingRestaurantDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterBookingRestaurant(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public RegisterBookingRestaurantDomain toDomainIdSimpleDomainCaseValidationExistBooking(RegisterBookingRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = new RegisterBookingRestaurantDomain();

        registerBookingRestaurantDomain.setId( source.getId() );
        return registerBookingRestaurantDomain;
    }

    public RegisterBookingRestaurantDomain toDomainAllSimpleToDomainCaseCaseAllCupon(RegisterBookingRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = new RegisterBookingRestaurantDomain();

        registerBookingRestaurantDomain.setId( source.getId() );
        registerBookingRestaurantDomain.setNoCupon( source.getNoCupon() );
        registerBookingRestaurantDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingRestaurantDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingRestaurantDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingRestaurantDomain.setCuponCantPaxAdult(source.getCuponCantPaxAdult());
        registerBookingRestaurantDomain.setCuponCantPaxChildren(source.getCuponCantPaxChildren());
        registerBookingRestaurantDomain.setCuponTc(source.getCuponTc());
        registerBookingRestaurantDomain.setCuponTcLocal(source.getCuponTcLocal());
        registerBookingRestaurantDomain.setGuide(source.getGuide());

        registerBookingRestaurantDomain.setCantPaxAdult(source.getCantPaxAdult());
        registerBookingRestaurantDomain.setCantPaxChildren(source.getCantPaxChildren());
        registerBookingRestaurantDomain.setTc(source.getTc());
        registerBookingRestaurantDomain.setTcLocal(source.getTcLocal());

        registerBookingRestaurantDomain.setGastosExtrasNoIncluidos(source.getGastosExtrasNoIncluidos());

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            registerBookingRestaurantDomain.setSupplierType( new SupplierTypeDomain() );
            registerBookingRestaurantDomain.getSupplierType().setId(source.getSupplierType().getId());
        }
        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            registerBookingRestaurantDomain.setSupplier( new SupplierDomain() );
            registerBookingRestaurantDomain.getSupplier().setId(source.getSupplier().getId());
            registerBookingRestaurantDomain.getSupplier().setName(source.getSupplier().getName());
            registerBookingRestaurantDomain.getSupplier().setTypeInheritance( source.getSupplier().getTypeInheritance() );
            if (source.getSupplier().getPartner() != null &&
                    source.getSupplier().getPartner().getId() != null &&
                    !source.getSupplier().getPartner().getId().isEmpty()) {
                registerBookingRestaurantDomain.getSupplier().setPartner( new PartnerDomain( ));
                registerBookingRestaurantDomain.getSupplier().getPartner().setId(source.getSupplier().getPartner().getId());
                if (source.getSupplier().getPartner().getAddress() != null) {
                    registerBookingRestaurantDomain.getSupplier().getPartner().setAddress( new AddressEmbeddedDomain() );
                    registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().setStreet(source.getSupplier().getPartner().getAddress().getStreet());
                    registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().setStreet2(source.getSupplier().getPartner().getAddress().getStreet2());
                    if (source.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                            source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                            !source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                        registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                        registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                        registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                    }
                }
            }
        }
        if (source.getEmployee() != null &&
                source.getEmployee().getId() != null &&
                !source.getEmployee().getId().isEmpty()) {
            registerBookingRestaurantDomain.setEmployee( new EmployeeDomain() );
            registerBookingRestaurantDomain.getEmployee().setId(source.getEmployee().getId());
            if (source.getEmployee().getPerson() != null &&
                    source.getEmployee().getPerson().getId() != null &&
                    !source.getEmployee().getPerson().getId().isEmpty()) {
                registerBookingRestaurantDomain.getEmployee().setPerson( new PersonDomain() );
                registerBookingRestaurantDomain.getEmployee().getPerson().setId(source.getEmployee().getPerson().getId());
                registerBookingRestaurantDomain.getEmployee().getPerson().setLastName(source.getEmployee().getPerson().getLastName());
                registerBookingRestaurantDomain.getEmployee().getPerson().setFirstName(source.getEmployee().getPerson().getFirstName());
            }
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            registerBookingRestaurantDomain.setRegisterGroup( new RegisterGroupDomain() );
            registerBookingRestaurantDomain.getRegisterGroup().setId(source.getRegisterGroup().getId());
            registerBookingRestaurantDomain.getRegisterGroup().setName(source.getRegisterGroup().getName());
        }

        return registerBookingRestaurantDomain;
    }
}
