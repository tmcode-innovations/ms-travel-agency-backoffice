package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderserviceitem.OrderServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.IOrderServiceItemRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
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
public class OrderServiceItemRepositoryTemplateImpl implements IOrderServiceItemRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private OrderServiceItemConverter converter;

    @Override
    public PageDTO<OrderServiceItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, OrderServiceItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<OrderServiceItemEntity> results = mongoTemplate.find(query, OrderServiceItemEntity.class);
        Page<OrderServiceItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<OrderServiceItemDomain> query(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        OrderServiceItemEntity entity = mongoTemplate.findOne(query, OrderServiceItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<OrderServiceItemDomain> queryAllSimpleDomain(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItems(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseListOrderServiceItems(items);
    }

    @Override
    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(items);
    }

    @Override
    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(items);
    }

    @Override
    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseShowOrderServiceItemByDelete(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowOrderServiceItemByDelete(items);
    }

    @Override
    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItemsExpenseGuide(Query query) {
        List<OrderServiceItemEntity> items = mongoTemplate.find(query, OrderServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseListOrderServiceItemsExpenseGuide(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, OrderServiceItemEntity.class);
    }
}
