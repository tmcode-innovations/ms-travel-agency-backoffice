package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultOrderServiceItem implements SetFieldDefaultEntity<OrderServiceItemEntity, OrderServiceItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public OrderServiceItemEntity setDefaultFieldBaseCreate(OrderServiceItemEntity entityParam) throws RuntimeException {
        OrderServiceItemEntity entity = (OrderServiceItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public OrderServiceItemEntity setDefaultFieldBaseUpdate(OrderServiceItemEntity entityParam) throws RuntimeException {
        OrderServiceItemEntity entity = (OrderServiceItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public OrderServiceItemEntity setDefaultFieldBaseDelete(OrderServiceItemEntity entityParam) throws RuntimeException {
        OrderServiceItemEntity entity = (OrderServiceItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public OrderServiceItemDomain setDefaultFieldBaseUpdated(OrderServiceItemDomain domainTarget, OrderServiceItemDomain domainSource) throws RuntimeException {
        OrderServiceItemDomain domain = (OrderServiceItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
