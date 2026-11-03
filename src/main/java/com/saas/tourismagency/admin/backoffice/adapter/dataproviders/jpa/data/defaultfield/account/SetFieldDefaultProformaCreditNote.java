package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaCreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProformaCreditNote implements SetFieldDefaultEntity<ProformaCreditNoteEntity, ProformaCreditNoteDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProformaCreditNoteEntity setDefaultFieldBaseCreate(ProformaCreditNoteEntity entityParam) throws RuntimeException {
        ProformaCreditNoteEntity entity = (ProformaCreditNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ProformaCreditNoteEntity setDefaultFieldBaseUpdate(ProformaCreditNoteEntity entityParam) throws RuntimeException {
        ProformaCreditNoteEntity entity = (ProformaCreditNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ProformaCreditNoteEntity setDefaultFieldBaseDelete(ProformaCreditNoteEntity entityParam) throws RuntimeException {
        ProformaCreditNoteEntity entity = (ProformaCreditNoteEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ProformaCreditNoteDomain setDefaultFieldBaseUpdated(ProformaCreditNoteDomain domainTarget, ProformaCreditNoteDomain domainSource) throws RuntimeException {
        ProformaCreditNoteDomain domain = (ProformaCreditNoteDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
