package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramCostTourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramCostTourDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProgramCostTour implements SetFieldDefaultEntity<ProgramCostTourEntity, ProgramCostTourDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProgramCostTourEntity setDefaultFieldBaseCreate(ProgramCostTourEntity programCostTourEntity) throws RuntimeException {
        ProgramCostTourEntity entity = (ProgramCostTourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(programCostTourEntity);
        return entity;
    }

    @Override
    public ProgramCostTourEntity setDefaultFieldBaseUpdate(ProgramCostTourEntity programCostTourEntity) throws RuntimeException {
        ProgramCostTourEntity entity = (ProgramCostTourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(programCostTourEntity);
        return entity;
    }

    @Override
    public ProgramCostTourEntity setDefaultFieldBaseDelete(ProgramCostTourEntity programCostTourEntity) throws RuntimeException {
        ProgramCostTourEntity entity = (ProgramCostTourEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(programCostTourEntity);
        return entity;
    }

    @Override
    public ProgramCostTourDomain setDefaultFieldBaseUpdated(ProgramCostTourDomain domainTarget, ProgramCostTourDomain domainSource) throws RuntimeException {
        ProgramCostTourDomain domain = (ProgramCostTourDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
