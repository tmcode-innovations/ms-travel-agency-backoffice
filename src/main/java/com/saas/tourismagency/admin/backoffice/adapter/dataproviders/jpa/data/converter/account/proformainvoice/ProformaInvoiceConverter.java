package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformainvoice;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaInvoiceEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaInvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProformaInvoiceConverter implements Converter<ProformaInvoiceDomain, ProformaInvoiceEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DateProcessConverter dateProcessConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public ProformaInvoiceDomain toDomain(ProformaInvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaInvoiceDomain proformaInvoiceDomain = new ProformaInvoiceDomain();

        proformaInvoiceDomain.setId( source.getId() );
        proformaInvoiceDomain.setDeleted( source.getDeleted() );
        proformaInvoiceDomain.setEnabled( source.getEnabled() );
        proformaInvoiceDomain.setMigrated( source.getMigrated() );
        proformaInvoiceDomain.setCreatedAt( source.getCreatedAt() );
        proformaInvoiceDomain.setUpdatedAt( source.getUpdatedAt() );
        proformaInvoiceDomain.setDeletedAt( source.getDeletedAt() );
        proformaInvoiceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        proformaInvoiceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        proformaInvoiceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        proformaInvoiceDomain.setIdentifierName( source.getIdentifierName() );
        proformaInvoiceDomain.setTenant( source.getTenant() );
        proformaInvoiceDomain.setName( source.getName() );
        proformaInvoiceDomain.setDescription( source.getDescription() );
        proformaInvoiceDomain.setIdentify( source.getIdentify() );

        proformaInvoiceDomain.setTypeInheritanceProforma(source.getTypeInheritanceProforma());

        proformaInvoiceDomain.setTitle( source.getTitle() );
        proformaInvoiceDomain.setDate( source.getDate() );
        proformaInvoiceDomain.setAddress( source.getAddress() );
        proformaInvoiceDomain.setReference( source.getReference() );
        proformaInvoiceDomain.setDateInit( source.getDateInit() );
        proformaInvoiceDomain.setDateEnd( source.getDateEnd() );
        proformaInvoiceDomain.setNoPax( source.getNoPax() );
        proformaInvoiceDomain.setImportTotalUsd( source.getImportTotalUsd() );
        proformaInvoiceDomain.setImportTotalSoles( source.getImportTotalSoles() );
        proformaInvoiceDomain.setImportAmortizationUsd( source.getImportAmortizationUsd() );
        proformaInvoiceDomain.setImportAmortizationSoles( source.getImportAmortizationSoles() );
        proformaInvoiceDomain.setImportSaldxAmortizarUsd( source.getImportSaldxAmortizarUsd() );
        proformaInvoiceDomain.setImportSaldxAmortizarSoles( source.getImportSaldxAmortizarSoles() );
        proformaInvoiceDomain.setPriceUnit( source.getPriceUnit() );
        proformaInvoiceDomain.setCantRegister( source.getCantRegister() );
        proformaInvoiceDomain.setDateCancel( source.getDateCancel() );

        proformaInvoiceDomain.setProformaType( source.getProformaType() );
        proformaInvoiceDomain.setProformaState( source.getProformaState() );

        proformaInvoiceDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        proformaInvoiceDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        proformaInvoiceDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        proformaInvoiceDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        proformaInvoiceDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        proformaInvoiceDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        proformaInvoiceDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()));

        return proformaInvoiceDomain;
    }

    @Override
    public ProformaInvoiceEntity toEntity(ProformaInvoiceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaInvoiceEntity proformaInvoiceEntity = new ProformaInvoiceEntity();

        proformaInvoiceEntity.setId( source.getId() );
        proformaInvoiceEntity.setDeleted( source.getDeleted() );
        proformaInvoiceEntity.setEnabled( source.getEnabled() );
        proformaInvoiceEntity.setMigrated( source.getMigrated() );
        proformaInvoiceEntity.setCreatedAt( source.getCreatedAt() );
        proformaInvoiceEntity.setUpdatedAt( source.getUpdatedAt() );
        proformaInvoiceEntity.setDeletedAt( source.getDeletedAt() );
        proformaInvoiceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        proformaInvoiceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        proformaInvoiceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        proformaInvoiceEntity.setIdentifierName( source.getIdentifierName() );
        proformaInvoiceEntity.setTenant( source.getTenant() );
        proformaInvoiceEntity.setName( source.getName() );
        proformaInvoiceEntity.setDescription( source.getDescription() );
        proformaInvoiceEntity.setIdentify( source.getIdentify() );

        proformaInvoiceEntity.setTypeInheritanceProforma(source.getTypeInheritanceProforma());

        proformaInvoiceEntity.setTitle( source.getTitle() );
        proformaInvoiceEntity.setDate( source.getDate() );
        proformaInvoiceEntity.setAddress( source.getAddress() );
        proformaInvoiceEntity.setReference( source.getReference() );
        proformaInvoiceEntity.setDateInit( source.getDateInit() );
        proformaInvoiceEntity.setDateEnd( source.getDateEnd() );
        proformaInvoiceEntity.setNoPax( source.getNoPax() );
        proformaInvoiceEntity.setImportTotalUsd( source.getImportTotalUsd() );
        proformaInvoiceEntity.setImportTotalSoles( source.getImportTotalSoles() );
        proformaInvoiceEntity.setImportAmortizationUsd( source.getImportAmortizationUsd() );
        proformaInvoiceEntity.setImportAmortizationSoles( source.getImportAmortizationSoles() );
        proformaInvoiceEntity.setImportSaldxAmortizarUsd( source.getImportSaldxAmortizarUsd() );
        proformaInvoiceEntity.setImportSaldxAmortizarSoles( source.getImportSaldxAmortizarSoles() );
        proformaInvoiceEntity.setPriceUnit( source.getPriceUnit() );
        proformaInvoiceEntity.setCantRegister( source.getCantRegister() );
        proformaInvoiceEntity.setDateCancel( source.getDateCancel() );

        proformaInvoiceEntity.setProformaType( source.getProformaType() );
        proformaInvoiceEntity.setProformaState( source.getProformaState() );

        proformaInvoiceEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        proformaInvoiceEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        proformaInvoiceEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        proformaInvoiceEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        proformaInvoiceEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        proformaInvoiceEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        proformaInvoiceEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()));

        return proformaInvoiceEntity;
    }

    public IdBaseDomain toDomainIdBase(ProformaInvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProformaInvoiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProformaInvoiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProformaInvoiceDomain> toDomainAllSimpleToDomainCaseListProformaInvoice(List<ProformaInvoiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProformaInvoiceDomain proformaInvoiceDomain = new ProformaInvoiceDomain();
            proformaInvoiceDomain.setId(entity.getId());
            proformaInvoiceDomain.setIdentify(entity.getIdentify());
            proformaInvoiceDomain.setDate(entity.getDate());
            proformaInvoiceDomain.setProformaState(entity.getProformaState());
            proformaInvoiceDomain.setDateInit(entity.getDateInit());
            proformaInvoiceDomain.setDateEnd(entity.getDateEnd());
            proformaInvoiceDomain.setImportTotalUsd(entity.getImportTotalUsd());
            proformaInvoiceDomain.setImportTotalSoles(entity.getImportTotalSoles());
            proformaInvoiceDomain.setDateCancel(entity.getDateCancel());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                proformaInvoiceDomain.setCurrency(new CurrencyDomain());
                proformaInvoiceDomain.getCurrency().setId(entity.getCurrency().getId());
                proformaInvoiceDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getEmployee() != null && entity.getEmployee().getId() != null && !entity.getEmployee().getId().isEmpty()){
                proformaInvoiceDomain.setEmployee(new EmployeeDomain());
                proformaInvoiceDomain.getEmployee().setId(entity.getEmployee().getId());
                if (entity.getEmployee().getPerson() != null &&
                        entity.getEmployee().getPerson().getId() != null &&
                        !entity.getEmployee().getPerson().getId().isEmpty()){
                    proformaInvoiceDomain.getEmployee().setPerson(new PersonDomain());
                    proformaInvoiceDomain.getEmployee().getPerson().setId(entity.getEmployee().getPerson().getId());
                    proformaInvoiceDomain.getEmployee().getPerson().setFirstName(entity.getEmployee().getPerson().getFirstName());
                    proformaInvoiceDomain.getEmployee().getPerson().setLastName(entity.getEmployee().getPerson().getLastName());
                }
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                proformaInvoiceDomain.setCustomer(new CustomerDomain());
                proformaInvoiceDomain.getCustomer().setId(entity.getCustomer().getId());
                proformaInvoiceDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                proformaInvoiceDomain.setRegisterGroup(new RegisterGroupDomain());
                proformaInvoiceDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                proformaInvoiceDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                proformaInvoiceDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return proformaInvoiceDomain;
        }).toList();
    }

    public PageDTO<ProformaInvoiceDomain> toDomainPageCaseListProformaInvoice(Page<ProformaInvoiceEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ProformaInvoiceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListProformaInvoice(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
