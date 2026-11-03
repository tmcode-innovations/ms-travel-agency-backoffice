package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom.IdiomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom.IIdiomRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
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
public class IdiomRepositoryTemplateImpl implements IIdiomRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private IdiomConverter converter;

    @Override
    public PageDTO<IdiomDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, IdiomEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<IdiomEntity> results = mongoTemplate.find(query, IdiomEntity.class);
        Page<IdiomEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<IdiomDomain> query(Query query) {
        List<IdiomEntity> items = mongoTemplate.find(query, IdiomEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        IdiomEntity entity = mongoTemplate.findOne(query, IdiomEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<IdiomEntity> items = mongoTemplate.find(query, IdiomEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<IdiomDomain> queryAllSimpleDomainCaseListIdiom(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, IdiomEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<IdiomEntity> results = mongoTemplate.find(query, IdiomEntity.class);
        Page<IdiomEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListIdiom(resultsWithPage);
    }
}
