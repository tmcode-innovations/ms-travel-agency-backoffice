package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinginstitution;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBookingInstitutionConverter implements Converter<RegisterBookingInstitutionDomain, RegisterBookingInstitutionEntity> {

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
    public RegisterBookingInstitutionDomain toDomain(RegisterBookingInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = new RegisterBookingInstitutionDomain();

        registerBookingInstitutionDomain.setId( source.getId() );
        registerBookingInstitutionDomain.setDeleted( source.getDeleted() );
        registerBookingInstitutionDomain.setEnabled( source.getEnabled() );
        registerBookingInstitutionDomain.setMigrated( source.getMigrated() );
        registerBookingInstitutionDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingInstitutionDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingInstitutionDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingInstitutionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingInstitutionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingInstitutionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingInstitutionDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingInstitutionDomain.setTenant( source.getTenant() );
        registerBookingInstitutionDomain.setName( source.getName() );
        registerBookingInstitutionDomain.setDescription( source.getDescription() );
        registerBookingInstitutionDomain.setIdentify( source.getIdentify() );
        registerBookingInstitutionDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingInstitutionDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingInstitutionDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingInstitutionDomain.setArriveAir( source.getArriveAir() );
        registerBookingInstitutionDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingInstitutionDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingInstitutionDomain.setDateAperture( source.getDateAperture() );
        registerBookingInstitutionDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingInstitutionDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingInstitutionDomain.setConfirmation( source.getConfirmation() );
        registerBookingInstitutionDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingInstitutionDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingInstitutionDomain.setPrePago( source.getPrePago() );
        registerBookingInstitutionDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingInstitutionDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingInstitutionDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingInstitutionDomain.setObservation( source.getObservation() );
        registerBookingInstitutionDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingInstitutionDomain.setCantPax( source.getCantPax() );
        registerBookingInstitutionDomain.setCantFree( source.getCantFree() );
        registerBookingInstitutionDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingInstitutionDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingInstitutionDomain.setTc( source.getTc() );
        registerBookingInstitutionDomain.setTcLocal( source.getTcLocal() );
        registerBookingInstitutionDomain.setGuide( source.getGuide() );
        registerBookingInstitutionDomain.setTotalPax( source.getTotalPax() );
        registerBookingInstitutionDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingInstitutionDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingInstitutionDomain.setStateBooking( source.getStateBooking() );
        registerBookingInstitutionDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingInstitutionDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingInstitutionDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingInstitutionDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingInstitutionDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingInstitutionDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingInstitutionDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingInstitutionDomain.setNumberService( source.getNumberService() );
        registerBookingInstitutionDomain.setDateInitActivity( source.getDateInitActivity() );
        registerBookingInstitutionDomain.setDateEndActivity( source.getDateEndActivity() );

        registerBookingInstitutionDomain.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingInstitutionDomain.setNoCupon(source.getNoCupon() );
        registerBookingInstitutionDomain.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingInstitutionDomain.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingInstitutionDomain.setCuponTc( source.getCuponTc() );
        registerBookingInstitutionDomain.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingInstitutionDomain.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingInstitutionDomain.setOperatorArrive( supplierOperatorConverter.toDomain( source.getOperatorArrive() ) );
        }else{
            registerBookingInstitutionDomain.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingInstitutionDomain.setOperatorDeparture( supplierOperatorConverter.toDomain( source.getOperatorDeparture() ) );
        }else{
            registerBookingInstitutionDomain.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingInstitutionDomain.setSupplierLodging( supplierLodgingConverter.toDomain( source.getSupplierLodging() ) );
        }else{
            registerBookingInstitutionDomain.setSupplierLodging( null );
        }

        return registerBookingInstitutionDomain;
    }

    @Override
    public RegisterBookingInstitutionEntity toEntity(RegisterBookingInstitutionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingInstitutionEntity registerBookingInstitutionEntity = new RegisterBookingInstitutionEntity();

        registerBookingInstitutionEntity.setId( source.getId() );
        registerBookingInstitutionEntity.setDeleted( source.getDeleted() );
        registerBookingInstitutionEntity.setEnabled( source.getEnabled() );
        registerBookingInstitutionEntity.setMigrated( source.getMigrated() );
        registerBookingInstitutionEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingInstitutionEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingInstitutionEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingInstitutionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingInstitutionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingInstitutionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingInstitutionEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingInstitutionEntity.setTenant( source.getTenant() );
        registerBookingInstitutionEntity.setName( source.getName() );
        registerBookingInstitutionEntity.setDescription( source.getDescription() );
        registerBookingInstitutionEntity.setIdentify( source.getIdentify() );
        registerBookingInstitutionEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingInstitutionEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingInstitutionEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingInstitutionEntity.setArriveAir( source.getArriveAir() );
        registerBookingInstitutionEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingInstitutionEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingInstitutionEntity.setDateAperture( source.getDateAperture() );
        registerBookingInstitutionEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingInstitutionEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingInstitutionEntity.setConfirmation( source.getConfirmation() );
        registerBookingInstitutionEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingInstitutionEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingInstitutionEntity.setPrePago( source.getPrePago() );
        registerBookingInstitutionEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingInstitutionEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingInstitutionEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingInstitutionEntity.setObservation( source.getObservation() );
        registerBookingInstitutionEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingInstitutionEntity.setCantPax( source.getCantPax() );
        registerBookingInstitutionEntity.setCantFree( source.getCantFree() );
        registerBookingInstitutionEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingInstitutionEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingInstitutionEntity.setTc( source.getTc() );
        registerBookingInstitutionEntity.setTcLocal( source.getTcLocal() );
        registerBookingInstitutionEntity.setGuide( source.getGuide() );
        registerBookingInstitutionEntity.setTotalPax( source.getTotalPax() );
        registerBookingInstitutionEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingInstitutionEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingInstitutionEntity.setStateBooking( source.getStateBooking() );
        registerBookingInstitutionEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingInstitutionEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingInstitutionEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingInstitutionEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingInstitutionEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingInstitutionEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingInstitutionEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingInstitutionEntity.setNumberService( source.getNumberService() );
        registerBookingInstitutionEntity.setDateInitActivity( source.getDateInitActivity() );
        registerBookingInstitutionEntity.setDateEndActivity( source.getDateEndActivity() );

        registerBookingInstitutionEntity.setDateTimeInitService( source.getDateTimeInitService() );

        registerBookingInstitutionEntity.setNoCupon(source.getNoCupon() );
        registerBookingInstitutionEntity.setNoCuponPreImpress( source.getNoCuponPreImpress() );
        registerBookingInstitutionEntity.setCuponTcLocal( source.getCuponTcLocal() );
        registerBookingInstitutionEntity.setCuponTc( source.getCuponTc() );
        registerBookingInstitutionEntity.setCuponCantPaxChildren( source.getCuponCantPaxChildren() );
        registerBookingInstitutionEntity.setCuponCantPaxAdult( source.getCuponCantPaxAdult() );

        if (source.getOperatorArrive() != null &&
                source.getOperatorArrive().getId() != null &&
                !source.getOperatorArrive().getId().isEmpty()){
            registerBookingInstitutionEntity.setOperatorArrive( supplierOperatorConverter.toEntity( source.getOperatorArrive() ) );
        }else{
            registerBookingInstitutionEntity.setOperatorArrive( null );
        }
        if (source.getOperatorDeparture() != null &&
                source.getOperatorDeparture().getId() != null &&
                !source.getOperatorDeparture().getId().isEmpty()){
            registerBookingInstitutionEntity.setOperatorDeparture( supplierOperatorConverter.toEntity( source.getOperatorDeparture() ) );
        }else{
            registerBookingInstitutionEntity.setOperatorDeparture( null );
        }
        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()){
            registerBookingInstitutionEntity.setSupplierLodging( supplierLodgingConverter.toEntity( source.getSupplierLodging() ) );
        }else{
            registerBookingInstitutionEntity.setSupplierLodging( null );
        }

        return registerBookingInstitutionEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingInstitutionDomain> toDomainAllSimpleToDomainCaseListRegisterBookingInstitution(List<RegisterBookingInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingInstitutionDomain registerBookingInstitutionDomain = new RegisterBookingInstitutionDomain();
            registerBookingInstitutionDomain.setId(entity.getId());
            registerBookingInstitutionDomain.setIdentify(entity.getIdentify());
            registerBookingInstitutionDomain.setCreatedAt(entity.getCreatedAt());
            registerBookingInstitutionDomain.setStateBooking(entity.getStateBooking());
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                registerBookingInstitutionDomain.setSupplierType(new SupplierTypeDomain());
                registerBookingInstitutionDomain.getSupplierType().setId(entity.getSupplierType().getId());
                registerBookingInstitutionDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                registerBookingInstitutionDomain.setSupplier(new SupplierDomain());
                registerBookingInstitutionDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingInstitutionDomain.getSupplier().setName(entity.getSupplier().getName());
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()){
                    registerBookingInstitutionDomain.getSupplier().setPartner(new PartnerDomain());
                    registerBookingInstitutionDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        registerBookingInstitutionDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                            registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                registerBookingInstitutionDomain.setRegisterGroup(new RegisterGroupDomain());
                registerBookingInstitutionDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingInstitutionDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                registerBookingInstitutionDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return registerBookingInstitutionDomain;
        }).toList();
    }

    public PageDTO<RegisterBookingInstitutionDomain> toDomainPageCaseListRegisterBookingInstitution(Page<RegisterBookingInstitutionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingInstitutionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterBookingInstitution(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public RegisterBookingInstitutionDomain toDomainIdSimpleDomainCaseValidationExistBooking(RegisterBookingInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = new RegisterBookingInstitutionDomain();

        registerBookingInstitutionDomain.setId( source.getId() );
        return registerBookingInstitutionDomain;
    }
}
