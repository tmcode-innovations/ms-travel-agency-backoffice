package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnotecommission.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.creditnotecommission.CreditNoteCommissionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteCommissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnotecommission.ICreditNoteCommissionRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteCommissionDomain;
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
public class CreditNoteCommissionRepoTempImpl implements ICreditNoteCommissionRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CreditNoteCommissionConverter converter;

    @Override
    public PageDTO<CreditNoteCommissionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CreditNoteCommissionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CreditNoteCommissionEntity> results = mongoTemplate.find(query, CreditNoteCommissionEntity.class);
        Page<CreditNoteCommissionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CreditNoteCommissionDomain> query(Query query) {
        List<CreditNoteCommissionEntity> files = mongoTemplate.find(query, CreditNoteCommissionEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CreditNoteCommissionEntity entity = mongoTemplate.findOne(query, CreditNoteCommissionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CreditNoteCommissionEntity> items = mongoTemplate.find(query, CreditNoteCommissionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, CreditNoteCommissionEntity.class);
    }

    @Override
    public CreditNoteCommissionDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        CreditNoteCommissionEntity entity = mongoTemplate.findOne(query, CreditNoteCommissionEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<CreditNoteCommissionDomain> queryAllSimpleDomainCaseListCreditNoteCommission(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CreditNoteCommissionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CreditNoteCommissionEntity> results = mongoTemplate.find(query, CreditNoteCommissionEntity.class);
        Page<CreditNoteCommissionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCreditNoteCommission(resultsWithPage);
    }
}
