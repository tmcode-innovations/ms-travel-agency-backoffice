package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultOrderService implements SetFieldDefaultEntity<OrderServiceEntity, OrderServiceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public OrderServiceEntity setDefaultFieldBaseCreate(OrderServiceEntity entityParam) throws RuntimeException {
        OrderServiceEntity entity = (OrderServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public OrderServiceEntity setDefaultFieldBaseUpdate(OrderServiceEntity entityParam) throws RuntimeException {
        OrderServiceEntity entity = (OrderServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public OrderServiceEntity setDefaultFieldBaseDelete(OrderServiceEntity entityParam) throws RuntimeException {
        OrderServiceEntity entity = (OrderServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public OrderServiceDomain setDefaultFieldBaseUpdated(OrderServiceDomain domainTarget, OrderServiceDomain domainSource) throws RuntimeException {
        OrderServiceDomain domain = (OrderServiceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
