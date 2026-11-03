package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguidedetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguide.ExpenseGuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderserviceitem.OrderServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ExpenseGuideDetailEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseGuideDetailConverter implements Converter<ExpenseGuideDetailDomain, ExpenseGuideDetailEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ExpenseGuideConverter expenseGuideConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private OrderServiceItemConverter orderServiceItemConverter;

    @Autowired
    private GuideConverter guideConverter;

    @Override
    public ExpenseGuideDetailDomain toDomain(ExpenseGuideDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ExpenseGuideDetailDomain expenseGuideDetailDomain = new ExpenseGuideDetailDomain();

        expenseGuideDetailDomain.setId( source.getId() );
        expenseGuideDetailDomain.setDeleted( source.getDeleted() );
        expenseGuideDetailDomain.setEnabled( source.getEnabled() );
        expenseGuideDetailDomain.setMigrated( source.getMigrated() );
        expenseGuideDetailDomain.setCreatedAt( source.getCreatedAt() );
        expenseGuideDetailDomain.setUpdatedAt( source.getUpdatedAt() );
        expenseGuideDetailDomain.setDeletedAt( source.getDeletedAt() );
        expenseGuideDetailDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        expenseGuideDetailDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        expenseGuideDetailDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        expenseGuideDetailDomain.setIdentifierName( source.getIdentifierName() );
        expenseGuideDetailDomain.setTenant( source.getTenant() );
        expenseGuideDetailDomain.setName( source.getName() );
        expenseGuideDetailDomain.setDescription( source.getDescription() );
        expenseGuideDetailDomain.setIdentify( source.getIdentify() );

        expenseGuideDetailDomain.setPriceUnit(source.getPriceUnit());
        expenseGuideDetailDomain.setImportUsd(source.getImportUsd());
        expenseGuideDetailDomain.setImportSoles(source.getImportSoles());
        expenseGuideDetailDomain.setCant(source.getCant());
        expenseGuideDetailDomain.setDateTimeService(source.getDateTimeService());

        expenseGuideDetailDomain.setExpenseGuide(expenseGuideConverter.toDomain(source.getExpenseGuide()));
        expenseGuideDetailDomain.setService(serviceConverter.toDomain(source.getService()));
        expenseGuideDetailDomain.setOrderServiceItem(orderServiceItemConverter.toDomain(source.getOrderServiceItem()));
        expenseGuideDetailDomain.setGuideDrive(guideConverter.toDomain(source.getGuideDrive()));

        return expenseGuideDetailDomain;
    }

    @Override
    public ExpenseGuideDetailEntity toEntity(ExpenseGuideDetailDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ExpenseGuideDetailEntity expenseGuideDetailEntity = new ExpenseGuideDetailEntity();

        expenseGuideDetailEntity.setId( source.getId() );
        expenseGuideDetailEntity.setDeleted( source.getDeleted() );
        expenseGuideDetailEntity.setEnabled( source.getEnabled() );
        expenseGuideDetailEntity.setMigrated( source.getMigrated() );
        expenseGuideDetailEntity.setCreatedAt( source.getCreatedAt() );
        expenseGuideDetailEntity.setUpdatedAt( source.getUpdatedAt() );
        expenseGuideDetailEntity.setDeletedAt( source.getDeletedAt() );
        expenseGuideDetailEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        expenseGuideDetailEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        expenseGuideDetailEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        expenseGuideDetailEntity.setIdentifierName( source.getIdentifierName() );
        expenseGuideDetailEntity.setTenant( source.getTenant() );
        expenseGuideDetailEntity.setName( source.getName() );
        expenseGuideDetailEntity.setDescription( source.getDescription() );
        expenseGuideDetailEntity.setIdentify( source.getIdentify() );

        expenseGuideDetailEntity.setPriceUnit(source.getPriceUnit());
        expenseGuideDetailEntity.setImportUsd(source.getImportUsd());
        expenseGuideDetailEntity.setImportSoles(source.getImportSoles());
        expenseGuideDetailEntity.setCant(source.getCant());
        expenseGuideDetailEntity.setDateTimeService(source.getDateTimeService());

        expenseGuideDetailEntity.setExpenseGuide(expenseGuideConverter.toEntity(source.getExpenseGuide()));
        expenseGuideDetailEntity.setService(serviceConverter.toEntity(source.getService()));
        expenseGuideDetailEntity.setOrderServiceItem(orderServiceItemConverter.toEntity(source.getOrderServiceItem()));
        expenseGuideDetailEntity.setGuideDrive(guideConverter.toEntity(source.getGuideDrive()));

        return expenseGuideDetailEntity;
    }

    public IdBaseDomain toDomainIdBase(ExpenseGuideDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ExpenseGuideDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ExpenseGuideDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

}
