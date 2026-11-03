package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultVoucherIngress implements SetFieldDefaultEntity<VoucherIngressEntity, VoucherIngressDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public VoucherIngressEntity setDefaultFieldBaseCreate(VoucherIngressEntity entityParam) throws RuntimeException {
        VoucherIngressEntity entity = (VoucherIngressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public VoucherIngressEntity setDefaultFieldBaseUpdate(VoucherIngressEntity entityParam) throws RuntimeException {
        VoucherIngressEntity entity = (VoucherIngressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public VoucherIngressEntity setDefaultFieldBaseDelete(VoucherIngressEntity entityParam) throws RuntimeException {
        VoucherIngressEntity entity = (VoucherIngressEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public VoucherIngressDomain setDefaultFieldBaseUpdated(VoucherIngressDomain domainTarget, VoucherIngressDomain domainSource) throws RuntimeException {
        VoucherIngressDomain domain = (VoucherIngressDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
