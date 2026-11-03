package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingbase;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingBaseEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingBaseConverter implements Converter<RegisterBookingBaseDomain, RegisterBookingBaseEntity> {

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
    public RegisterBookingBaseDomain toDomain(RegisterBookingBaseEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingBaseDomain registerBookingBaseDomain = new RegisterBookingBaseDomain();

        registerBookingBaseDomain.setId( source.getId() );
        registerBookingBaseDomain.setDeleted( source.getDeleted() );
        registerBookingBaseDomain.setEnabled( source.getEnabled() );
        registerBookingBaseDomain.setMigrated( source.getMigrated() );
        registerBookingBaseDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingBaseDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingBaseDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingBaseDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingBaseDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingBaseDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingBaseDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingBaseDomain.setTenant( source.getTenant() );
        registerBookingBaseDomain.setName( source.getName() );
        registerBookingBaseDomain.setDescription( source.getDescription() );
        registerBookingBaseDomain.setIdentify( source.getIdentify() );
        registerBookingBaseDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingBaseDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingBaseDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingBaseDomain.setArriveAir( source.getArriveAir() );
        registerBookingBaseDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingBaseDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingBaseDomain.setDateAperture( source.getDateAperture() );
        registerBookingBaseDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingBaseDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingBaseDomain.setConfirmation( source.getConfirmation() );
        registerBookingBaseDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingBaseDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingBaseDomain.setPrePago( source.getPrePago() );
        registerBookingBaseDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingBaseDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingBaseDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingBaseDomain.setObservation( source.getObservation() );
        registerBookingBaseDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingBaseDomain.setCantPax( source.getCantPax() );
        registerBookingBaseDomain.setCantFree( source.getCantFree() );
        registerBookingBaseDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingBaseDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingBaseDomain.setTc( source.getTc() );
        registerBookingBaseDomain.setTcLocal( source.getTcLocal() );
        registerBookingBaseDomain.setGuide( source.getGuide() );
        registerBookingBaseDomain.setTotalPax( source.getTotalPax() );
        registerBookingBaseDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingBaseDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingBaseDomain.setStateBooking( source.getStateBooking() );
        registerBookingBaseDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingBaseDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingBaseDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingBaseDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingBaseDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingBaseDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingBaseDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        return registerBookingBaseDomain;
    }

    @Override
    public RegisterBookingBaseEntity toEntity(RegisterBookingBaseDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingBaseEntity registerBookingBaseEntity = new RegisterBookingBaseEntity();

        registerBookingBaseEntity.setId( source.getId() );
        registerBookingBaseEntity.setDeleted( source.getDeleted() );
        registerBookingBaseEntity.setEnabled( source.getEnabled() );
        registerBookingBaseEntity.setMigrated( source.getMigrated() );
        registerBookingBaseEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingBaseEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingBaseEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingBaseEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingBaseEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingBaseEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingBaseEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingBaseEntity.setTenant( source.getTenant() );
        registerBookingBaseEntity.setName( source.getName() );
        registerBookingBaseEntity.setDescription( source.getDescription() );
        registerBookingBaseEntity.setIdentify( source.getIdentify() );
        registerBookingBaseEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingBaseEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingBaseEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingBaseEntity.setArriveAir( source.getArriveAir() );
        registerBookingBaseEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingBaseEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingBaseEntity.setDateAperture( source.getDateAperture() );
        registerBookingBaseEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingBaseEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingBaseEntity.setConfirmation( source.getConfirmation() );
        registerBookingBaseEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingBaseEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingBaseEntity.setPrePago( source.getPrePago() );
        registerBookingBaseEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingBaseEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingBaseEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingBaseEntity.setObservation( source.getObservation() );
        registerBookingBaseEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingBaseEntity.setCantPax( source.getCantPax() );
        registerBookingBaseEntity.setCantFree( source.getCantFree() );
        registerBookingBaseEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingBaseEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingBaseEntity.setTc( source.getTc() );
        registerBookingBaseEntity.setTcLocal( source.getTcLocal() );
        registerBookingBaseEntity.setGuide( source.getGuide() );
        registerBookingBaseEntity.setTotalPax( source.getTotalPax() );
        registerBookingBaseEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingBaseEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingBaseEntity.setStateBooking( source.getStateBooking() );
        registerBookingBaseEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingBaseEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingBaseEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingBaseEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingBaseEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingBaseEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingBaseEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        return registerBookingBaseEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingBaseEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingBaseEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingBaseEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingBaseDomain> toDomainAllSimpleToDomainCaseCaseAllCupon(List<RegisterBookingBaseEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingBaseDomain registerBookingBaseDomain = new RegisterBookingBaseDomain();
            registerBookingBaseDomain.setId( entity.getId() );
            registerBookingBaseDomain.setIdentify( entity.getIdentify() );
            registerBookingBaseDomain.setGastosExtrasNoIncluidos( entity.getGastosExtrasNoIncluidos() );
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                registerBookingBaseDomain.setSupplierType( new SupplierTypeDomain() );
                registerBookingBaseDomain.getSupplierType().setId(entity.getSupplierType().getId());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                registerBookingBaseDomain.setSupplier( new SupplierDomain() );
                registerBookingBaseDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingBaseDomain.getSupplier().setName(entity.getSupplier().getName());
                registerBookingBaseDomain.getSupplier().setTypeInheritance( entity.getSupplier().getTypeInheritance() );
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()) {
                    registerBookingBaseDomain.getSupplier().setPartner( new PartnerDomain( ));
                    registerBookingBaseDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null) {
                        registerBookingBaseDomain.getSupplier().getPartner().setAddress( new AddressEmbeddedDomain() );
                        registerBookingBaseDomain.getSupplier().getPartner().getAddress().setStreet(entity.getSupplier().getPartner().getAddress().getStreet());
                        registerBookingBaseDomain.getSupplier().getPartner().getAddress().setStreet2(entity.getSupplier().getPartner().getAddress().getStreet2());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                            registerBookingBaseDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getEmployee() != null &&
                    entity.getEmployee().getId() != null &&
                    !entity.getEmployee().getId().isEmpty()) {
                registerBookingBaseDomain.setEmployee( new EmployeeDomain() );
                registerBookingBaseDomain.getEmployee().setId(entity.getEmployee().getId());
                if (entity.getEmployee().getPerson() != null &&
                        entity.getEmployee().getPerson().getId() != null &&
                        !entity.getEmployee().getPerson().getId().isEmpty()) {
                    registerBookingBaseDomain.getEmployee().setPerson( new PersonDomain() );
                    registerBookingBaseDomain.getEmployee().getPerson().setId(entity.getEmployee().getPerson().getId());
                    registerBookingBaseDomain.getEmployee().getPerson().setLastName(entity.getEmployee().getPerson().getLastName());
                    registerBookingBaseDomain.getEmployee().getPerson().setFirstName(entity.getEmployee().getPerson().getFirstName());
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()) {
                registerBookingBaseDomain.setRegisterGroup( new RegisterGroupDomain() );
                registerBookingBaseDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingBaseDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return registerBookingBaseDomain;
        }).toList();
    }

    public List<RegisterBookingBaseDomain> toDomainAllSimpleToDomainCaseCaseAllGenerateBooking(List<RegisterBookingBaseEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingBaseDomain registerBookingBaseDomain = new RegisterBookingBaseDomain();
            registerBookingBaseDomain.setId( entity.getId() );
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                registerBookingBaseDomain.setSupplierType( new SupplierTypeDomain() );
                registerBookingBaseDomain.getSupplierType().setId(entity.getSupplierType().getId());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                registerBookingBaseDomain.setSupplier( new SupplierDomain() );
                registerBookingBaseDomain.getSupplier().setId(entity.getSupplier().getId());
            }
            return registerBookingBaseDomain;
        }).toList();
    }

    public List<RegisterBookingBaseDomain> toDomainAllSimpleToDomainCaseCaseAnnulled(List<RegisterBookingBaseEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingBaseDomain registerBookingBaseDomain = new RegisterBookingBaseDomain();
            registerBookingBaseDomain.setId( entity.getId() );
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                registerBookingBaseDomain.setSupplierType( new SupplierTypeDomain() );
                registerBookingBaseDomain.getSupplierType().setId(entity.getSupplierType().getId());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                registerBookingBaseDomain.setSupplier( new SupplierDomain() );
                registerBookingBaseDomain.getSupplier().setId(entity.getSupplier().getId());
            }
            return registerBookingBaseDomain;
        }).toList();
    }
}
