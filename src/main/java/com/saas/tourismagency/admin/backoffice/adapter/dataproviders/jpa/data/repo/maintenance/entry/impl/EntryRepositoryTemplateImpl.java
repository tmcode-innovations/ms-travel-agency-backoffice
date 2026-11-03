package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.entry.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.entry.EntryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.EntryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.entry.IEntryRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
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
public class EntryRepositoryTemplateImpl implements IEntryRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private EntryConverter converter;

    @Override
    public PageDTO<EntryDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, EntryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<EntryEntity> results = mongoTemplate.find(query, EntryEntity.class);
        Page<EntryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<EntryDomain> query(Query query) {
        List<EntryEntity> items = mongoTemplate.find(query, EntryEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        EntryEntity entity = mongoTemplate.findOne(query, EntryEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<EntryEntity > items = mongoTemplate.find(query, EntryEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<EntryDomain> queryAllSimpleDocumentFinance(Query query) {
        List<EntryEntity > items = mongoTemplate.find(query, EntryEntity.class);
        return converter.toDomainAllSimpleDocumentFinance(items);
    }

    @Override
    public PageDTO<EntryDomain> queryAllSimpleDomainCaseListEntry(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, EntryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<EntryEntity> results = mongoTemplate.find(query, EntryEntity.class);
        Page<EntryEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListEntry(resultsWithPage);
    }
}
