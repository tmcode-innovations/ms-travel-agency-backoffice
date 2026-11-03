package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderservice.OrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.RequestOrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.IOrderServiceRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
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
public class OrderServiceRepositoryTemplateImpl implements IOrderServiceRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private OrderServiceConverter converter;

    @Override
    public PageDTO<OrderServiceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, OrderServiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<OrderServiceEntity> results = mongoTemplate.find(query, OrderServiceEntity.class);
        Page<OrderServiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<OrderServiceDomain> query(Query query) {
        List<OrderServiceEntity> items = mongoTemplate.find(query, OrderServiceEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        OrderServiceEntity entity = mongoTemplate.findOne(query, OrderServiceEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<OrderServiceEntity> items = mongoTemplate.find(query, OrderServiceEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<OrderServiceDomain> queryAllSimpleDomain(Query query) {
        List<OrderServiceEntity> items = mongoTemplate.find(query, OrderServiceEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public PageDTO<OrderServiceDomain> queryAllSimpleDomainCaseListOrderService(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, OrderServiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<OrderServiceEntity> results = mongoTemplate.find(query, OrderServiceEntity.class);
        Page<OrderServiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListOrderService(resultsWithPage);
    }

    @Override
    public void updateOrderServiceCantPaxByIdCaseUpdateItem(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, OrderServiceEntity.class);
    }

    @Override
    public List<OrderServiceDomain> queryAllSimpleDomainCaseShowOrderServiceByDelete(Query query) {
        List<OrderServiceEntity> items = mongoTemplate.find(query, OrderServiceEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowOrderServiceByDelete(items);
    }
}
