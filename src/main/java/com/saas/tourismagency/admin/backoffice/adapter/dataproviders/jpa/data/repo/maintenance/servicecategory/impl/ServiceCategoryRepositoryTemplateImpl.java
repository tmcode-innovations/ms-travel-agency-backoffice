package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.servicecategory.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.servicecategory.ServiceCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceCategoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.servicecategory.IServiceCategoryRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
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
public class ServiceCategoryRepositoryTemplateImpl implements IServiceCategoryRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ServiceCategoryConverter converter;

    @Override
    public PageDTO<ServiceCategoryDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ServiceCategoryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ServiceCategoryEntity> results = mongoTemplate.find(query, ServiceCategoryEntity.class);
        Page<ServiceCategoryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ServiceCategoryDomain> query(Query query) {
        List<ServiceCategoryEntity> items = mongoTemplate.find(query, ServiceCategoryEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ServiceCategoryEntity entity = mongoTemplate.findOne(query, ServiceCategoryEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ServiceCategoryEntity> items = mongoTemplate.find(query, ServiceCategoryEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ServiceCategoryDomain> queryAllSimpleDomainCaseListServiceCategory(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ServiceCategoryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ServiceCategoryEntity> results = mongoTemplate.find(query, ServiceCategoryEntity.class);
        Page<ServiceCategoryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListServiceCategory(resultsWithPage);
    }
}
