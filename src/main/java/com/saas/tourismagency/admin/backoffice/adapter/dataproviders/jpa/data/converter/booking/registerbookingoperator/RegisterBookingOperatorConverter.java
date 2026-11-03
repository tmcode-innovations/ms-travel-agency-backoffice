package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator;

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
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
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
public class RegisterBookingOperatorConverter implements Converter<RegisterBookingOperatorDomain, RegisterBookingOperatorEntity> {

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
    public RegisterBookingOperatorDomain toDomain(RegisterBookingOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = new RegisterBookingOperatorDomain();

        registerBookingOperatorDomain.setId( source.getId() );
        registerBookingOperatorDomain.setDeleted( source.getDeleted() );
        registerBookingOperatorDomain.setEnabled( source.getEnabled() );
        registerBookingOperatorDomain.setMigrated( source.getMigrated() );
        registerBookingOperatorDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingOperatorDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingOperatorDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingOperatorDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingOperatorDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingOperatorDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingOperatorDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingOperatorDomain.setTenant( source.getTenant() );
        registerBookingOperatorDomain.setName( source.getName() );
        registerBookingOperatorDomain.setDescription( source.getDescription() );
        registerBookingOperatorDomain.setIdentify( source.getIdentify() );
        registerBookingOperatorDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingOperatorDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingOperatorDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingOperatorDomain.setArriveAir( source.getArriveAir() );
        registerBookingOperatorDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingOperatorDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingOperatorDomain.setDateAperture( source.getDateAperture() );
        registerBookingOperatorDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingOperatorDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingOperatorDomain.setConfirmation( source.getConfirmation() );
        registerBookingOperatorDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingOperatorDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingOperatorDomain.setPrePago( source.getPrePago() );
        registerBookingOperatorDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingOperatorDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingOperatorDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingOperatorDomain.setObservation( source.getObservation() );
        registerBookingOperatorDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingOperatorDomain.setCantPax( source.getCantPax() );
        registerBookingOperatorDomain.setCantFree( source.getCantFree() );
        registerBookingOperatorDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingOperatorDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingOperatorDomain.setTc( source.getTc() );
        registerBookingOperatorDomain.setTcLocal( source.getTcLocal() );
        registerBookingOperatorDomain.setGuide( source.getGuide() );
        registerBookingOperatorDomain.setTotalPax( source.getTotalPax() );
        registerBookingOperatorDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingOperatorDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingOperatorDomain.setStateBooking( source.getStateBooking() );
        registerBookingOperatorDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingOperatorDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingOperatorDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingOperatorDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingOperatorDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingOperatorDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingOperatorDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingOperatorDomain.setNumberService( source.getNumberService() );
        registerBookingOperatorDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingOperatorDomain.setDateEndActivity( source.getDateEndActivity() );

        registerBookingOperatorDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingOperatorDomain.setNoCupon(source.getNoCupon() );
        registerBookingOperatorDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingOperatorDomain.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingOperatorDomain.setCuponTc( source.getCuponTc() );
        registerBookingOperatorDomain.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingOperatorDomain.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingOperatorDomain.setOperatorArrive( supplierOperatorConverter.toDomain( source.getOperatorArrive() ) );
        }else{
            registerBookingOperatorDomain.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingOperatorDomain.setOperatorDeparture( supplierOperatorConverter.toDomain( source.getOperatorDeparture() ) );
        }else{
            registerBookingOperatorDomain.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingOperatorDomain.setSupplierLodging( supplierLodgingConverter.toDomain( source.getSupplierLodging() ) );
        }else{
            registerBookingOperatorDomain.setSupplierLodging( null );
        }

        return registerBookingOperatorDomain;
    }

    @Override
    public RegisterBookingOperatorEntity toEntity(RegisterBookingOperatorDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingOperatorEntity registerBookingOperatorEntity = new RegisterBookingOperatorEntity();

        registerBookingOperatorEntity.setId( source.getId() );
        registerBookingOperatorEntity.setDeleted( source.getDeleted() );
        registerBookingOperatorEntity.setEnabled( source.getEnabled() );
        registerBookingOperatorEntity.setMigrated( source.getMigrated() );
        registerBookingOperatorEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingOperatorEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingOperatorEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingOperatorEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingOperatorEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingOperatorEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingOperatorEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingOperatorEntity.setTenant( source.getTenant() );
        registerBookingOperatorEntity.setName( source.getName() );
        registerBookingOperatorEntity.setDescription( source.getDescription() );
        registerBookingOperatorEntity.setIdentify( source.getIdentify() );
        registerBookingOperatorEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingOperatorEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingOperatorEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingOperatorEntity.setArriveAir( source.getArriveAir() );
        registerBookingOperatorEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingOperatorEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingOperatorEntity.setDateAperture( source.getDateAperture() );
        registerBookingOperatorEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingOperatorEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingOperatorEntity.setConfirmation( source.getConfirmation() );
        registerBookingOperatorEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingOperatorEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingOperatorEntity.setPrePago( source.getPrePago() );
        registerBookingOperatorEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingOperatorEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingOperatorEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingOperatorEntity.setObservation( source.getObservation() );
        registerBookingOperatorEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingOperatorEntity.setCantPax( source.getCantPax() );
        registerBookingOperatorEntity.setCantFree( source.getCantFree() );
        registerBookingOperatorEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingOperatorEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingOperatorEntity.setTc( source.getTc() );
        registerBookingOperatorEntity.setTcLocal( source.getTcLocal() );
        registerBookingOperatorEntity.setGuide( source.getGuide() );
        registerBookingOperatorEntity.setTotalPax( source.getTotalPax() );
        registerBookingOperatorEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingOperatorEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingOperatorEntity.setStateBooking( source.getStateBooking() );
        registerBookingOperatorEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingOperatorEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingOperatorEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingOperatorEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingOperatorEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingOperatorEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingOperatorEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingOperatorEntity.setNumberService( source.getNumberService() );
        registerBookingOperatorEntity.setDateInitActivity( source.getDateInitActivity() );
        registerBookingOperatorEntity.setDateEndActivity( source.getDateEndActivity() );

        registerBookingOperatorEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingOperatorEntity.setNoCupon(source.getNoCupon() );
        registerBookingOperatorEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingOperatorEntity.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingOperatorEntity.setCuponTc( source.getCuponTc() );
        registerBookingOperatorEntity.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingOperatorEntity.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingOperatorEntity.setOperatorArrive( supplierOperatorConverter.toEntity( source.getOperatorArrive() ) );
        }else{
            registerBookingOperatorEntity.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingOperatorEntity.setOperatorDeparture( supplierOperatorConverter.toEntity( source.getOperatorDeparture() ) );
        }else{
            registerBookingOperatorEntity.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingOperatorEntity.setSupplierLodging( supplierLodgingConverter.toEntity( source.getSupplierLodging() ) );
        }else{
            registerBookingOperatorEntity.setSupplierLodging( null );
        }

        return registerBookingOperatorEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingOperatorDomain> toDomainAllSimpleToDomainCaseListRegisterBookingOperator(List<RegisterBookingOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingOperatorDomain registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(entity.getId());
            registerBookingOperatorDomain.setIdentify(entity.getIdentify());
            registerBookingOperatorDomain.setCreatedAt(entity.getCreatedAt());
            registerBookingOperatorDomain.setStateBooking(entity.getStateBooking());
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                registerBookingOperatorDomain.setSupplierType(new SupplierTypeDomain());
                registerBookingOperatorDomain.getSupplierType().setId(entity.getSupplierType().getId());
                registerBookingOperatorDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                registerBookingOperatorDomain.setSupplier(new SupplierDomain());
                registerBookingOperatorDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingOperatorDomain.getSupplier().setName(entity.getSupplier().getName());
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()){
                    registerBookingOperatorDomain.getSupplier().setPartner(new PartnerDomain());
                    registerBookingOperatorDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        registerBookingOperatorDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                            registerBookingOperatorDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                registerBookingOperatorDomain.setRegisterGroup(new RegisterGroupDomain());
                registerBookingOperatorDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingOperatorDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                registerBookingOperatorDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return registerBookingOperatorDomain;
        }).toList();
    }

    public PageDTO<RegisterBookingOperatorDomain> toDomainPageCaseListRegisterBookingOperator(Page<RegisterBookingOperatorEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingOperatorDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterBookingOperator(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public RegisterBookingOperatorDomain toDomainIdSimpleDomainCaseValidationExistBooking(RegisterBookingOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = new RegisterBookingOperatorDomain();

        registerBookingOperatorDomain.setId( source.getId() );
        return registerBookingOperatorDomain;
    }

    public RegisterBookingOperatorDomain toDomainAllSimpleToDomainCaseCaseAllCupon(RegisterBookingOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = new RegisterBookingOperatorDomain();

        registerBookingOperatorDomain.setId( source.getId() );
        registerBookingOperatorDomain.setNoCupon( source.getNoCupon() );
        registerBookingOperatorDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingOperatorDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingOperatorDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingOperatorDomain.setCuponCantPaxAdult(source.getCuponCantPaxAdult());
        registerBookingOperatorDomain.setCuponCantPaxChildren(source.getCuponCantPaxChildren());
        registerBookingOperatorDomain.setCuponTc(source.getCuponTc());
        registerBookingOperatorDomain.setCuponTcLocal(source.getCuponTcLocal());
        registerBookingOperatorDomain.setGuide(source.getGuide());

        registerBookingOperatorDomain.setCantPaxAdult(source.getCantPaxAdult());
        registerBookingOperatorDomain.setCantPaxChildren(source.getCantPaxChildren());
        registerBookingOperatorDomain.setTc(source.getTc());
        registerBookingOperatorDomain.setTcLocal(source.getTcLocal());

        registerBookingOperatorDomain.setGastosExtrasNoIncluidos(source.getGastosExtrasNoIncluidos());

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            registerBookingOperatorDomain.setSupplierType( new SupplierTypeDomain() );
            registerBookingOperatorDomain.getSupplierType().setId(source.getSupplierType().getId());
        }
        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            registerBookingOperatorDomain.setSupplier( new SupplierDomain() );
            registerBookingOperatorDomain.getSupplier().setId(source.getSupplier().getId());
            registerBookingOperatorDomain.getSupplier().setName(source.getSupplier().getName());
            registerBookingOperatorDomain.getSupplier().setTypeInheritance( source.getSupplier().getTypeInheritance() );
            if (source.getSupplier().getPartner() != null &&
                    source.getSupplier().getPartner().getId() != null &&
                    !source.getSupplier().getPartner().getId().isEmpty()) {
                registerBookingOperatorDomain.getSupplier().setPartner( new PartnerDomain( ));
                registerBookingOperatorDomain.getSupplier().getPartner().setId(source.getSupplier().getPartner().getId());
                if (source.getSupplier().getPartner().getAddress() != null) {
                    registerBookingOperatorDomain.getSupplier().getPartner().setAddress( new AddressEmbeddedDomain() );
                    registerBookingOperatorDomain.getSupplier().getPartner().getAddress().setStreet(source.getSupplier().getPartner().getAddress().getStreet());
                    registerBookingOperatorDomain.getSupplier().getPartner().getAddress().setStreet2(source.getSupplier().getPartner().getAddress().getStreet2());
                    if (source.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                            source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                            !source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                        registerBookingOperatorDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                        registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                        registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(source.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                    }
                }
            }
        }
        if (source.getEmployee() != null &&
                source.getEmployee().getId() != null &&
                !source.getEmployee().getId().isEmpty()) {
            registerBookingOperatorDomain.setEmployee( new EmployeeDomain() );
            registerBookingOperatorDomain.getEmployee().setId(source.getEmployee().getId());
            if (source.getEmployee().getPerson() != null &&
                    source.getEmployee().getPerson().getId() != null &&
                    !source.getEmployee().getPerson().getId().isEmpty()) {
                registerBookingOperatorDomain.getEmployee().setPerson( new PersonDomain() );
                registerBookingOperatorDomain.getEmployee().getPerson().setId(source.getEmployee().getPerson().getId());
                registerBookingOperatorDomain.getEmployee().getPerson().setLastName(source.getEmployee().getPerson().getLastName());
                registerBookingOperatorDomain.getEmployee().getPerson().setFirstName(source.getEmployee().getPerson().getFirstName());
            }
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            registerBookingOperatorDomain.setRegisterGroup( new RegisterGroupDomain() );
            registerBookingOperatorDomain.getRegisterGroup().setId(source.getRegisterGroup().getId());
            registerBookingOperatorDomain.getRegisterGroup().setName(source.getRegisterGroup().getName());
        }

        return registerBookingOperatorDomain;
    }
}
