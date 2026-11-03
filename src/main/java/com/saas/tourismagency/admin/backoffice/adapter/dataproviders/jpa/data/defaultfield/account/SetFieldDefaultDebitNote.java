package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DebitNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.DebitNoteDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDebitNote implements SetFieldDefaultEntity<DebitNoteEntity, DebitNoteDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DebitNoteEntity setDefaultFieldBaseCreate(DebitNoteEntity entityParam) throws RuntimeException {
        DebitNoteEntity entity = (DebitNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public DebitNoteEntity setDefaultFieldBaseUpdate(DebitNoteEntity entityParam) throws RuntimeException {
        DebitNoteEntity entity = (DebitNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public DebitNoteEntity setDefaultFieldBaseDelete(DebitNoteEntity entityParam) throws RuntimeException {
        DebitNoteEntity entity = (DebitNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public DebitNoteDomain setDefaultFieldBaseUpdated(DebitNoteDomain domainTarget, DebitNoteDomain domainSource) throws RuntimeException {
        DebitNoteDomain domain = (DebitNoteDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
