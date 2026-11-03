package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDepartmentRegion implements SetFieldDefaultEntity<DepartmentRegionEntity, DepartmentRegionDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DepartmentRegionEntity setDefaultFieldBaseCreate(DepartmentRegionEntity departmentRegionEntity) throws RuntimeException {
        DepartmentRegionEntity entity = (DepartmentRegionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(departmentRegionEntity);
        return entity;
    }

    @Override
    public DepartmentRegionEntity setDefaultFieldBaseUpdate(DepartmentRegionEntity departmentRegionEntity) throws RuntimeException {
        DepartmentRegionEntity entity = (DepartmentRegionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(departmentRegionEntity);
        return entity;
    }

    @Override
    public DepartmentRegionEntity setDefaultFieldBaseDelete(DepartmentRegionEntity departmentRegionEntity) throws RuntimeException {
        DepartmentRegionEntity entity = (DepartmentRegionEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(departmentRegionEntity);
        return entity;
    }

    @Override
    public DepartmentRegionDomain setDefaultFieldBaseUpdated(DepartmentRegionDomain domainTarget, DepartmentRegionDomain domainSource) throws RuntimeException {
        DepartmentRegionDomain domain = (DepartmentRegionDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
