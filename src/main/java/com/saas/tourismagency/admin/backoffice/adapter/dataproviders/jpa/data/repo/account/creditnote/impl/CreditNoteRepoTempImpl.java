package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnote.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.creditnote.CreditNoteConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnote.ICreditNoteRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
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
public class CreditNoteRepoTempImpl implements ICreditNoteRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CreditNoteConverter converter;

    @Override
    public PageDTO<CreditNoteDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CreditNoteEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CreditNoteEntity> results = mongoTemplate.find(query, CreditNoteEntity.class);
        Page<CreditNoteEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CreditNoteDomain> query(Query query) {
        List<CreditNoteEntity> files = mongoTemplate.find(query, CreditNoteEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CreditNoteEntity entity = mongoTemplate.findOne(query, CreditNoteEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CreditNoteEntity> items = mongoTemplate.find(query, CreditNoteEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, CreditNoteEntity.class);
    }

    @Override
    public CreditNoteDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        CreditNoteEntity entity = mongoTemplate.findOne(query, CreditNoteEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<CreditNoteDomain> queryAllSimpleDomainCaseListCreditNote(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CreditNoteEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CreditNoteEntity> results = mongoTemplate.find(query, CreditNoteEntity.class);
        Page<CreditNoteEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCreditNote(resultsWithPage);
    }
}
