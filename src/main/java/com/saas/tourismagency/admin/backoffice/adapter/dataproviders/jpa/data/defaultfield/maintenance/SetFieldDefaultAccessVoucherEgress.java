package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.AccessVoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.AccessVoucherEgressDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultAccessVoucherEgress implements SetFieldDefaultEntity<AccessVoucherEgressEntity, AccessVoucherEgressDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public AccessVoucherEgressEntity setDefaultFieldBaseCreate(AccessVoucherEgressEntity accessVoucherEgressEntity) throws RuntimeException {
        AccessVoucherEgressEntity entity = (AccessVoucherEgressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(accessVoucherEgressEntity);
        return entity;
    }

    @Override
    public AccessVoucherEgressEntity setDefaultFieldBaseUpdate(AccessVoucherEgressEntity accessVoucherEgressEntity) throws RuntimeException {
        AccessVoucherEgressEntity entity = (AccessVoucherEgressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(accessVoucherEgressEntity);
        return entity;
    }

    @Override
    public AccessVoucherEgressEntity setDefaultFieldBaseDelete(AccessVoucherEgressEntity accessVoucherEgressEntity) throws RuntimeException {
        AccessVoucherEgressEntity entity = (AccessVoucherEgressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(accessVoucherEgressEntity);
        return entity;
    }

    @Override
    public AccessVoucherEgressDomain setDefaultFieldBaseUpdated(AccessVoucherEgressDomain domainTarget, AccessVoucherEgressDomain domainSource) throws RuntimeException {
        AccessVoucherEgressDomain domain = (AccessVoucherEgressDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
