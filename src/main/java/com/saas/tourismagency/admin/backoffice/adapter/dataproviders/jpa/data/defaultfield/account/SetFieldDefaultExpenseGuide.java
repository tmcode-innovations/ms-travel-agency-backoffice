package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ExpenseGuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultExpenseGuide implements SetFieldDefaultEntity<ExpenseGuideEntity, ExpenseGuideDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ExpenseGuideEntity setDefaultFieldBaseCreate(ExpenseGuideEntity entityParam) throws RuntimeException {
        ExpenseGuideEntity entity = (ExpenseGuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ExpenseGuideEntity setDefaultFieldBaseUpdate(ExpenseGuideEntity entityParam) throws RuntimeException {
        ExpenseGuideEntity entity = (ExpenseGuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ExpenseGuideEntity setDefaultFieldBaseDelete(ExpenseGuideEntity entityParam) throws RuntimeException {
        ExpenseGuideEntity entity = (ExpenseGuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ExpenseGuideDomain setDefaultFieldBaseUpdated(ExpenseGuideDomain domainTarget, ExpenseGuideDomain domainSource) throws RuntimeException {
        ExpenseGuideDomain domain = (ExpenseGuideDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
