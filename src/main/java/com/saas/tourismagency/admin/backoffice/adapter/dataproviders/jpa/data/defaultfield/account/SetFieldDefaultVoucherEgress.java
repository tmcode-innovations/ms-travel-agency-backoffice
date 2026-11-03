package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultVoucherEgress implements SetFieldDefaultEntity<VoucherEgressEntity, VoucherEgressDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public VoucherEgressEntity setDefaultFieldBaseCreate(VoucherEgressEntity entityParam) throws RuntimeException {
        VoucherEgressEntity entity = (VoucherEgressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressEntity setDefaultFieldBaseUpdate(VoucherEgressEntity entityParam) throws RuntimeException {
        VoucherEgressEntity entity = (VoucherEgressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressEntity setDefaultFieldBaseDelete(VoucherEgressEntity entityParam) throws RuntimeException {
        VoucherEgressEntity entity = (VoucherEgressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDomain setDefaultFieldBaseUpdated(VoucherEgressDomain domainTarget, VoucherEgressDomain domainSource) throws RuntimeException {
        VoucherEgressDomain domain = (VoucherEgressDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
