package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.area.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.area.AreaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.area.IAreaRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
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
public class AreaRepositoryTemplateImpl implements IAreaRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private AreaConverter converter;

    @Override
    public PageDTO<AreaDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AreaEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AreaEntity> results = mongoTemplate.find(query, AreaEntity.class);
        Page<AreaEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<AreaDomain> query(Query query) {
        List<AreaEntity> items = mongoTemplate.find(query, AreaEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        AreaEntity entity = mongoTemplate.findOne(query, AreaEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<AreaEntity> items = mongoTemplate.find(query, AreaEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<AreaDomain> queryAllSimpleDomainCaseListArea(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AreaEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AreaEntity> results = mongoTemplate.find(query, AreaEntity.class);
        Page<AreaEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListArea(resultsWithPage);
    }
}
