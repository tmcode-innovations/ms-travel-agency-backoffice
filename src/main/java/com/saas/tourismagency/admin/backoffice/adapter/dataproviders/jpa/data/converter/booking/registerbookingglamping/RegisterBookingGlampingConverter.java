package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingGlampingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
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
public class RegisterBookingGlampingConverter implements Converter<RegisterBookingGlampingDomain, RegisterBookingGlampingEntity> {

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
    public RegisterBookingGlampingDomain toDomain(RegisterBookingGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = new RegisterBookingGlampingDomain();

        registerBookingGlampingDomain.setId( source.getId() );
        registerBookingGlampingDomain.setDeleted( source.getDeleted() );
        registerBookingGlampingDomain.setEnabled( source.getEnabled() );
        registerBookingGlampingDomain.setMigrated( source.getMigrated() );
        registerBookingGlampingDomain.setCreatedAt( source.getCreatedAt() );
        registerBookingGlampingDomain.setUpdatedAt( source.getUpdatedAt() );
        registerBookingGlampingDomain.setDeletedAt( source.getDeletedAt() );
        registerBookingGlampingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerBookingGlampingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerBookingGlampingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerBookingGlampingDomain.setIdentifierName( source.getIdentifierName() );
        registerBookingGlampingDomain.setTenant( source.getTenant() );
        registerBookingGlampingDomain.setName( source.getName() );
        registerBookingGlampingDomain.setDescription( source.getDescription() );
        registerBookingGlampingDomain.setIdentify( source.getIdentify() );
        registerBookingGlampingDomain.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingGlampingDomain.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingGlampingDomain.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingGlampingDomain.setArriveAir( source.getArriveAir() );
        registerBookingGlampingDomain.setDepartureAir( source.getDepartureAir() );
        registerBookingGlampingDomain.setArriveAirDate( source.getArriveAirDate() );
        registerBookingGlampingDomain.setDateAperture( source.getDateAperture() );
        registerBookingGlampingDomain.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingGlampingDomain.setDateConfirmation( source.getDateConfirmation() );
        registerBookingGlampingDomain.setConfirmation( source.getConfirmation() );
        registerBookingGlampingDomain.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingGlampingDomain.setReConfirmation( source.getReConfirmation() );
        registerBookingGlampingDomain.setPrePago( source.getPrePago() );
        registerBookingGlampingDomain.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingGlampingDomain.setWithConfirmation( source.getWithConfirmation() );
        registerBookingGlampingDomain.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingGlampingDomain.setObservation( source.getObservation() );
        registerBookingGlampingDomain.setObservationCupon( source.getObservationCupon() );
        registerBookingGlampingDomain.setCantPax( source.getCantPax() );
        registerBookingGlampingDomain.setCantFree( source.getCantFree() );
        registerBookingGlampingDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingGlampingDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingGlampingDomain.setTc( source.getTc() );
        registerBookingGlampingDomain.setTcLocal( source.getTcLocal() );
        registerBookingGlampingDomain.setGuide( source.getGuide() );
        registerBookingGlampingDomain.setTotalPax( source.getTotalPax() );
        registerBookingGlampingDomain.setValueConfirmation( source.getValueConfirmation() );
        registerBookingGlampingDomain.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingGlampingDomain.setStateBooking( source.getStateBooking() );
        registerBookingGlampingDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        registerBookingGlampingDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        registerBookingGlampingDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        registerBookingGlampingDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerBookingGlampingDomain.setNationality(nationalityConverter.toDomain(source.getNationality()) );
        registerBookingGlampingDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()) );
        registerBookingGlampingDomain.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingGlampingDomain.setInitTour( source.getInitTour() );
        registerBookingGlampingDomain.setEndTour( source.getEndTour() );

        registerBookingGlampingDomain.setInitHourTour( source.getInitHourTour() );
        registerBookingGlampingDomain.setEndHourTour( source.getEndHourTour() );

        registerBookingGlampingDomain.setCantSwb( source.getCantSwb() );
        registerBookingGlampingDomain.setCantSwbTcGuide( source.getCantSwbTcGuide() );
        registerBookingGlampingDomain.setCantSwbTcLocalGuide( source.getCantSwbTcLocalGuide() );
        registerBookingGlampingDomain.setCantSwbGuide( source.getCantSwbGuide() );
        registerBookingGlampingDomain.setCantTrp( source.getCantTrp() );
        registerBookingGlampingDomain.setCantCdp(source.getCantCdp() );
        registerBookingGlampingDomain.setCantQtp(source.getCantQtp() );
        registerBookingGlampingDomain.setCantSxtp( source.getCantSxtp() );
        registerBookingGlampingDomain.setCantTwin( source.getCantTwin() );
        registerBookingGlampingDomain.setCantTwinTcGuide( source.getCantTwinTcGuide() );
        registerBookingGlampingDomain.setCantTwinTcLocalGuide(source.getCantTwinTcLocalGuide() );
        registerBookingGlampingDomain.setCantTwinGuide(source.getCantTwinGuide() );
        registerBookingGlampingDomain.setCantMat( source.getCantMat() );
        registerBookingGlampingDomain.setNumberIngress( source.getNumberIngress() );

        return registerBookingGlampingDomain;
    }

    @Override
    public RegisterBookingGlampingEntity toEntity(RegisterBookingGlampingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingGlampingEntity registerBookingGlampingEntity = new RegisterBookingGlampingEntity();

        registerBookingGlampingEntity.setId( source.getId() );
        registerBookingGlampingEntity.setDeleted( source.getDeleted() );
        registerBookingGlampingEntity.setEnabled( source.getEnabled() );
        registerBookingGlampingEntity.setMigrated( source.getMigrated() );
        registerBookingGlampingEntity.setCreatedAt( source.getCreatedAt() );
        registerBookingGlampingEntity.setUpdatedAt( source.getUpdatedAt() );
        registerBookingGlampingEntity.setDeletedAt( source.getDeletedAt() );
        registerBookingGlampingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerBookingGlampingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerBookingGlampingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerBookingGlampingEntity.setIdentifierName( source.getIdentifierName() );
        registerBookingGlampingEntity.setTenant( source.getTenant() );
        registerBookingGlampingEntity.setName( source.getName() );
        registerBookingGlampingEntity.setDescription( source.getDescription() );
        registerBookingGlampingEntity.setIdentify( source.getIdentify() );
        registerBookingGlampingEntity.setTypeInheritanceBooking( source.getTypeInheritanceBooking() );
        registerBookingGlampingEntity.setIdentifyConfirmation( source.getIdentifyConfirmation() );
        registerBookingGlampingEntity.setIdentifyReConfirmation( source.getIdentifyReConfirmation() );
        registerBookingGlampingEntity.setArriveAir( source.getArriveAir() );
        registerBookingGlampingEntity.setDepartureAir( source.getDepartureAir() );
        registerBookingGlampingEntity.setArriveAirDate( source.getArriveAirDate() );
        registerBookingGlampingEntity.setDateAperture( source.getDateAperture() );
        registerBookingGlampingEntity.setDepartureAirDate( source.getDepartureAirDate() );
        registerBookingGlampingEntity.setDateConfirmation( source.getDateConfirmation() );
        registerBookingGlampingEntity.setConfirmation( source.getConfirmation() );
        registerBookingGlampingEntity.setDateReConfirmation( source.getDateReConfirmation() );
        registerBookingGlampingEntity.setReConfirmation( source.getReConfirmation() );
        registerBookingGlampingEntity.setPrePago( source.getPrePago() );
        registerBookingGlampingEntity.setGastosExtrasNoIncluidos( source.getGastosExtrasNoIncluidos() );
        registerBookingGlampingEntity.setWithConfirmation( source.getWithConfirmation() );
        registerBookingGlampingEntity.setWithReConfirmation( source.getWithReConfirmation() );
        registerBookingGlampingEntity.setObservation( source.getObservation() );
        registerBookingGlampingEntity.setObservationCupon( source.getObservationCupon() );
        registerBookingGlampingEntity.setCantPax( source.getCantPax() );
        registerBookingGlampingEntity.setCantFree( source.getCantFree() );
        registerBookingGlampingEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerBookingGlampingEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerBookingGlampingEntity.setTc( source.getTc() );
        registerBookingGlampingEntity.setTcLocal( source.getTcLocal() );
        registerBookingGlampingEntity.setGuide( source.getGuide() );
        registerBookingGlampingEntity.setTotalPax( source.getTotalPax() );
        registerBookingGlampingEntity.setValueConfirmation( source.getValueConfirmation() );
        registerBookingGlampingEntity.setValueReConfirmation( source.getValueReConfirmation() );
        registerBookingGlampingEntity.setStateBooking( source.getStateBooking() );
        registerBookingGlampingEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        registerBookingGlampingEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()) );
        registerBookingGlampingEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        registerBookingGlampingEntity.setCustomer(customerConverter.toEntity(source.getCustomer()) );
        registerBookingGlampingEntity.setNationality(nationalityConverter.toEntity(source.getNationality()) );
        registerBookingGlampingEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()) );
        registerBookingGlampingEntity.setActiveRegisterBooking( source.getActiveRegisterBooking() );

        registerBookingGlampingEntity.setInitTour( source.getInitTour() );
        registerBookingGlampingEntity.setEndTour( source.getEndTour() );

        registerBookingGlampingEntity.setInitHourTour( source.getInitHourTour() );
        registerBookingGlampingEntity.setEndHourTour( source.getEndHourTour() );

        registerBookingGlampingEntity.setCantSwb( source.getCantSwb() );
        registerBookingGlampingEntity.setCantSwbTcGuide( source.getCantSwbTcGuide() );
        registerBookingGlampingEntity.setCantSwbTcLocalGuide( source.getCantSwbTcLocalGuide() );
        registerBookingGlampingEntity.setCantSwbGuide( source.getCantSwbGuide() );
        registerBookingGlampingEntity.setCantTrp( source.getCantTrp() );
        registerBookingGlampingEntity.setCantCdp(source.getCantCdp() );
        registerBookingGlampingEntity.setCantQtp(source.getCantQtp() );
        registerBookingGlampingEntity.setCantSxtp( source.getCantSxtp() );
        registerBookingGlampingEntity.setCantTwin( source.getCantTwin() );
        registerBookingGlampingEntity.setCantTwinTcGuide( source.getCantTwinTcGuide() );
        registerBookingGlampingEntity.setCantTwinTcLocalGuide(source.getCantTwinTcLocalGuide() );
        registerBookingGlampingEntity.setCantTwinGuide(source.getCantTwinGuide() );
        registerBookingGlampingEntity.setCantMat( source.getCantMat() );
        registerBookingGlampingEntity.setNumberIngress( source.getNumberIngress() );

        return registerBookingGlampingEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterBookingGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterBookingGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterBookingGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RegisterBookingGlampingDomain> toDomainAllSimpleToDomainCaseListRegisterBookingGlamping(List<RegisterBookingGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterBookingGlampingDomain registerBookingGlampingDomain = new RegisterBookingGlampingDomain();
            registerBookingGlampingDomain.setId(entity.getId());
            registerBookingGlampingDomain.setIdentify(entity.getIdentify());
            registerBookingGlampingDomain.setCreatedAt(entity.getCreatedAt());
            registerBookingGlampingDomain.setStateBooking(entity.getStateBooking());
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                registerBookingGlampingDomain.setSupplierType(new SupplierTypeDomain());
                registerBookingGlampingDomain.getSupplierType().setId(entity.getSupplierType().getId());
                registerBookingGlampingDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                registerBookingGlampingDomain.setSupplier(new SupplierDomain());
                registerBookingGlampingDomain.getSupplier().setId(entity.getSupplier().getId());
                registerBookingGlampingDomain.getSupplier().setName(entity.getSupplier().getName());
                if (entity.getSupplier().getPartner() != null &&
                        entity.getSupplier().getPartner().getId() != null &&
                        !entity.getSupplier().getPartner().getId().isEmpty()){
                    registerBookingGlampingDomain.getSupplier().setPartner(new PartnerDomain());
                    registerBookingGlampingDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        registerBookingGlampingDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                !entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                            registerBookingGlampingDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            registerBookingGlampingDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            registerBookingGlampingDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                registerBookingGlampingDomain.setRegisterGroup(new RegisterGroupDomain());
                registerBookingGlampingDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                registerBookingGlampingDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                registerBookingGlampingDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return registerBookingGlampingDomain;
        }).toList();
    }

    public PageDTO<RegisterBookingGlampingDomain> toDomainPageCaseListRegisterBookingGlamping(Page<RegisterBookingGlampingEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingGlampingDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterBookingGlamping(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public RegisterBookingGlampingDomain toDomainIdSimpleDomainCaseValidationExistBooking(RegisterBookingGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = new RegisterBookingGlampingDomain();

        registerBookingGlampingDomain.setId( source.getId() );
        return registerBookingGlampingDomain;
    }
}
