package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NotificationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultNotification implements SetFieldDefaultEntity<NotificationEntity, NotificationDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public NotificationEntity setDefaultFieldBaseCreate(NotificationEntity notificationEntity) throws RuntimeException {
        NotificationEntity entity = (NotificationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(notificationEntity);
        return entity;
    }

    @Override
    public NotificationEntity setDefaultFieldBaseUpdate(NotificationEntity notificationEntity) throws RuntimeException {
        NotificationEntity entity = (NotificationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(notificationEntity);
        return entity;
    }

    @Override
    public NotificationEntity setDefaultFieldBaseDelete(NotificationEntity notificationEntity) throws RuntimeException {
        NotificationEntity entity = (NotificationEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(notificationEntity);
        return entity;
    }

    @Override
    public NotificationDomain setDefaultFieldBaseUpdated(NotificationDomain domainTarget, NotificationDomain domainSource) throws RuntimeException {
        NotificationDomain domain = (NotificationDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
