package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom.IdiomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class QuotationConverter implements Converter<QuotationDomain, QuotationEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private ProgramConverter programConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private IdiomConverter idiomConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Override
    public QuotationDomain toDomain(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        QuotationDomain quotationDomain = new QuotationDomain();

        quotationDomain.setId( source.getId() );
        quotationDomain.setDeleted( source.getDeleted() );
        quotationDomain.setEnabled( source.getEnabled() );
        quotationDomain.setMigrated( source.getMigrated() );
        quotationDomain.setCreatedAt( source.getCreatedAt() );
        quotationDomain.setUpdatedAt( source.getUpdatedAt() );
        quotationDomain.setDeletedAt( source.getDeletedAt() );
        quotationDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        quotationDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        quotationDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        quotationDomain.setIdentifierName( source.getIdentifierName() );
        quotationDomain.setTenant( source.getTenant() );
        quotationDomain.setName( source.getName() );
        quotationDomain.setDescription( source.getDescription() );
        quotationDomain.setIdentify( source.getIdentify() );
        quotationDomain.setCantDayTour( source.getCantDayTour() );
        quotationDomain.setPaxTotal( source.getPaxTotal() );
        quotationDomain.setPaxAdult( source.getPaxAdult() );
        quotationDomain.setPaxChild( source.getPaxChild() );
        quotationDomain.setPaxTc( source.getPaxTc() );
        quotationDomain.setPaxTcLocal( source.getPaxTcLocal() );
        quotationDomain.setGuide( source.getGuide() );
        quotationDomain.setDateInitTour( source.getDateInitTour() );
        quotationDomain.setDateEndTour( source.getDateEndTour() );
        quotationDomain.setObservation( source.getObservation() );
        quotationDomain.setStateQuotation( source.getStateQuotation() );
        quotationDomain.setAmount( source.getAmount() );
        quotationDomain.setProfitPercent( source.getProfitPercent() );
        quotationDomain.setOtherExpenses( source.getOtherExpenses() );
        quotationDomain.setLinkGDrive( source.getLinkGDrive() );
        quotationDomain.setLinkBitrix( source.getLinkBitrix() );
        quotationDomain.setLinkWetravel( source.getLinkWetravel() );
        if (source.getCustomer() != null){
            quotationDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        }else{
            quotationDomain.setCustomer( null );
        }
        if (source.getProgram() != null){
            quotationDomain.setProgram( programConverter.toDomain( source.getProgram() ) );
        }else{
            quotationDomain.setProgram( null );
        }
        if (source.getEmployee() != null){
            quotationDomain.setEmployee( employeeConverter.toDomain( source.getEmployee() ) );
        }else{
            quotationDomain.setEmployee( null );
        }
        if (source.getIdiom() != null){
            quotationDomain.setIdiom( idiomConverter.toDomain( source.getIdiom() ) );
        }else{
            quotationDomain.setIdiom( null );
        }
        if (source.getCountry() != null){
            quotationDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );
        }else{
            quotationDomain.setCountry( null );
        }
        if (source.getResponsibleFile() != null){
            quotationDomain.setResponsibleFile( employeeConverter.toDomain( source.getResponsibleFile() ) );
        }else{
            quotationDomain.setResponsibleFile( null );
        }
        quotationDomain.setIdentifyFile( source.getIdentifyFile() );
        quotationDomain.setQuotationType( source.getQuotationType() );
        quotationDomain.setUrlFileQuotation( source.getUrlFileQuotation() );
        quotationDomain.setUrlFileItinerary( source.getUrlFileItinerary() );
        quotationDomain.setFileNameQuotation( source.getFileNameQuotation() );
        quotationDomain.setFileNameItinerary( source.getFileNameItinerary() );
        if (source.getCurrency() != null){
            quotationDomain.setCurrency( currencyConverter.toDomain( source.getCurrency() ) );
        }else{
            quotationDomain.setCurrency( null );
        }
        quotationDomain.setEmailPassengerPrincipal( source.getEmailPassengerPrincipal() );
        quotationDomain.setMobilePassengerPrincipal( source.getMobilePassengerPrincipal() );
        quotationDomain.setWhatsappPassengerPrincipal( source.getWhatsappPassengerPrincipal() );
        return quotationDomain;
    }

    @Override
    public QuotationEntity toEntity(QuotationDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        QuotationEntity quotationEntity = new QuotationEntity();

        quotationEntity.setId( source.getId() );
        quotationEntity.setDeleted( source.getDeleted() );
        quotationEntity.setEnabled( source.getEnabled() );
        quotationEntity.setMigrated( source.getMigrated() );
        quotationEntity.setCreatedAt( source.getCreatedAt() );
        quotationEntity.setUpdatedAt( source.getUpdatedAt() );
        quotationEntity.setDeletedAt( source.getDeletedAt() );
        quotationEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        quotationEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        quotationEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        quotationEntity.setIdentifierName( source.getIdentifierName() );
        quotationEntity.setTenant( source.getTenant() );
        quotationEntity.setName( source.getName() );
        quotationEntity.setDescription( source.getDescription() );
        quotationEntity.setIdentify( source.getIdentify() );
        quotationEntity.setCantDayTour( source.getCantDayTour() );
        quotationEntity.setPaxTotal( source.getPaxTotal() );
        quotationEntity.setPaxAdult( source.getPaxAdult() );
        quotationEntity.setPaxChild( source.getPaxChild() );
        quotationEntity.setPaxTc( source.getPaxTc() );
        quotationEntity.setPaxTcLocal( source.getPaxTcLocal() );
        quotationEntity.setGuide( source.getGuide() );
        quotationEntity.setDateInitTour( source.getDateInitTour() );
        quotationEntity.setDateEndTour( source.getDateEndTour() );
        quotationEntity.setObservation( source.getObservation() );
        quotationEntity.setStateQuotation( source.getStateQuotation() );
        quotationEntity.setAmount( source.getAmount() );
        quotationEntity.setProfitPercent( source.getProfitPercent() );
        quotationEntity.setOtherExpenses( source.getOtherExpenses() );
        quotationEntity.setLinkGDrive( source.getLinkGDrive() );
        quotationEntity.setLinkBitrix( source.getLinkBitrix() );
        quotationEntity.setLinkWetravel( source.getLinkWetravel() );
        if (source.getCustomer() != null){
            quotationEntity.setCustomer( customerConverter.toEntity( source.getCustomer() ) );
        }else{
            quotationEntity.setCustomer( null );
        }
        if (source.getProgram() != null){
            quotationEntity.setProgram( programConverter.toEntity( source.getProgram() ) );
        }else{
            quotationEntity.setProgram( null );
        }
        if (source.getEmployee() != null){
            quotationEntity.setEmployee( employeeConverter.toEntity( source.getEmployee() ) );
        }else{
            quotationEntity.setEmployee( null );
        }
        if (source.getIdiom() != null){
            quotationEntity.setIdiom( idiomConverter.toEntity( source.getIdiom() ) );
        }else{
            quotationEntity.setIdiom( null );
        }
        if (source.getCountry() != null){
            quotationEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );
        }else{
            quotationEntity.setCountry( null );
        }
        if (source.getResponsibleFile() != null){
            quotationEntity.setResponsibleFile( employeeConverter.toEntity( source.getResponsibleFile() ) );
        }else{
            quotationEntity.setResponsibleFile( null );
        }
        quotationEntity.setIdentifyFile( source.getIdentifyFile() );
        quotationEntity.setQuotationType( source.getQuotationType() );
        quotationEntity.setUrlFileQuotation( source.getUrlFileQuotation() );
        quotationEntity.setUrlFileItinerary( source.getUrlFileItinerary() );
        quotationEntity.setFileNameQuotation( source.getFileNameQuotation() );
        quotationEntity.setFileNameItinerary( source.getFileNameItinerary() );
        if (source.getCurrency() != null){
            quotationEntity.setCurrency( currencyConverter.toEntity( source.getCurrency() ) );
        }else{
            quotationEntity.setCurrency( null );
        }
        quotationEntity.setEmailPassengerPrincipal( source.getEmailPassengerPrincipal() );
        quotationEntity.setMobilePassengerPrincipal( source.getMobilePassengerPrincipal() );
        quotationEntity.setWhatsappPassengerPrincipal( source.getWhatsappPassengerPrincipal() );
        return quotationEntity;
    }

    public IdBaseDomain toDomainIdBase(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<QuotationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public QuotationDomain toDomainIdSimpleDomainCaseCreateItinerary(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId( source.getId() );
        return quotationDomain;
    }

    public List<QuotationDomain> toDomainAllSimpleToDomainCaseListQuotation(List<QuotationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            QuotationDomain quotationDomain = new QuotationDomain();
            quotationDomain.setId(entity.getId());
            quotationDomain.setStateQuotation(entity.getStateQuotation());
            quotationDomain.setFileNameQuotation(entity.getFileNameQuotation());
            quotationDomain.setFileNameItinerary(entity.getFileNameItinerary());
            quotationDomain.setIdentify(entity.getIdentify());
            quotationDomain.setName(entity.getName());
            if (entity.getCustomer() != null &&
                    entity.getCustomer().getId() != null &&
                    !entity.getCustomer().getId().isEmpty()){
                quotationDomain.setCustomer(new CustomerDomain());
                quotationDomain.getCustomer().setId(entity.getCustomer().getId());
                quotationDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getResponsibleFile() != null &&
                    entity.getResponsibleFile().getId() != null &&
                    !entity.getResponsibleFile().getId().isEmpty()){
                quotationDomain.setResponsibleFile(new EmployeeDomain());
                quotationDomain.getResponsibleFile().setId(entity.getResponsibleFile().getId());
                if (entity.getResponsibleFile().getPerson() != null &&
                        entity.getResponsibleFile().getPerson().getId() != null &&
                        !entity.getResponsibleFile().getPerson().getId().isEmpty()){
                    quotationDomain.getResponsibleFile().setPerson(new PersonDomain());
                    quotationDomain.getResponsibleFile().getPerson().setId(entity.getResponsibleFile().getPerson().getId());
                    quotationDomain.getResponsibleFile().getPerson().setFirstName(entity.getResponsibleFile().getPerson().getFirstName());
                    quotationDomain.getResponsibleFile().getPerson().setLastName(entity.getResponsibleFile().getPerson().getLastName());
                }
            }
            quotationDomain.setCreatedAt(entity.getCreatedAt());
            quotationDomain.setIdentifyFile(entity.getIdentifyFile());
            if (entity.getEmployee() != null &&
                    entity.getEmployee().getId() != null &&
                    !entity.getEmployee().getId().isEmpty()){
                quotationDomain.setEmployee(new EmployeeDomain());
                quotationDomain.getEmployee().setId(entity.getEmployee().getId());
                if (entity.getEmployee().getPerson() != null &&
                        entity.getEmployee().getPerson().getId() != null &&
                        !entity.getEmployee().getPerson().getId().isEmpty()){
                    quotationDomain.getEmployee().setPerson(new PersonDomain());
                    quotationDomain.getEmployee().getPerson().setId(entity.getEmployee().getPerson().getId());
                    quotationDomain.getEmployee().getPerson().setFirstName(entity.getEmployee().getPerson().getFirstName());
                    quotationDomain.getEmployee().getPerson().setLastName(entity.getEmployee().getPerson().getLastName());
                }
            }
            quotationDomain.setAmount(entity.getAmount());
            return quotationDomain;
        }).toList();
    }

    public PageDTO<QuotationDomain> toDomainPageCaseListQuotation(Page<QuotationEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<QuotationDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListQuotation(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public QuotationDomain toDomainIdSimpleDomainCaseGetModificationData(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId( source.getId() );
        quotationDomain.setDateInitTour( source.getDateInitTour() );
        quotationDomain.setDateEndTour( source.getDateEndTour() );
        quotationDomain.setCantDayTour( source.getCantDayTour() );
        quotationDomain.setAmount( source.getAmount() );
        quotationDomain.setStateQuotation( source.getStateQuotation() );
        return quotationDomain;
    }

    public QuotationDomain toDomainIdSimpleDomainCaseGeneric(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId( source.getId() );
        quotationDomain.setIdentify( source.getIdentify() );
        quotationDomain.setIdentifyFile( source.getIdentifyFile() );
        return quotationDomain;
    }

    public QuotationDomain toDomainIdSimpleDomainCaseGetModificationUserData(QuotationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId( source.getId() );
        quotationDomain.setUpdatedAt( source.getUpdatedAt() );
        if (source.getUpdatedBy() != null &&
                source.getUpdatedBy().getId() != null &&
                !source.getUpdatedBy().getId().isEmpty()){
            quotationDomain.setUpdatedBy(new UserBaseDomain());
            quotationDomain.getUpdatedBy().setId( source.getUpdatedBy().getId() );
            quotationDomain.getUpdatedBy().setUserName( source.getUpdatedBy().getUserName() );
            quotationDomain.getUpdatedBy().setEmail( source.getUpdatedBy().getEmail() );
            quotationDomain.getUpdatedBy().setFullName( source.getUpdatedBy().getFullName() );
        }
        return quotationDomain;
    }

    public List<QuotationDomain> toDomainAllSimpleToDomainCaseToolUpdateEmployee(List<QuotationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            QuotationDomain quotationDomain = new QuotationDomain();
            quotationDomain.setId( entity.getId() );
            quotationDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( entity.getCreatedBy() ) );
            return quotationDomain;
        }).toList();
    }
}
