package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultVoucherIngressDetail implements SetFieldDefaultEntity<VoucherIngressDetailEntity, VoucherIngressDetailDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public VoucherIngressDetailEntity setDefaultFieldBaseCreate(VoucherIngressDetailEntity entityParam) throws RuntimeException {
        VoucherIngressDetailEntity entity = (VoucherIngressDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public VoucherIngressDetailEntity setDefaultFieldBaseUpdate(VoucherIngressDetailEntity entityParam) throws RuntimeException {
        VoucherIngressDetailEntity entity = (VoucherIngressDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public VoucherIngressDetailEntity setDefaultFieldBaseDelete(VoucherIngressDetailEntity entityParam) throws RuntimeException {
        VoucherIngressDetailEntity entity = (VoucherIngressDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public VoucherIngressDetailDomain setDefaultFieldBaseUpdated(VoucherIngressDetailDomain domainTarget, VoucherIngressDetailDomain domainSource) throws RuntimeException {
        VoucherIngressDetailDomain domain = (VoucherIngressDetailDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
