package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ExpenseGuideDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultExpenseGuideDetail implements SetFieldDefaultEntity<ExpenseGuideDetailEntity, ExpenseGuideDetailDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ExpenseGuideDetailEntity setDefaultFieldBaseCreate(ExpenseGuideDetailEntity entityParam) throws RuntimeException {
        ExpenseGuideDetailEntity entity = (ExpenseGuideDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ExpenseGuideDetailEntity setDefaultFieldBaseUpdate(ExpenseGuideDetailEntity entityParam) throws RuntimeException {
        ExpenseGuideDetailEntity entity = (ExpenseGuideDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ExpenseGuideDetailEntity setDefaultFieldBaseDelete(ExpenseGuideDetailEntity entityParam) throws RuntimeException {
        ExpenseGuideDetailEntity entity = (ExpenseGuideDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ExpenseGuideDetailDomain setDefaultFieldBaseUpdated(ExpenseGuideDetailDomain domainTarget, ExpenseGuideDetailDomain domainSource) throws RuntimeException {
        ExpenseGuideDetailDomain domain = (ExpenseGuideDetailDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
