package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.RequestOrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRequestOrderService implements SetFieldDefaultEntity<RequestOrderServiceEntity, RequestOrderServiceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RequestOrderServiceEntity setDefaultFieldBaseCreate(RequestOrderServiceEntity entityParam) throws RuntimeException {
        RequestOrderServiceEntity entity = (RequestOrderServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RequestOrderServiceEntity setDefaultFieldBaseUpdate(RequestOrderServiceEntity entityParam) throws RuntimeException {
        RequestOrderServiceEntity entity = (RequestOrderServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RequestOrderServiceEntity setDefaultFieldBaseDelete(RequestOrderServiceEntity entityParam) throws RuntimeException {
        RequestOrderServiceEntity entity = (RequestOrderServiceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RequestOrderServiceDomain setDefaultFieldBaseUpdated(RequestOrderServiceDomain domainTarget, RequestOrderServiceDomain domainSource) throws RuntimeException {
        RequestOrderServiceDomain domain = (RequestOrderServiceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
