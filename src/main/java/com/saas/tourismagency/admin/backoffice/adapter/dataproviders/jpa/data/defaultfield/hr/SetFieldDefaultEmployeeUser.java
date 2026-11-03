package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeUserEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultEmployeeUser implements SetFieldDefaultEntity<EmployeeUserEntity, EmployeeUserDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public EmployeeUserEntity setDefaultFieldBaseCreate(EmployeeUserEntity employeeUserEntity) throws RuntimeException {
        EmployeeUserEntity entity = (EmployeeUserEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(employeeUserEntity);
        return entity;
    }

    @Override
    public EmployeeUserEntity setDefaultFieldBaseUpdate(EmployeeUserEntity employeeUserEntity) throws RuntimeException {
        EmployeeUserEntity entity = (EmployeeUserEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(employeeUserEntity);
        return entity;
    }

    @Override
    public EmployeeUserEntity setDefaultFieldBaseDelete(EmployeeUserEntity employeeUserEntity) throws RuntimeException {
        EmployeeUserEntity entity = (EmployeeUserEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(employeeUserEntity);
        return entity;
    }

    @Override
    public EmployeeUserDomain setDefaultFieldBaseUpdated(EmployeeUserDomain domainTarget, EmployeeUserDomain domainSource) throws RuntimeException {
        EmployeeUserDomain domain = (EmployeeUserDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
