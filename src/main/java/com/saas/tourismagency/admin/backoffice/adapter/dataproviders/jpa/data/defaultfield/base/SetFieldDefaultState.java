package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.StateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.StateDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultState implements SetFieldDefaultEntity<StateEntity, StateDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public StateEntity setDefaultFieldBaseCreate(StateEntity stateEntity) throws RuntimeException {
        StateEntity entity = (StateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(stateEntity);
        return entity;
    }

    @Override
    public StateEntity setDefaultFieldBaseUpdate(StateEntity stateEntity) throws RuntimeException {
        StateEntity entity = (StateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(stateEntity);
        return entity;
    }

    @Override
    public StateEntity setDefaultFieldBaseDelete(StateEntity stateEntity) throws RuntimeException {
        StateEntity entity = (StateEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(stateEntity);
        return entity;
    }

    @Override
    public StateDomain setDefaultFieldBaseUpdated(StateDomain domainTarget, StateDomain domainSource) throws RuntimeException {
        StateDomain domain = (StateDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
