package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformacreditnote;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaCreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
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
public class ProformaCreditNoteConverter implements Converter<ProformaCreditNoteDomain, ProformaCreditNoteEntity> {

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
    public ProformaCreditNoteDomain toDomain(ProformaCreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaCreditNoteDomain proformaCreditNoteDomain = new ProformaCreditNoteDomain();

        proformaCreditNoteDomain.setId( source.getId() );
        proformaCreditNoteDomain.setDeleted( source.getDeleted() );
        proformaCreditNoteDomain.setEnabled( source.getEnabled() );
        proformaCreditNoteDomain.setMigrated( source.getMigrated() );
        proformaCreditNoteDomain.setCreatedAt( source.getCreatedAt() );
        proformaCreditNoteDomain.setUpdatedAt( source.getUpdatedAt() );
        proformaCreditNoteDomain.setDeletedAt( source.getDeletedAt() );
        proformaCreditNoteDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        proformaCreditNoteDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        proformaCreditNoteDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        proformaCreditNoteDomain.setIdentifierName( source.getIdentifierName() );
        proformaCreditNoteDomain.setTenant( source.getTenant() );
        proformaCreditNoteDomain.setName( source.getName() );
        proformaCreditNoteDomain.setDescription( source.getDescription() );
        proformaCreditNoteDomain.setIdentify( source.getIdentify() );

        proformaCreditNoteDomain.setTypeInheritanceProforma(source.getTypeInheritanceProforma());

        proformaCreditNoteDomain.setTitle( source.getTitle() );
        proformaCreditNoteDomain.setDate( source.getDate() );
        proformaCreditNoteDomain.setAddress( source.getAddress() );
        proformaCreditNoteDomain.setReference( source.getReference() );
        proformaCreditNoteDomain.setDateInit( source.getDateInit() );
        proformaCreditNoteDomain.setDateEnd( source.getDateEnd() );
        proformaCreditNoteDomain.setNoPax( source.getNoPax() );
        proformaCreditNoteDomain.setImportTotalUsd( source.getImportTotalUsd() );
        proformaCreditNoteDomain.setImportTotalSoles( source.getImportTotalSoles() );
        proformaCreditNoteDomain.setImportAmortizationUsd( source.getImportAmortizationUsd() );
        proformaCreditNoteDomain.setImportAmortizationSoles( source.getImportAmortizationSoles() );
        proformaCreditNoteDomain.setImportSaldxAmortizarUsd( source.getImportSaldxAmortizarUsd() );
        proformaCreditNoteDomain.setImportSaldxAmortizarSoles( source.getImportSaldxAmortizarSoles() );
        proformaCreditNoteDomain.setPriceUnit( source.getPriceUnit() );
        proformaCreditNoteDomain.setCantRegister( source.getCantRegister() );
        proformaCreditNoteDomain.setDateCancel( source.getDateCancel() );

        proformaCreditNoteDomain.setProformaType( source.getProformaType() );
        proformaCreditNoteDomain.setProformaState( source.getProformaState() );

        proformaCreditNoteDomain.setDateProcess( dateProcessConverter.toDomain(source.getDateProcess()) );
        proformaCreditNoteDomain.setCountry( countryConverter.toDomain(source.getCountry()) );
        proformaCreditNoteDomain.setCurrency( currencyConverter.toDomain(source.getCurrency()) );
        proformaCreditNoteDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        proformaCreditNoteDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        proformaCreditNoteDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        proformaCreditNoteDomain.setEmployee(employeeConverter.toDomain(source.getEmployee()));

        return proformaCreditNoteDomain;
    }

    @Override
    public ProformaCreditNoteEntity toEntity(ProformaCreditNoteDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProformaCreditNoteEntity proformaCreditNoteEntity = new ProformaCreditNoteEntity();

        proformaCreditNoteEntity.setId( source.getId() );
        proformaCreditNoteEntity.setDeleted( source.getDeleted() );
        proformaCreditNoteEntity.setEnabled( source.getEnabled() );
        proformaCreditNoteEntity.setMigrated( source.getMigrated() );
        proformaCreditNoteEntity.setCreatedAt( source.getCreatedAt() );
        proformaCreditNoteEntity.setUpdatedAt( source.getUpdatedAt() );
        proformaCreditNoteEntity.setDeletedAt( source.getDeletedAt() );
        proformaCreditNoteEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        proformaCreditNoteEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        proformaCreditNoteEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        proformaCreditNoteEntity.setIdentifierName( source.getIdentifierName() );
        proformaCreditNoteEntity.setTenant( source.getTenant() );
        proformaCreditNoteEntity.setName( source.getName() );
        proformaCreditNoteEntity.setDescription( source.getDescription() );
        proformaCreditNoteEntity.setIdentify( source.getIdentify() );

        proformaCreditNoteEntity.setTypeInheritanceProforma(source.getTypeInheritanceProforma());

        proformaCreditNoteEntity.setTitle( source.getTitle() );
        proformaCreditNoteEntity.setDate( source.getDate() );
        proformaCreditNoteEntity.setAddress( source.getAddress() );
        proformaCreditNoteEntity.setReference( source.getReference() );
        proformaCreditNoteEntity.setDateInit( source.getDateInit() );
        proformaCreditNoteEntity.setDateEnd( source.getDateEnd() );
        proformaCreditNoteEntity.setNoPax( source.getNoPax() );
        proformaCreditNoteEntity.setImportTotalUsd( source.getImportTotalUsd() );
        proformaCreditNoteEntity.setImportTotalSoles( source.getImportTotalSoles() );
        proformaCreditNoteEntity.setImportAmortizationUsd( source.getImportAmortizationUsd() );
        proformaCreditNoteEntity.setImportAmortizationSoles( source.getImportAmortizationSoles() );
        proformaCreditNoteEntity.setImportSaldxAmortizarUsd( source.getImportSaldxAmortizarUsd() );
        proformaCreditNoteEntity.setImportSaldxAmortizarSoles( source.getImportSaldxAmortizarSoles() );
        proformaCreditNoteEntity.setPriceUnit( source.getPriceUnit() );
        proformaCreditNoteEntity.setCantRegister( source.getCantRegister() );
        proformaCreditNoteEntity.setDateCancel( source.getDateCancel() );

        proformaCreditNoteEntity.setProformaType( source.getProformaType() );
        proformaCreditNoteEntity.setProformaState( source.getProformaState() );

        proformaCreditNoteEntity.setDateProcess( dateProcessConverter.toEntity(source.getDateProcess()) );
        proformaCreditNoteEntity.setCountry( countryConverter.toEntity(source.getCountry()) );
        proformaCreditNoteEntity.setCurrency( currencyConverter.toEntity(source.getCurrency()) );
        proformaCreditNoteEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        proformaCreditNoteEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        proformaCreditNoteEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        proformaCreditNoteEntity.setEmployee(employeeConverter.toEntity(source.getEmployee()));

        return proformaCreditNoteEntity;
    }

    public IdBaseDomain toDomainIdBase(ProformaCreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProformaCreditNoteEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProformaCreditNoteEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProformaCreditNoteDomain> toDomainAllSimpleToDomainCaseListProformaCreditNote(List<ProformaCreditNoteEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProformaCreditNoteDomain proformaCreditNoteDomain = new ProformaCreditNoteDomain();
            proformaCreditNoteDomain.setId(entity.getId());
            proformaCreditNoteDomain.setIdentify(entity.getIdentify());
            proformaCreditNoteDomain.setDate(entity.getDate());
            proformaCreditNoteDomain.setProformaState(entity.getProformaState());
            proformaCreditNoteDomain.setDateInit(entity.getDateInit());
            proformaCreditNoteDomain.setDateEnd(entity.getDateEnd());
            proformaCreditNoteDomain.setImportTotalUsd(entity.getImportTotalUsd());
            proformaCreditNoteDomain.setImportTotalSoles(entity.getImportTotalSoles());
            proformaCreditNoteDomain.setDateCancel(entity.getDateCancel());
            if (entity.getCurrency() != null && entity.getCurrency().getId() != null && !entity.getCurrency().getId().isEmpty()){
                proformaCreditNoteDomain.setCurrency(new CurrencyDomain());
                proformaCreditNoteDomain.getCurrency().setId(entity.getCurrency().getId());
                proformaCreditNoteDomain.getCurrency().setName(entity.getCurrency().getName());
            }
            if (entity.getEmployee() != null && entity.getEmployee().getId() != null && !entity.getEmployee().getId().isEmpty()){
                proformaCreditNoteDomain.setEmployee(new EmployeeDomain());
                proformaCreditNoteDomain.getEmployee().setId(entity.getEmployee().getId());
                if (entity.getEmployee().getPerson() != null &&
                        entity.getEmployee().getPerson().getId() != null &&
                        !entity.getEmployee().getPerson().getId().isEmpty()){
                    proformaCreditNoteDomain.getEmployee().setPerson(new PersonDomain());
                    proformaCreditNoteDomain.getEmployee().getPerson().setId(entity.getEmployee().getPerson().getId());
                    proformaCreditNoteDomain.getEmployee().getPerson().setFirstName(entity.getEmployee().getPerson().getFirstName());
                    proformaCreditNoteDomain.getEmployee().getPerson().setLastName(entity.getEmployee().getPerson().getLastName());
                }
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                proformaCreditNoteDomain.setCustomer(new CustomerDomain());
                proformaCreditNoteDomain.getCustomer().setId(entity.getCustomer().getId());
                proformaCreditNoteDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            if (entity.getRegisterGroup() != null && entity.getRegisterGroup().getId() != null && !entity.getRegisterGroup().getId().isEmpty()){
                proformaCreditNoteDomain.setRegisterGroup(new RegisterGroupDomain());
                proformaCreditNoteDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                proformaCreditNoteDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                proformaCreditNoteDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return proformaCreditNoteDomain;
        }).toList();
    }

    public PageDTO<ProformaCreditNoteDomain> toDomainPageCaseListProformaCreditNote(Page<ProformaCreditNoteEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ProformaCreditNoteDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListProformaCreditNote(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
