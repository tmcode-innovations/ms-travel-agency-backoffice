package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultCreditNote implements SetFieldDefaultEntity<CreditNoteEntity, CreditNoteDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public CreditNoteEntity setDefaultFieldBaseCreate(CreditNoteEntity entityParam) throws RuntimeException {
        CreditNoteEntity entity = (CreditNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public CreditNoteEntity setDefaultFieldBaseUpdate(CreditNoteEntity entityParam) throws RuntimeException {
        CreditNoteEntity entity = (CreditNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public CreditNoteEntity setDefaultFieldBaseDelete(CreditNoteEntity entityParam) throws RuntimeException {
        CreditNoteEntity entity = (CreditNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public CreditNoteDomain setDefaultFieldBaseUpdated(CreditNoteDomain domainTarget, CreditNoteDomain domainSource) throws RuntimeException {
        CreditNoteDomain domain = (CreditNoteDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
