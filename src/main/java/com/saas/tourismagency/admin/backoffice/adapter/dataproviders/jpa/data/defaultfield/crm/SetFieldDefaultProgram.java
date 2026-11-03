package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProgram implements SetFieldDefaultEntity<ProgramEntity, ProgramDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProgramEntity setDefaultFieldBaseCreate(ProgramEntity programEntity) throws RuntimeException {
        ProgramEntity entity = (ProgramEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(programEntity);
        return entity;
    }

    @Override
    public ProgramEntity setDefaultFieldBaseUpdate(ProgramEntity programEntity) throws RuntimeException {
        ProgramEntity entity = (ProgramEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(programEntity);
        return entity;
    }

    @Override
    public ProgramEntity setDefaultFieldBaseDelete(ProgramEntity programEntity) throws RuntimeException {
        ProgramEntity entity = (ProgramEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(programEntity);
        return entity;
    }

    @Override
    public ProgramDomain setDefaultFieldBaseUpdated(ProgramDomain domainTarget, ProgramDomain domainSource) throws RuntimeException {
        ProgramDomain domain = (ProgramDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
