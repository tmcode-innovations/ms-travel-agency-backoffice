package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailItemDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultVoucherEgressDetailItem implements SetFieldDefaultEntity<VoucherEgressDetailItemEntity, VoucherEgressDetailItemDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public VoucherEgressDetailItemEntity setDefaultFieldBaseCreate(VoucherEgressDetailItemEntity entityParam) throws RuntimeException {
        VoucherEgressDetailItemEntity entity = (VoucherEgressDetailItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDetailItemEntity setDefaultFieldBaseUpdate(VoucherEgressDetailItemEntity entityParam) throws RuntimeException {
        VoucherEgressDetailItemEntity entity = (VoucherEgressDetailItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDetailItemEntity setDefaultFieldBaseDelete(VoucherEgressDetailItemEntity entityParam) throws RuntimeException {
        VoucherEgressDetailItemEntity entity = (VoucherEgressDetailItemEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public VoucherEgressDetailItemDomain setDefaultFieldBaseUpdated(VoucherEgressDetailItemDomain domainTarget, VoucherEgressDetailItemDomain domainSource) throws RuntimeException {
        VoucherEgressDetailItemDomain domain = (VoucherEgressDetailItemDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
