package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultGuide implements SetFieldDefaultEntity<GuideEntity, GuideDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public GuideEntity setDefaultFieldBaseCreate(GuideEntity contactEntity) throws RuntimeException {
        GuideEntity entity = (GuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(contactEntity);
        return entity;
    }

    @Override
    public GuideEntity setDefaultFieldBaseUpdate(GuideEntity contactEntity) throws RuntimeException {
        GuideEntity entity = (GuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(contactEntity);
        return entity;
    }

    @Override
    public GuideEntity setDefaultFieldBaseDelete(GuideEntity contactEntity) throws RuntimeException {
        GuideEntity entity = (GuideEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(contactEntity);
        return entity;
    }

    @Override
    public GuideDomain setDefaultFieldBaseUpdated(GuideDomain domainTarget, GuideDomain domainSource) throws RuntimeException {
        GuideDomain domain = (GuideDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
