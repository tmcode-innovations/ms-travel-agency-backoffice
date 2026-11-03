package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformainvoice.ProformaInvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaInvoiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.IProformaInvoiceRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaInvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class ProformaInvoiceRepoTempImpl implements IProformaInvoiceRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProformaInvoiceConverter converter;

    @Override
    public PageDTO<ProformaInvoiceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProformaInvoiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProformaInvoiceEntity> results = mongoTemplate.find(query, ProformaInvoiceEntity.class);
        Page<ProformaInvoiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProformaInvoiceDomain> query(Query query) {
        List<ProformaInvoiceEntity> entities = mongoTemplate.find(query, ProformaInvoiceEntity.class);
        return converter.toDomainAll(entities);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProformaInvoiceEntity entity = mongoTemplate.findOne(query, ProformaInvoiceEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProformaInvoiceEntity> items = mongoTemplate.find(query, ProformaInvoiceEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ProformaInvoiceDomain> queryAllSimpleDomainCaseListProformaInvoice(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProformaInvoiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProformaInvoiceEntity> results = mongoTemplate.find(query, ProformaInvoiceEntity.class);
        Page<ProformaInvoiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListProformaInvoice(resultsWithPage);
    }
}
