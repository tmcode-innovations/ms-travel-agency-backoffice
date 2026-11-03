package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.invoicecommission.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.invoicecommission.InvoiceCommissionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.invoicecommission.IInvoiceCommissionRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCommissionRepoTempImpl implements IInvoiceCommissionRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private InvoiceCommissionConverter converter;

    @Override
    public PageDTO<InvoiceCommissionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, InvoiceCommissionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<InvoiceCommissionEntity> results = mongoTemplate.find(query, InvoiceCommissionEntity.class);
        Page<InvoiceCommissionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<InvoiceCommissionDomain> query(Query query) {
        List<InvoiceCommissionEntity> files = mongoTemplate.find(query, InvoiceCommissionEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        InvoiceCommissionEntity entity = mongoTemplate.findOne(query, InvoiceCommissionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<InvoiceCommissionEntity> items = mongoTemplate.find(query, InvoiceCommissionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, InvoiceCommissionEntity.class);
    }

    @Override
    public InvoiceCommissionDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        InvoiceCommissionEntity entity = mongoTemplate.findOne(query, InvoiceCommissionEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<InvoiceCommissionDomain> queryAllSimpleDomainCaseListInvoiceCommission(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, InvoiceCommissionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<InvoiceCommissionEntity> results = mongoTemplate.find(query, InvoiceCommissionEntity.class);
        Page<InvoiceCommissionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListInvoiceCommission(resultsWithPage);
    }
}
