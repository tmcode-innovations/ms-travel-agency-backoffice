package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguide;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ExpenseGuideEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseGuideConverter implements Converter<ExpenseGuideDomain, ExpenseGuideEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private GuideConverter guideConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private SupplierLodgingConverter supplierLodgingConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private DateProcessConverter dateProcessConverter;

    @Override
    public ExpenseGuideDomain toDomain(ExpenseGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ExpenseGuideDomain expenseGuideDomain = new ExpenseGuideDomain();

        expenseGuideDomain.setId( source.getId() );
        expenseGuideDomain.setDeleted( source.getDeleted() );
        expenseGuideDomain.setEnabled( source.getEnabled() );
        expenseGuideDomain.setMigrated( source.getMigrated() );
        expenseGuideDomain.setCreatedAt( source.getCreatedAt() );
        expenseGuideDomain.setUpdatedAt( source.getUpdatedAt() );
        expenseGuideDomain.setDeletedAt( source.getDeletedAt() );
        expenseGuideDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        expenseGuideDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        expenseGuideDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        expenseGuideDomain.setIdentifierName( source.getIdentifierName() );
        expenseGuideDomain.setTenant( source.getTenant() );
        expenseGuideDomain.setName( source.getName() );
        expenseGuideDomain.setDescription( source.getDescription() );
        expenseGuideDomain.setIdentify( source.getIdentify() );

        expenseGuideDomain.setDateExpenseGuide(source.getDateExpenseGuide());
        expenseGuideDomain.setNroPax(source.getNroPax());
        expenseGuideDomain.setLodging(source.getLodging());
        expenseGuideDomain.setImportSubTotalUsd(source.getImportSubTotalUsd());
        expenseGuideDomain.setImportSubTotalSoles(source.getImportSubTotalSoles());
        expenseGuideDomain.setImportTotalUsd(source.getImportTotalUsd());
        expenseGuideDomain.setImportTotalSoles(source.getImportTotalSoles());
        expenseGuideDomain.setSubTotalPrice(source.getSubTotalPrice());
        expenseGuideDomain.setExpenseGuideState(source.getExpenseGuideState());

        expenseGuideDomain.setGuideTransfer(guideConverter.toDomain(source.getGuideTransfer()));
        expenseGuideDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));
        expenseGuideDomain.setSupplierLodging(supplierLodgingConverter.toDomain(source.getSupplierLodging()));
        expenseGuideDomain.setCurrency(currencyConverter.toDomain(source.getCurrency()));
        expenseGuideDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        expenseGuideDomain.setDateProcess(dateProcessConverter.toDomain(source.getDateProcess()));

        return expenseGuideDomain;
    }

    @Override
    public ExpenseGuideEntity toEntity(ExpenseGuideDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ExpenseGuideEntity expenseGuideEntity = new ExpenseGuideEntity();

        expenseGuideEntity.setId( source.getId() );
        expenseGuideEntity.setDeleted( source.getDeleted() );
        expenseGuideEntity.setEnabled( source.getEnabled() );
        expenseGuideEntity.setMigrated( source.getMigrated() );
        expenseGuideEntity.setCreatedAt( source.getCreatedAt() );
        expenseGuideEntity.setUpdatedAt( source.getUpdatedAt() );
        expenseGuideEntity.setDeletedAt( source.getDeletedAt() );
        expenseGuideEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        expenseGuideEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        expenseGuideEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        expenseGuideEntity.setIdentifierName( source.getIdentifierName() );
        expenseGuideEntity.setTenant( source.getTenant() );
        expenseGuideEntity.setName( source.getName() );
        expenseGuideEntity.setDescription( source.getDescription() );
        expenseGuideEntity.setIdentify( source.getIdentify() );

        expenseGuideEntity.setDateExpenseGuide(source.getDateExpenseGuide());
        expenseGuideEntity.setNroPax(source.getNroPax());
        expenseGuideEntity.setLodging(source.getLodging());
        expenseGuideEntity.setImportSubTotalUsd(source.getImportSubTotalUsd());
        expenseGuideEntity.setImportSubTotalSoles(source.getImportSubTotalSoles());
        expenseGuideEntity.setImportTotalUsd(source.getImportTotalUsd());
        expenseGuideEntity.setImportTotalSoles(source.getImportTotalSoles());
        expenseGuideEntity.setSubTotalPrice(source.getSubTotalPrice());
        expenseGuideEntity.setExpenseGuideState(source.getExpenseGuideState());

        expenseGuideEntity.setGuideTransfer(guideConverter.toEntity(source.getGuideTransfer()));
        expenseGuideEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));
        expenseGuideEntity.setSupplierLodging(supplierLodgingConverter.toEntity(source.getSupplierLodging()));
        expenseGuideEntity.setCurrency(currencyConverter.toEntity(source.getCurrency()));
        expenseGuideEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        expenseGuideEntity.setDateProcess(dateProcessConverter.toEntity(source.getDateProcess()));

        return expenseGuideEntity;
    }

    public IdBaseDomain toDomainIdBase(ExpenseGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ExpenseGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ExpenseGuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ExpenseGuideDomain> toDomainAllSimpleToDomainCaseListExpenseGuide(List<ExpenseGuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ExpenseGuideDomain expenseGuideDomain = new ExpenseGuideDomain();
            expenseGuideDomain.setId(entity.getId());
            expenseGuideDomain.setIdentify(entity.getIdentify());
            expenseGuideDomain.setDateExpenseGuide(entity.getDateExpenseGuide());
            expenseGuideDomain.setNroPax(entity.getNroPax());
            expenseGuideDomain.setImportTotalUsd(entity.getImportTotalUsd());
            if (entity.getGuideTransfer() != null &&
                    entity.getGuideTransfer().getId() != null &&
                    !entity.getGuideTransfer().getId().isEmpty()){
                expenseGuideDomain.setGuideTransfer(new GuideDomain());
                expenseGuideDomain.getGuideTransfer().setId(entity.getGuideTransfer().getId());
                if (entity.getGuideTransfer().getPerson() != null &&
                        entity.getGuideTransfer().getPerson().getId() != null &&
                        !entity.getGuideTransfer().getPerson().getId().isEmpty()){
                    expenseGuideDomain.getGuideTransfer().setPerson(new PersonDomain());
                    expenseGuideDomain.getGuideTransfer().getPerson().setId(entity.getGuideTransfer().getPerson().getId());
                    expenseGuideDomain.getGuideTransfer().getPerson().setFirstName(entity.getGuideTransfer().getPerson().getFirstName());
                    expenseGuideDomain.getGuideTransfer().getPerson().setLastName(entity.getGuideTransfer().getPerson().getLastName());
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                expenseGuideDomain.setRegisterGroup(new RegisterGroupDomain());
                expenseGuideDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                expenseGuideDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                expenseGuideDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return expenseGuideDomain;
        }).toList();
    }

    public PageDTO<ExpenseGuideDomain> toDomainPageCaseListExpenseGuide(Page<ExpenseGuideEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ExpenseGuideDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListExpenseGuide(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
