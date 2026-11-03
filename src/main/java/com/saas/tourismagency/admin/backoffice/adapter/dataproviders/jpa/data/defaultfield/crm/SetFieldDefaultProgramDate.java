package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProgramDate implements SetFieldDefaultEntity<ProgramDateEntity, ProgramDateDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProgramDateEntity setDefaultFieldBaseCreate(ProgramDateEntity programDateEntity) throws RuntimeException {
        ProgramDateEntity entity = (ProgramDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(programDateEntity);
        return entity;
    }

    @Override
    public ProgramDateEntity setDefaultFieldBaseUpdate(ProgramDateEntity programDateEntity) throws RuntimeException {
        ProgramDateEntity entity = (ProgramDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(programDateEntity);
        return entity;
    }

    @Override
    public ProgramDateEntity setDefaultFieldBaseDelete(ProgramDateEntity programDateEntity) throws RuntimeException {
        ProgramDateEntity entity = (ProgramDateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(programDateEntity);
        return entity;
    }

    @Override
    public ProgramDateDomain setDefaultFieldBaseUpdated(ProgramDateDomain domainTarget, ProgramDateDomain domainSource) throws RuntimeException {
        ProgramDateDomain domain = (ProgramDateDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
