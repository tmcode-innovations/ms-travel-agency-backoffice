package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinance.IDocumentFinanceRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
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
public class DocumentFinanceRepoTempImpl implements IDocumentFinanceRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DocumentFinanceConverter converter;

    @Override
    public PageDTO<DocumentFinanceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DocumentFinanceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DocumentFinanceEntity> results = mongoTemplate.find(query, DocumentFinanceEntity.class);
        Page<DocumentFinanceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DocumentFinanceDomain> query(Query query) {
        List<DocumentFinanceEntity> files = mongoTemplate.find(query, DocumentFinanceEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DocumentFinanceEntity entity = mongoTemplate.findOne(query, DocumentFinanceEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DocumentFinanceEntity> items = mongoTemplate.find(query, DocumentFinanceEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
