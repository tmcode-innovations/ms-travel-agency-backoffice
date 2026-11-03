package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteCommissionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCreditNoteCommission implements SetFieldDefaultEntity<CreditNoteCommissionEntity, CreditNoteCommissionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CreditNoteCommissionEntity setDefaultFieldBaseCreate(CreditNoteCommissionEntity entityParam) throws RuntimeException {
        CreditNoteCommissionEntity entity = (CreditNoteCommissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public CreditNoteCommissionEntity setDefaultFieldBaseUpdate(CreditNoteCommissionEntity entityParam) throws RuntimeException {
        CreditNoteCommissionEntity entity = (CreditNoteCommissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public CreditNoteCommissionEntity setDefaultFieldBaseDelete(CreditNoteCommissionEntity entityParam) throws RuntimeException {
        CreditNoteCommissionEntity entity = (CreditNoteCommissionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public CreditNoteCommissionDomain setDefaultFieldBaseUpdated(CreditNoteCommissionDomain domainTarget, CreditNoteCommissionDomain domainSource) throws RuntimeException {
        CreditNoteCommissionDomain domain = (CreditNoteCommissionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
