package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.collection.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.collection.CollectionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.collection.ICollectionRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
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
public class CollectionRepoTempImpl implements ICollectionRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CollectionConverter converter;

    @Override
    public PageDTO<CollectionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CollectionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CollectionEntity> results = mongoTemplate.find(query, CollectionEntity.class);
        Page<CollectionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CollectionDomain> query(Query query) {
        List<CollectionEntity> files = mongoTemplate.find(query, CollectionEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CollectionEntity entity = mongoTemplate.findOne(query, CollectionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CollectionEntity> items = mongoTemplate.find(query, CollectionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, CollectionEntity.class);
    }

    @Override
    public CollectionDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        CollectionEntity entity = mongoTemplate.findOne(query, CollectionEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<CollectionDomain> queryAllSimpleDomainCaseListCollection(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CollectionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CollectionEntity> results = mongoTemplate.find(query, CollectionEntity.class);
        Page<CollectionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCollection(resultsWithPage);
    }
}
