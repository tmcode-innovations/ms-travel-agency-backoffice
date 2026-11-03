package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.IGuideRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
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
public class GuideRepositoryTemplateImpl implements IGuideRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private GuideConverter converter;

    @Override
    public PageDTO<GuideDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, GuideEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<GuideEntity> results = mongoTemplate.find(query, GuideEntity.class);
        Page<GuideEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<GuideDomain> query(Query query) {
        List<GuideEntity> guideTypes = mongoTemplate.find(query, GuideEntity.class);
        return converter.toDomainAll(guideTypes);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        GuideEntity entity = mongoTemplate.findOne(query, GuideEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<GuideDomain> queryAllSimple(Query query) {
        List<GuideEntity> items = mongoTemplate.find(query, GuideEntity.class);
        return converter.toDomainAllSimpleGuide(items);
    }

    @Override
    public PageDTO<GuideDomain> queryAllSimpleDomainCaseListGuide(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, GuideEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<GuideEntity> results = mongoTemplate.find(query, GuideEntity.class);
        Page<GuideEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListGuide(resultsWithPage);
    }
}
