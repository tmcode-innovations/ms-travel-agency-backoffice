package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.debitnote.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.debitnote.DebitNoteConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DebitNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.debitnote.IDebitNoteRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.DebitNoteDomain;
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
public class DebitNoteRepoTempImpl implements IDebitNoteRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DebitNoteConverter converter;

    @Override
    public PageDTO<DebitNoteDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DebitNoteEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DebitNoteEntity> results = mongoTemplate.find(query, DebitNoteEntity.class);
        Page<DebitNoteEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DebitNoteDomain> query(Query query) {
        List<DebitNoteEntity> files = mongoTemplate.find(query, DebitNoteEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DebitNoteEntity entity = mongoTemplate.findOne(query, DebitNoteEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DebitNoteEntity> items = mongoTemplate.find(query, DebitNoteEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, DebitNoteEntity.class);
    }

    @Override
    public DebitNoteDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        DebitNoteEntity entity = mongoTemplate.findOne(query, DebitNoteEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<DebitNoteDomain> queryAllSimpleDomainCaseListDebitNote(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DebitNoteEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DebitNoteEntity> results = mongoTemplate.find(query, DebitNoteEntity.class);
        Page<DebitNoteEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDebitNote(resultsWithPage);
    }
}
