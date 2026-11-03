package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterGroup implements SetFieldDefaultEntity<RegisterGroupEntity, RegisterGroupDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterGroupEntity setDefaultFieldBaseCreate(RegisterGroupEntity entityParam) throws RuntimeException {
        RegisterGroupEntity entity = (RegisterGroupEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterGroupEntity setDefaultFieldBaseUpdate(RegisterGroupEntity entityParam) throws RuntimeException {
        RegisterGroupEntity entity = (RegisterGroupEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterGroupEntity setDefaultFieldBaseDelete(RegisterGroupEntity entityParam) throws RuntimeException {
        RegisterGroupEntity entity = (RegisterGroupEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterGroupDomain setDefaultFieldBaseUpdated(RegisterGroupDomain domainTarget, RegisterGroupDomain domainSource) throws RuntimeException {
        RegisterGroupDomain domain = (RegisterGroupDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
