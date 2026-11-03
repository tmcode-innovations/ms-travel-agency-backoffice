package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.restauranttype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.restauranttype.RestaurantTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.RestaurantTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.restauranttype.IRestaurantTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.RestaurantTypeDomain;
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
public class RestaurantTypeRepositoryTemplateImpl implements IRestaurantTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RestaurantTypeConverter converter;

    @Override
    public PageDTO<RestaurantTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RestaurantTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RestaurantTypeEntity> results = mongoTemplate.find(query, RestaurantTypeEntity.class);
        Page<RestaurantTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RestaurantTypeDomain> query(Query query) {
        List<RestaurantTypeEntity> items = mongoTemplate.find(query, RestaurantTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RestaurantTypeEntity entity = mongoTemplate.findOne(query, RestaurantTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RestaurantTypeEntity> items = mongoTemplate.find(query, RestaurantTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RestaurantTypeDomain> queryAllSimpleDomainCaseListRestaurantType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RestaurantTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RestaurantTypeEntity> results = mongoTemplate.find(query, RestaurantTypeEntity.class);
        Page<RestaurantTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRestaurantType(resultsWithPage);
    }
}
