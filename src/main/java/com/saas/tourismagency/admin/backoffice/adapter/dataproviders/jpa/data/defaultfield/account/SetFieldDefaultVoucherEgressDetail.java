package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultVoucherEgressDetail implements SetFieldDefaultEntity<VoucherEgressDetailEntity, VoucherEgressDetailDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public VoucherEgressDetailEntity setDefaultFieldBaseCreate(VoucherEgressDetailEntity entityParam) throws RuntimeException {
        VoucherEgressDetailEntity entity = (VoucherEgressDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDetailEntity setDefaultFieldBaseUpdate(VoucherEgressDetailEntity entityParam) throws RuntimeException {
        VoucherEgressDetailEntity entity = (VoucherEgressDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDetailEntity setDefaultFieldBaseDelete(VoucherEgressDetailEntity entityParam) throws RuntimeException {
        VoucherEgressDetailEntity entity = (VoucherEgressDetailEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDetailDomain setDefaultFieldBaseUpdated(VoucherEgressDetailDomain domainTarget, VoucherEgressDetailDomain domainSource) throws RuntimeException {
        VoucherEgressDetailDomain domain = (VoucherEgressDetailDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
