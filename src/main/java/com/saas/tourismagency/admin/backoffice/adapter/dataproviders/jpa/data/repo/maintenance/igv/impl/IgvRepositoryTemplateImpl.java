package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.igv.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.igv.IgvConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.igv.IIgvRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
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
public class IgvRepositoryTemplateImpl implements IIgvRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private IgvConverter converter;

    @Override
    public PageDTO<IgvDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, IgvEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<IgvEntity> results = mongoTemplate.find(query, IgvEntity.class);
        Page<IgvEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<IgvDomain> query(Query query) {
        List<IgvEntity> guideTypes = mongoTemplate.find(query, IgvEntity.class);
        return converter.toDomainAll(guideTypes);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        IgvEntity entity = mongoTemplate.findOne(query, IgvEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<IgvEntity> items = mongoTemplate.find(query, IgvEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<IgvDomain> queryAllSimpleDomainCaseListIgv(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, IgvEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<IgvEntity> results = mongoTemplate.find(query, IgvEntity.class);
        Page<IgvEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListIgv(resultsWithPage);
    }
}
