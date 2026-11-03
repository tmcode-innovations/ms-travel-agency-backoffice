package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.invoice.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.invoice.InvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.InvoiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.invoice.IInvoiceRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
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
public class InvoiceRepoTempImpl implements IInvoiceRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private InvoiceConverter converter;

    @Override
    public PageDTO<InvoiceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, InvoiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<InvoiceEntity> results = mongoTemplate.find(query, InvoiceEntity.class);
        Page<InvoiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<InvoiceDomain> query(Query query) {
        List<InvoiceEntity> files = mongoTemplate.find(query, InvoiceEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        InvoiceEntity entity = mongoTemplate.findOne(query, InvoiceEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<InvoiceEntity> items = mongoTemplate.find(query, InvoiceEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, InvoiceEntity.class);
    }

    @Override
    public InvoiceDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        InvoiceEntity entity = mongoTemplate.findOne(query, InvoiceEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<InvoiceDomain> queryAllSimpleDomainCaseListInvoice(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, InvoiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<InvoiceEntity> results = mongoTemplate.find(query, InvoiceEntity.class);
        Page<InvoiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListInvoice(resultsWithPage);
    }
}
