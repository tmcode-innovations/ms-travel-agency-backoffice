package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.detraction.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.detraction.DetractionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DetractionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.detraction.IDetractionRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
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
public class DetractionRepositoryTemplateImpl implements IDetractionRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DetractionConverter converter;

    @Override
    public PageDTO<DetractionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DetractionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DetractionEntity> results = mongoTemplate.find(query, DetractionEntity.class);
        Page<DetractionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DetractionDomain> query(Query query) {
        List<DetractionEntity> items = mongoTemplate.find(query, DetractionEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DetractionEntity entity = mongoTemplate.findOne(query, DetractionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DetractionEntity> items = mongoTemplate.find(query, DetractionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<DetractionDomain> queryAllSimpleDomainCaseListDetraction(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DetractionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DetractionEntity> results = mongoTemplate.find(query, DetractionEntity.class);
        Page<DetractionEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDetraction(resultsWithPage);
    }
}
