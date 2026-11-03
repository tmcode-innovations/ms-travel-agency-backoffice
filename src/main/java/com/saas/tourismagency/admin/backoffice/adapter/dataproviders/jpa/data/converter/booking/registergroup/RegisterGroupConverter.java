package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom.IdiomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.domain.UserBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterGroupConverter implements Converter<RegisterGroupDomain, RegisterGroupEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProgramConverter programConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private IdiomConverter idiomConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Override
    public RegisterGroupDomain toDomain(RegisterGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();

        registerGroupDomain.setId( source.getId() );
        registerGroupDomain.setDeleted( source.getDeleted() );
        registerGroupDomain.setEnabled( source.getEnabled() );
        registerGroupDomain.setMigrated( source.getMigrated() );
        registerGroupDomain.setCreatedAt( source.getCreatedAt() );
        registerGroupDomain.setUpdatedAt( source.getUpdatedAt() );
        registerGroupDomain.setDeletedAt( source.getDeletedAt() );
        registerGroupDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        registerGroupDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        registerGroupDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        registerGroupDomain.setIdentifierName( source.getIdentifierName() );
        registerGroupDomain.setTenant( source.getTenant() );
        registerGroupDomain.setName( source.getName() );
        registerGroupDomain.setDescription( source.getDescription() );
        registerGroupDomain.setIdentify( source.getIdentify() );
        registerGroupDomain.setDays( source.getDays() );
        registerGroupDomain.setCantPax( source.getCantPax() );
        registerGroupDomain.setCantFree( source.getCantFree() );
        registerGroupDomain.setCantPaxAdult( source.getCantPaxAdult() );
        registerGroupDomain.setCantPaxChildren( source.getCantPaxChildren() );
        registerGroupDomain.setTC( source.getTC() );
        registerGroupDomain.setTCLocal( source.getTCLocal() );
        registerGroupDomain.setGuide( source.getGuide() );
        registerGroupDomain.setTotalPax( source.getTotalPax() );
        registerGroupDomain.setDateAperture( source.getDateAperture() );
        registerGroupDomain.setInitTour( source.getInitTour() );
        registerGroupDomain.setEndTour( source.getEndTour() );
        registerGroupDomain.setArriveAir( source.getArriveAir() );
        registerGroupDomain.setDepartureAir( source.getDepartureAir() );
        registerGroupDomain.setCantSWB( source.getCantSWB() );
        registerGroupDomain.setCantTRP( source.getCantTRP() );
        registerGroupDomain.setCantCDP( source.getCantCDP() );
        registerGroupDomain.setCantQTP( source.getCantQTP() );
        registerGroupDomain.setCantSXTP( source.getCantSXTP() );
        registerGroupDomain.setCantTWIN( source.getCantTWIN() );
        registerGroupDomain.setCantMAT( source.getCantMAT() );
        registerGroupDomain.setObservation( source.getObservation() );
        registerGroupDomain.setLink( source.getLink() );
        registerGroupDomain.setLinkBitrix( source.getLinkBitrix() );
        registerGroupDomain.setLinkWetravel( source.getLinkWetravel() );
        registerGroupDomain.setProgram( programConverter.toDomain( source.getProgram() ) );
        registerGroupDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );
        registerGroupDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        registerGroupDomain.setIdiom( idiomConverter.toDomain( source.getIdiom() ) );
        registerGroupDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );
        registerGroupDomain.setStateRegisterGroup( source.getStateRegisterGroup() );
        registerGroupDomain.setResponsible( employeeConverter.toDomain( source.getResponsible() ) );
        registerGroupDomain.setVersionQuotation( source.getVersionQuotation() );
        registerGroupDomain.setApertureWrite( source.getApertureWrite() );
        registerGroupDomain.setEmailPassengerPrincipal( source.getEmailPassengerPrincipal() );
        registerGroupDomain.setMobilePassengerPrincipal( source.getMobilePassengerPrincipal() );
        registerGroupDomain.setWhatsappPassengerPrincipal( source.getWhatsappPassengerPrincipal() );
        return registerGroupDomain;
    }

    @Override
    public RegisterGroupEntity toEntity(RegisterGroupDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterGroupEntity registerGroupEntity = new RegisterGroupEntity();

        registerGroupEntity.setId( source.getId() );
        registerGroupEntity.setDeleted( source.getDeleted() );
        registerGroupEntity.setEnabled( source.getEnabled() );
        registerGroupEntity.setMigrated( source.getMigrated() );
        registerGroupEntity.setCreatedAt( source.getCreatedAt() );
        registerGroupEntity.setUpdatedAt( source.getUpdatedAt() );
        registerGroupEntity.setDeletedAt( source.getDeletedAt() );
        registerGroupEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        registerGroupEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        registerGroupEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        registerGroupEntity.setIdentifierName( source.getIdentifierName() );
        registerGroupEntity.setTenant( source.getTenant() );
        registerGroupEntity.setName( source.getName() );
        registerGroupEntity.setDescription( source.getDescription() );
        registerGroupEntity.setIdentify( source.getIdentify() );
        registerGroupEntity.setDays( source.getDays() );
        registerGroupEntity.setCantPax( source.getCantPax() );
        registerGroupEntity.setCantFree( source.getCantFree() );
        registerGroupEntity.setCantPaxAdult( source.getCantPaxAdult() );
        registerGroupEntity.setCantPaxChildren( source.getCantPaxChildren() );
        registerGroupEntity.setTC( source.getTC() );
        registerGroupEntity.setTCLocal( source.getTCLocal() );
        registerGroupEntity.setGuide( source.getGuide() );
        registerGroupEntity.setTotalPax( source.getTotalPax() );
        registerGroupEntity.setDateAperture( source.getDateAperture() );
        registerGroupEntity.setInitTour( source.getInitTour() );
        registerGroupEntity.setEndTour( source.getEndTour() );
        registerGroupEntity.setArriveAir( source.getArriveAir() );
        registerGroupEntity.setDepartureAir( source.getDepartureAir() );
        registerGroupEntity.setCantSWB( source.getCantSWB() );
        registerGroupEntity.setCantTRP( source.getCantTRP() );
        registerGroupEntity.setCantCDP( source.getCantCDP() );
        registerGroupEntity.setCantQTP( source.getCantQTP() );
        registerGroupEntity.setCantSXTP( source.getCantSXTP() );
        registerGroupEntity.setCantTWIN( source.getCantTWIN() );
        registerGroupEntity.setCantMAT( source.getCantMAT() );
        registerGroupEntity.setObservation( source.getObservation() );
        registerGroupEntity.setLink( source.getLink() );
        registerGroupEntity.setLinkBitrix( source.getLinkBitrix() );
        registerGroupEntity.setLinkWetravel( source.getLinkWetravel() );
        registerGroupEntity.setProgram( programConverter.toEntity( source.getProgram() ) );
        registerGroupEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );
        registerGroupEntity.setCustomer( customerConverter.toEntity( source.getCustomer() ) );
        registerGroupEntity.setIdiom( idiomConverter.toEntity( source.getIdiom() ) );
        registerGroupEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );
        registerGroupEntity.setStateRegisterGroup( source.getStateRegisterGroup() );
        registerGroupEntity.setResponsible( employeeConverter.toEntity( source.getResponsible() ) );
        registerGroupEntity.setVersionQuotation( source.getVersionQuotation() );
        registerGroupEntity.setApertureWrite( source.getApertureWrite() );
        registerGroupEntity.setEmailPassengerPrincipal( source.getEmailPassengerPrincipal() );
        registerGroupEntity.setMobilePassengerPrincipal( source.getMobilePassengerPrincipal() );
        registerGroupEntity.setWhatsappPassengerPrincipal( source.getWhatsappPassengerPrincipal() );
        return registerGroupEntity;
    }

    public IdBaseDomain toDomainIdBase(RegisterGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RegisterGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getIdentify() + " - " + source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public RegisterGroupDomain toDomainIdSimpleDomainCaseCreateItinerary(RegisterGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();

        registerGroupDomain.setId( source.getId() );
        registerGroupDomain.setQuotation( new QuotationDomain());
        registerGroupDomain.getQuotation().setId( source.getQuotation().getId() );
        return registerGroupDomain;
    }

    public List<RegisterGroupDomain> toDomainAllSimpleToDomainCaseListRegisterGroup(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(entity.getId());
            registerGroupDomain.setApertureWrite(entity.getApertureWrite());
            registerGroupDomain.setStateRegisterGroup(entity.getStateRegisterGroup());
            registerGroupDomain.setIdentify(entity.getIdentify());
            registerGroupDomain.setName(entity.getName());
            registerGroupDomain.setDateAperture(entity.getDateAperture());
            registerGroupDomain.setInitTour(entity.getInitTour());
            registerGroupDomain.setEndTour(entity.getEndTour());
            registerGroupDomain.setTotalPax(entity.getTotalPax());
            registerGroupDomain.setLink(entity.getLink());
            if (entity.getCustomer() != null &&
                    entity.getCustomer().getId() != null &&
                    !entity.getCustomer().getId().isEmpty()){
                registerGroupDomain.setCustomer(new CustomerDomain());
                registerGroupDomain.getCustomer().setId(entity.getCustomer().getId());
                registerGroupDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getQuotation() != null &&
                    entity.getQuotation().getId() != null &&
                    !entity.getQuotation().getId().isEmpty()){
                registerGroupDomain.setQuotation(new QuotationDomain());
                registerGroupDomain.getQuotation().setId(entity.getQuotation().getId());
                registerGroupDomain.getQuotation().setName(entity.getQuotation().getName());
                registerGroupDomain.getQuotation().setIdentify(entity.getQuotation().getIdentify());
                registerGroupDomain.getQuotation().setStateQuotation(entity.getQuotation().getStateQuotation());
                registerGroupDomain.getQuotation().setQuotationType(entity.getQuotation().getQuotationType());
                registerGroupDomain.getQuotation().setFileNameQuotation(entity.getQuotation().getFileNameQuotation());
                registerGroupDomain.getQuotation().setFileNameItinerary(entity.getQuotation().getFileNameItinerary());
            }
            if (entity.getResponsible() != null &&
                    entity.getResponsible().getId() != null &&
                    !entity.getResponsible().getId().isEmpty()){
                registerGroupDomain.setResponsible(new EmployeeDomain());
                registerGroupDomain.getResponsible().setId(entity.getResponsible().getId());
                if (entity.getResponsible().getPerson() != null &&
                        entity.getResponsible().getPerson().getId() != null &&
                        !entity.getResponsible().getPerson().getId().isEmpty()){
                    registerGroupDomain.getResponsible().setPerson(new PersonDomain());
                    registerGroupDomain.getResponsible().getPerson().setId(entity.getResponsible().getPerson().getId());
                    registerGroupDomain.getResponsible().getPerson().setFirstName(entity.getResponsible().getPerson().getFirstName());
                    registerGroupDomain.getResponsible().getPerson().setLastName(entity.getResponsible().getPerson().getLastName());
                }
            }
            return registerGroupDomain;
        }).toList();
    }

    public PageDTO<RegisterGroupDomain> toDomainPageCaseListRegisterGroup(Page<RegisterGroupEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RegisterGroupDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRegisterGroup(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<RegisterGroupDomain> toDomainAllSimpleToDomainCaseListRegisterGroupWithoutCostSheetTwo(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(entity.getId());
            registerGroupDomain.setIdentify(entity.getIdentify());
            registerGroupDomain.setName(entity.getName());

            registerGroupDomain.setInitTour(entity.getInitTour());
            registerGroupDomain.setEndTour(entity.getEndTour());

            registerGroupDomain.setCantPaxAdult( entity.getCantPaxAdult() );
            registerGroupDomain.setCantPaxChildren( entity.getCantPaxChildren() );
            registerGroupDomain.setTC( entity.getTC() );
            registerGroupDomain.setTCLocal( entity.getTCLocal() );
            registerGroupDomain.setGuide( entity.getGuide() );

            if (entity.getCustomer() != null &&
                    entity.getCustomer().getId() != null &&
                    !entity.getCustomer().getId().isEmpty()){
                registerGroupDomain.setCustomer(new CustomerDomain());
                registerGroupDomain.getCustomer().setId(entity.getCustomer().getId());
                registerGroupDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            return registerGroupDomain;
        }).toList();
    }

    public List<RegisterGroupDomain> toDomainAllCaseAperture(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(entity.getId());
            registerGroupDomain.setIdentify(entity.getIdentify());
            registerGroupDomain.setName(entity.getName());

            registerGroupDomain.setCantPax(entity.getCantPax());
            registerGroupDomain.setCantPaxChildren(entity.getCantPaxChildren());
            registerGroupDomain.setTC(entity.getTC());
            registerGroupDomain.setTCLocal(entity.getTCLocal());
            registerGroupDomain.setGuide(entity.getGuide());
            registerGroupDomain.setTotalPax(entity.getTotalPax());

            if (entity.getResponsible() != null &&
                    entity.getResponsible().getId() != null &&
                    !entity.getResponsible().getId().isEmpty()){
                registerGroupDomain.setResponsible(new EmployeeDomain());
                registerGroupDomain.getResponsible().setId(entity.getResponsible().getId());
                registerGroupDomain.getResponsible().setName(entity.getResponsible().getName());
                if (entity.getResponsible().getPerson() != null &&
                        entity.getResponsible().getPerson().getId() != null &&
                        !entity.getResponsible().getPerson().getId().isEmpty()){
                    registerGroupDomain.getResponsible().setPerson(new PersonDomain());
                    registerGroupDomain.getResponsible().getPerson().setId(entity.getResponsible().getPerson().getId());
                    registerGroupDomain.getResponsible().getPerson().setName(entity.getResponsible().getPerson().getName());
                    registerGroupDomain.getResponsible().getPerson().setFirstName(entity.getResponsible().getPerson().getFirstName());
                    registerGroupDomain.getResponsible().getPerson().setLastName(entity.getResponsible().getPerson().getLastName());
                    registerGroupDomain.getResponsible().setName(entity.getResponsible().getPerson().getFirstName() + " " + entity.getResponsible().getPerson().getLastName());
                }
            }
            return registerGroupDomain;
        }).toList();
    }

    public List<RegisterGroupDomain> toDomainAllCaseApertureDocumentFinance(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(entity.getId());
            registerGroupDomain.setIdentify(entity.getIdentify());
            registerGroupDomain.setName(entity.getName());

            registerGroupDomain.setCantPax(entity.getCantPax());
            registerGroupDomain.setCantPaxChildren(entity.getCantPaxChildren());
            registerGroupDomain.setTC(entity.getTC());
            registerGroupDomain.setTCLocal(entity.getTCLocal());
            registerGroupDomain.setGuide(entity.getGuide());
            registerGroupDomain.setTotalPax(entity.getTotalPax());

            registerGroupDomain.setInitTour(entity.getInitTour());
            registerGroupDomain.setEndTour(entity.getEndTour());

            if (entity.getResponsible() != null &&
                    entity.getResponsible().getId() != null &&
                    !entity.getResponsible().getId().isEmpty()){
                registerGroupDomain.setResponsible(new EmployeeDomain());
                registerGroupDomain.getResponsible().setId(entity.getResponsible().getId());
                registerGroupDomain.getResponsible().setName(entity.getResponsible().getName());
                if (entity.getResponsible().getPerson() != null &&
                        entity.getResponsible().getPerson().getId() != null &&
                        !entity.getResponsible().getPerson().getId().isEmpty()){
                    registerGroupDomain.getResponsible().setPerson(new PersonDomain());
                    registerGroupDomain.getResponsible().getPerson().setId(entity.getResponsible().getPerson().getId());
                    registerGroupDomain.getResponsible().getPerson().setName(entity.getResponsible().getPerson().getName());
                    registerGroupDomain.getResponsible().getPerson().setFirstName(entity.getResponsible().getPerson().getFirstName());
                    registerGroupDomain.getResponsible().getPerson().setLastName(entity.getResponsible().getPerson().getLastName());
                    registerGroupDomain.getResponsible().setName(entity.getResponsible().getPerson().getFirstName() + " " + entity.getResponsible().getPerson().getLastName());
                }
            }

            if (entity.getCustomer() != null &&
                    entity.getCustomer().getId() != null &&
                    !entity.getCustomer().getId().isEmpty()){
                registerGroupDomain.setCustomer(new CustomerDomain());
                registerGroupDomain.getCustomer().setId(entity.getCustomer().getId());
                registerGroupDomain.getCustomer().setName(entity.getCustomer().getName());
                if (entity.getCustomer().getPartner() != null &&
                        entity.getCustomer().getPartner().getId() != null &&
                        !entity.getCustomer().getPartner().getId().isEmpty()){
                    registerGroupDomain.getCustomer().setPartner(new PartnerDomain());
                    registerGroupDomain.getCustomer().getPartner().setId(entity.getCustomer().getPartner().getId());
                    if (entity.getCustomer().getPartner().getAddress() != null){
                        registerGroupDomain.getCustomer().getPartner().setAddress(new AddressEmbeddedDomain());
                        registerGroupDomain.getCustomer().getPartner().getAddress().setStreet(entity.getCustomer().getPartner().getAddress().getStreet());
                        registerGroupDomain.getCustomer().getPartner().getAddress().setStreet2(entity.getCustomer().getPartner().getAddress().getStreet2());
                        if (entity.getCustomer().getPartner().getAddress().getCountry() != null){
                            registerGroupDomain.getCustomer().getPartner().getAddress().setCountry(new CountryDomain());
                            registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().setId(entity.getCustomer().getPartner().getAddress().getCountry().getId());
                            registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().setName(entity.getCustomer().getPartner().getAddress().getCountry().getName());
                        }
                    }
                }
            }
            return registerGroupDomain;
        }).toList();
    }

    public List<RegisterGroupDomain> toDomainAllCaseApertureVoucherIngress(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(entity.getId());
            registerGroupDomain.setIdentify(entity.getIdentify());
            registerGroupDomain.setName(entity.getName());

            registerGroupDomain.setCantPax(entity.getCantPax());
            registerGroupDomain.setCantPaxChildren(entity.getCantPaxChildren());
            registerGroupDomain.setTC(entity.getTC());
            registerGroupDomain.setTCLocal(entity.getTCLocal());
            registerGroupDomain.setGuide(entity.getGuide());
            registerGroupDomain.setTotalPax(entity.getTotalPax());

            registerGroupDomain.setInitTour(entity.getInitTour());
            registerGroupDomain.setEndTour(entity.getEndTour());

            if (entity.getResponsible() != null &&
                    entity.getResponsible().getId() != null &&
                    !entity.getResponsible().getId().isEmpty()){
                registerGroupDomain.setResponsible(new EmployeeDomain());
                registerGroupDomain.getResponsible().setId(entity.getResponsible().getId());
                registerGroupDomain.getResponsible().setName(entity.getResponsible().getName());
                if (entity.getResponsible().getPerson() != null &&
                        entity.getResponsible().getPerson().getId() != null &&
                        !entity.getResponsible().getPerson().getId().isEmpty()){
                    registerGroupDomain.getResponsible().setPerson(new PersonDomain());
                    registerGroupDomain.getResponsible().getPerson().setId(entity.getResponsible().getPerson().getId());
                    registerGroupDomain.getResponsible().getPerson().setName(entity.getResponsible().getPerson().getName());
                    registerGroupDomain.getResponsible().getPerson().setFirstName(entity.getResponsible().getPerson().getFirstName());
                    registerGroupDomain.getResponsible().getPerson().setLastName(entity.getResponsible().getPerson().getLastName());
                    registerGroupDomain.getResponsible().setName(entity.getResponsible().getPerson().getFirstName() + " " + entity.getResponsible().getPerson().getLastName());
                }
            }

            if (entity.getCustomer() != null &&
                    entity.getCustomer().getId() != null &&
                    !entity.getCustomer().getId().isEmpty()){
                registerGroupDomain.setCustomer(new CustomerDomain());
                registerGroupDomain.getCustomer().setId(entity.getCustomer().getId());
                registerGroupDomain.getCustomer().setName(entity.getCustomer().getName());
                if (entity.getCustomer().getPartner() != null &&
                        entity.getCustomer().getPartner().getId() != null &&
                        !entity.getCustomer().getPartner().getId().isEmpty()){
                    registerGroupDomain.getCustomer().setPartner(new PartnerDomain());
                    registerGroupDomain.getCustomer().getPartner().setId(entity.getCustomer().getPartner().getId());
                    if (entity.getCustomer().getPartner().getAddress() != null){
                        registerGroupDomain.getCustomer().getPartner().setAddress(new AddressEmbeddedDomain());
                        registerGroupDomain.getCustomer().getPartner().getAddress().setStreet(entity.getCustomer().getPartner().getAddress().getStreet());
                        registerGroupDomain.getCustomer().getPartner().getAddress().setStreet2(entity.getCustomer().getPartner().getAddress().getStreet2());
                        if (entity.getCustomer().getPartner().getAddress().getCountry() != null){
                            registerGroupDomain.getCustomer().getPartner().getAddress().setCountry(new CountryDomain());
                            registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().setId(entity.getCustomer().getPartner().getAddress().getCountry().getId());
                            registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().setName(entity.getCustomer().getPartner().getAddress().getCountry().getName());
                        }
                    }
                }
            }
            return registerGroupDomain;
        }).toList();
    }

    public List<RegisterGroupDomain> toDomainAllCaseApertureVoucherEgress(List<RegisterGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(entity.getId());
            registerGroupDomain.setIdentify(entity.getIdentify());
            registerGroupDomain.setName(entity.getName());

            registerGroupDomain.setCantPax(entity.getCantPax());
            registerGroupDomain.setCantPaxChildren(entity.getCantPaxChildren());
            registerGroupDomain.setTC(entity.getTC());
            registerGroupDomain.setTCLocal(entity.getTCLocal());
            registerGroupDomain.setGuide(entity.getGuide());
            registerGroupDomain.setTotalPax(entity.getTotalPax());

            registerGroupDomain.setInitTour(entity.getInitTour());
            registerGroupDomain.setEndTour(entity.getEndTour());

            if (entity.getResponsible() != null &&
                    entity.getResponsible().getId() != null &&
                    !entity.getResponsible().getId().isEmpty()){
                registerGroupDomain.setResponsible(new EmployeeDomain());
                registerGroupDomain.getResponsible().setId(entity.getResponsible().getId());
                registerGroupDomain.getResponsible().setName(entity.getResponsible().getName());
                if (entity.getResponsible().getPerson() != null &&
                        entity.getResponsible().getPerson().getId() != null &&
                        !entity.getResponsible().getPerson().getId().isEmpty()){
                    registerGroupDomain.getResponsible().setPerson(new PersonDomain());
                    registerGroupDomain.getResponsible().getPerson().setId(entity.getResponsible().getPerson().getId());
                    registerGroupDomain.getResponsible().getPerson().setName(entity.getResponsible().getPerson().getName());
                    registerGroupDomain.getResponsible().getPerson().setFirstName(entity.getResponsible().getPerson().getFirstName());
                    registerGroupDomain.getResponsible().getPerson().setLastName(entity.getResponsible().getPerson().getLastName());
                    registerGroupDomain.getResponsible().setName(entity.getResponsible().getPerson().getFirstName() + " " + entity.getResponsible().getPerson().getLastName());
                }
            }

            if (entity.getCustomer() != null &&
                    entity.getCustomer().getId() != null &&
                    !entity.getCustomer().getId().isEmpty()){
                registerGroupDomain.setCustomer(new CustomerDomain());
                registerGroupDomain.getCustomer().setId(entity.getCustomer().getId());
                registerGroupDomain.getCustomer().setName(entity.getCustomer().getName());
                if (entity.getCustomer().getPartner() != null &&
                        entity.getCustomer().getPartner().getId() != null &&
                        !entity.getCustomer().getPartner().getId().isEmpty()){
                    registerGroupDomain.getCustomer().setPartner(new PartnerDomain());
                    registerGroupDomain.getCustomer().getPartner().setId(entity.getCustomer().getPartner().getId());
                    if (entity.getCustomer().getPartner().getAddress() != null){
                        registerGroupDomain.getCustomer().getPartner().setAddress(new AddressEmbeddedDomain());
                        registerGroupDomain.getCustomer().getPartner().getAddress().setStreet(entity.getCustomer().getPartner().getAddress().getStreet());
                        registerGroupDomain.getCustomer().getPartner().getAddress().setStreet2(entity.getCustomer().getPartner().getAddress().getStreet2());
                        if (entity.getCustomer().getPartner().getAddress().getCountry() != null){
                            registerGroupDomain.getCustomer().getPartner().getAddress().setCountry(new CountryDomain());
                            registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().setId(entity.getCustomer().getPartner().getAddress().getCountry().getId());
                            registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().setName(entity.getCustomer().getPartner().getAddress().getCountry().getName());
                        }
                    }
                }
            }
            return registerGroupDomain;
        }).toList();
    }

    public RegisterGroupDomain toDomainIdSimpleDomainCaseGetModificationUserData(RegisterGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId( source.getId() );
        registerGroupDomain.setUpdatedAt( source.getUpdatedAt() );
        if (source.getUpdatedBy() != null &&
                source.getUpdatedBy().getId() != null &&
                !source.getUpdatedBy().getId().isEmpty()){
            registerGroupDomain.setUpdatedBy(new UserBaseDomain());
            registerGroupDomain.getUpdatedBy().setId( source.getUpdatedBy().getId() );
            registerGroupDomain.getUpdatedBy().setUserName( source.getUpdatedBy().getUserName() );
            registerGroupDomain.getUpdatedBy().setEmail( source.getUpdatedBy().getEmail() );
            registerGroupDomain.getUpdatedBy().setFullName( source.getUpdatedBy().getFullName() );
        }
        return registerGroupDomain;
    }

    public RegisterGroupDomain toDomainIdSimpleDomainCaseGetModificationCustomerData(RegisterGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId( source.getId() );
        return registerGroupDomain;
    }
}
