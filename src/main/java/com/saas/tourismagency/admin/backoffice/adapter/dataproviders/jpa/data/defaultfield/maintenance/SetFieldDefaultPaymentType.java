package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PaymentTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PaymentTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultPaymentType implements SetFieldDefaultEntity<PaymentTypeEntity, PaymentTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public PaymentTypeEntity setDefaultFieldBaseCreate(PaymentTypeEntity paymentTypeEntity) throws RuntimeException {
        PaymentTypeEntity entity = (PaymentTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(paymentTypeEntity);
        return entity;
    }

    @Override
    public PaymentTypeEntity setDefaultFieldBaseUpdate(PaymentTypeEntity paymentTypeEntity) throws RuntimeException {
        PaymentTypeEntity entity = (PaymentTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(paymentTypeEntity);
        return entity;
    }

    @Override
    public PaymentTypeEntity setDefaultFieldBaseDelete(PaymentTypeEntity paymentTypeEntity) throws RuntimeException {
        PaymentTypeEntity entity = (PaymentTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(paymentTypeEntity);
        return entity;
    }

    @Override
    public PaymentTypeDomain setDefaultFieldBaseUpdated(PaymentTypeDomain domainTarget, PaymentTypeDomain domainSource) throws RuntimeException {
        PaymentTypeDomain domain = (PaymentTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
