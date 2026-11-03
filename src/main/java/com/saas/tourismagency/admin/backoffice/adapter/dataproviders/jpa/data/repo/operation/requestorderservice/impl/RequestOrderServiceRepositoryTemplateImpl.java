package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.requestorderservice.RequestOrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.RequestOrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.IRequestOrderServiceRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
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
public class RequestOrderServiceRepositoryTemplateImpl implements IRequestOrderServiceRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RequestOrderServiceConverter converter;

    @Override
    public PageDTO<RequestOrderServiceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RequestOrderServiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RequestOrderServiceEntity> results = mongoTemplate.find(query, RequestOrderServiceEntity.class);
        Page<RequestOrderServiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RequestOrderServiceDomain> query(Query query) {
        List<RequestOrderServiceEntity> items = mongoTemplate.find(query, RequestOrderServiceEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RequestOrderServiceEntity entity = mongoTemplate.findOne(query, RequestOrderServiceEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RequestOrderServiceEntity> items = mongoTemplate.find(query, RequestOrderServiceEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<RequestOrderServiceDomain> queryAllSimpleDomain(Query query) {
        List<RequestOrderServiceEntity> items = mongoTemplate.find(query, RequestOrderServiceEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public RequestOrderServiceDomain queryOneIdSimpleDomainCaseGenerateRequestOrderService(Query query) {
        RequestOrderServiceEntity entity = mongoTemplate.findOne(query, RequestOrderServiceEntity.class);
        return converter.toDomainIdSimpleDomainCaseGenerateRequestOrderService(entity);
    }

    @Override
    public PageDTO<RequestOrderServiceDomain> queryAllSimpleDomainCaseListRequestOrderService(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RequestOrderServiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RequestOrderServiceEntity> results = mongoTemplate.find(query, RequestOrderServiceEntity.class);
        Page<RequestOrderServiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRequestOrderService(resultsWithPage);
    }

    @Override
    public void updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RequestOrderServiceEntity.class);
    }
}
