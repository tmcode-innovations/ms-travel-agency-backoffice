package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultEmployee implements SetFieldDefaultEntity<EmployeeEntity, EmployeeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public EmployeeEntity setDefaultFieldBaseCreate(EmployeeEntity countryEntity) throws RuntimeException {
        EmployeeEntity entity = (EmployeeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(countryEntity);
        return entity;
    }

    @Override
    public EmployeeEntity setDefaultFieldBaseUpdate(EmployeeEntity countryEntity) throws RuntimeException {
        EmployeeEntity entity = (EmployeeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(countryEntity);
        return entity;
    }

    @Override
    public EmployeeEntity setDefaultFieldBaseDelete(EmployeeEntity countryEntity) throws RuntimeException {
        EmployeeEntity entity = (EmployeeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(countryEntity);
        return entity;
    }

    @Override
    public EmployeeDomain setDefaultFieldBaseUpdated(EmployeeDomain domainTarget, EmployeeDomain domainSource) throws RuntimeException {
        EmployeeDomain domain = (EmployeeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
