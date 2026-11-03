package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProformaDetail implements SetFieldDefaultEntity<ProformaDetailEntity, ProformaDetailDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProformaDetailEntity setDefaultFieldBaseCreate(ProformaDetailEntity entityParam) throws RuntimeException {
        ProformaDetailEntity entity = (ProformaDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public ProformaDetailEntity setDefaultFieldBaseUpdate(ProformaDetailEntity entityParam) throws RuntimeException {
        ProformaDetailEntity entity = (ProformaDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public ProformaDetailEntity setDefaultFieldBaseDelete(ProformaDetailEntity entityParam) throws RuntimeException {
        ProformaDetailEntity entity = (ProformaDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public ProformaDetailDomain setDefaultFieldBaseUpdated(ProformaDetailDomain domainTarget, ProformaDetailDomain domainSource) throws RuntimeException {
        ProformaDetailDomain domain = (ProformaDetailDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
