package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.LodgingCategoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.ILodgingCategoryRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
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
public class LodgingCategoryRepositoryTemplateImpl implements ILodgingCategoryRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private LodgingCategoryConverter converter;

    @Override
    public PageDTO<LodgingCategoryDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, LodgingCategoryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<LodgingCategoryEntity> results = mongoTemplate.find(query, LodgingCategoryEntity.class);
        Page<LodgingCategoryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<LodgingCategoryDomain> query(Query query) {
        List<LodgingCategoryEntity> items = mongoTemplate.find(query, LodgingCategoryEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        LodgingCategoryEntity entity = mongoTemplate.findOne(query, LodgingCategoryEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<LodgingCategoryEntity> items = mongoTemplate.find(query, LodgingCategoryEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<LodgingCategoryDomain> queryAllSimpleDomainCaseListLodgingCategory(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, LodgingCategoryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<LodgingCategoryEntity> results = mongoTemplate.find(query, LodgingCategoryEntity.class);
        Page<LodgingCategoryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListLodgingCategory(resultsWithPage);
    }
}
