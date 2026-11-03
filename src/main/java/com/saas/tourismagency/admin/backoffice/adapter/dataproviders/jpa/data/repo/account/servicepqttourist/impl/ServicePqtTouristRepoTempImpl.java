package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.servicepqttourist.ServicePqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ServicePqtTouristEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.IServicePqtTouristRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
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
public class ServicePqtTouristRepoTempImpl implements IServicePqtTouristRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ServicePqtTouristConverter converter;

    @Override
    public PageDTO<ServicePqtTouristDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ServicePqtTouristEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ServicePqtTouristEntity> results = mongoTemplate.find(query, ServicePqtTouristEntity.class);
        Page<ServicePqtTouristEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ServicePqtTouristDomain> query(Query query) {
        List<ServicePqtTouristEntity> files = mongoTemplate.find(query, ServicePqtTouristEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ServicePqtTouristEntity entity = mongoTemplate.findOne(query, ServicePqtTouristEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ServicePqtTouristEntity> items = mongoTemplate.find(query, ServicePqtTouristEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ServicePqtTouristDomain> queryAllSimpleDomainCaseShowServicePqtTourist(Query query) {
        List<ServicePqtTouristEntity> items = mongoTemplate.find(query, ServicePqtTouristEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowServicePqtTourist(items);
    }

    @Override
    public PageDTO<ServicePqtTouristDomain> queryAllSimpleDomainCaseListServicePqtTourist(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ServicePqtTouristEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ServicePqtTouristEntity> results = mongoTemplate.find(query, ServicePqtTouristEntity.class);
        Page<ServicePqtTouristEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListServicePqtTourist(resultsWithPage);
    }
}
