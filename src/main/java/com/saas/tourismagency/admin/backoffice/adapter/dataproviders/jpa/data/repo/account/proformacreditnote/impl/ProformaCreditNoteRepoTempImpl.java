package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformacreditnote.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformacreditnote.ProformaCreditNoteConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaCreditNoteEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformacreditnote.IProformaCreditNoteRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
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
public class ProformaCreditNoteRepoTempImpl implements IProformaCreditNoteRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProformaCreditNoteConverter converter;

    @Override
    public PageDTO<ProformaCreditNoteDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProformaCreditNoteEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProformaCreditNoteEntity> results = mongoTemplate.find(query, ProformaCreditNoteEntity.class);
        Page<ProformaCreditNoteEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProformaCreditNoteDomain> query(Query query) {
        List<ProformaCreditNoteEntity> entities = mongoTemplate.find(query, ProformaCreditNoteEntity.class);
        return converter.toDomainAll(entities);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProformaCreditNoteEntity entity = mongoTemplate.findOne(query, ProformaCreditNoteEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProformaCreditNoteEntity> items = mongoTemplate.find(query, ProformaCreditNoteEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ProformaCreditNoteDomain> queryAllSimpleDomainCaseListProformaCreditNote(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProformaCreditNoteEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProformaCreditNoteEntity> results = mongoTemplate.find(query, ProformaCreditNoteEntity.class);
        Page<ProformaCreditNoteEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListProformaCreditNote(resultsWithPage);
    }
}
