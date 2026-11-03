package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.paymenttype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.paymenttype.PaymentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PaymentTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.paymenttype.IPaymentTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PaymentTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentTypeRepositoryTemplateImpl implements IPaymentTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PaymentTypeConverter converter;

    @Override
    public PageDTO<PaymentTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PaymentTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PaymentTypeEntity> results = mongoTemplate.find(query, PaymentTypeEntity.class);
        Page<PaymentTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<PaymentTypeDomain> query(Query query) {
        List<PaymentTypeEntity> items = mongoTemplate.find(query, PaymentTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        PaymentTypeEntity entity = mongoTemplate.findOne(query, PaymentTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<PaymentTypeEntity> items = mongoTemplate.find(query, PaymentTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<PaymentTypeDomain> queryAllSimpleDomainCaseListPaymentType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PaymentTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PaymentTypeEntity> results = mongoTemplate.find(query, PaymentTypeEntity.class);
        Page<PaymentTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListPaymentType(resultsWithPage);
    }
}
