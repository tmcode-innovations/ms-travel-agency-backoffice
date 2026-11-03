package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.GroupPqtTouristEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultGroupPqtTourist implements SetFieldDefaultEntity<GroupPqtTouristEntity, GroupPqtTouristDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public GroupPqtTouristEntity setDefaultFieldBaseCreate(GroupPqtTouristEntity entityParam) throws RuntimeException {
        GroupPqtTouristEntity entity = (GroupPqtTouristEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public GroupPqtTouristEntity setDefaultFieldBaseUpdate(GroupPqtTouristEntity entityParam) throws RuntimeException {
        GroupPqtTouristEntity entity = (GroupPqtTouristEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public GroupPqtTouristEntity setDefaultFieldBaseDelete(GroupPqtTouristEntity entityParam) throws RuntimeException {
        GroupPqtTouristEntity entity = (GroupPqtTouristEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public GroupPqtTouristDomain setDefaultFieldBaseUpdated(GroupPqtTouristDomain domainTarget, GroupPqtTouristDomain domainSource) throws RuntimeException {
        GroupPqtTouristDomain domain = (GroupPqtTouristDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
