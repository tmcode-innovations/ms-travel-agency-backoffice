package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProforma implements SetFieldDefaultEntity<ProformaEntity, ProformaDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProformaEntity setDefaultFieldBaseCreate(ProformaEntity entityParam) throws RuntimeException {
        ProformaEntity entity = (ProformaEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ProformaEntity setDefaultFieldBaseUpdate(ProformaEntity entityParam) throws RuntimeException {
        ProformaEntity entity = (ProformaEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ProformaEntity setDefaultFieldBaseDelete(ProformaEntity entityParam) throws RuntimeException {
        ProformaEntity entity = (ProformaEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ProformaDomain setDefaultFieldBaseUpdated(ProformaDomain domainTarget, ProformaDomain domainSource) throws RuntimeException {
        ProformaDomain domain = (ProformaDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
