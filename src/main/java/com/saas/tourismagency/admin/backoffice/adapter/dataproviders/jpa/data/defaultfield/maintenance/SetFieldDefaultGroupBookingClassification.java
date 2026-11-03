package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GroupBookingClassificationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GroupBookingClassificationDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultGroupBookingClassification implements SetFieldDefaultEntity<GroupBookingClassificationEntity, GroupBookingClassificationDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public GroupBookingClassificationEntity setDefaultFieldBaseCreate(GroupBookingClassificationEntity bankEntity) throws RuntimeException {
        GroupBookingClassificationEntity entity = (GroupBookingClassificationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(bankEntity);
        return entity;
    }

    @Override
    public GroupBookingClassificationEntity setDefaultFieldBaseUpdate(GroupBookingClassificationEntity bankEntity) throws RuntimeException {
        GroupBookingClassificationEntity entity = (GroupBookingClassificationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(bankEntity);
        return entity;
    }

    @Override
    public GroupBookingClassificationEntity setDefaultFieldBaseDelete(GroupBookingClassificationEntity bankEntity) throws RuntimeException {
        GroupBookingClassificationEntity entity = (GroupBookingClassificationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(bankEntity);
        return entity;
    }

    @Override
    public GroupBookingClassificationDomain setDefaultFieldBaseUpdated(GroupBookingClassificationDomain domainTarget, GroupBookingClassificationDomain domainSource) throws RuntimeException {
        GroupBookingClassificationDomain domain = (GroupBookingClassificationDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
