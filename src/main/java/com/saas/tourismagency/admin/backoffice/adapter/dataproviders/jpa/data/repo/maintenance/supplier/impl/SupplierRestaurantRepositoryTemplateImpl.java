package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierRestaurantRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierRestaurantDomain;
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
public class SupplierRestaurantRepositoryTemplateImpl implements ISupplierRestaurantRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierRestaurantConverter converter;

    @Override
    public PageDTO<SupplierRestaurantDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierRestaurantEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierRestaurantEntity> results = mongoTemplate.find(query, SupplierRestaurantEntity.class);
        Page<SupplierRestaurantEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierRestaurantDomain> query(Query query) {
        List<SupplierRestaurantEntity> items = mongoTemplate.find(query, SupplierRestaurantEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierRestaurantEntity entity = mongoTemplate.findOne(query, SupplierRestaurantEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierRestaurantEntity> items = mongoTemplate.find(query, SupplierRestaurantEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierRestaurantDomain> queryAllSimpleWithType(Query query) {
        List<SupplierRestaurantEntity> items = mongoTemplate.find(query, SupplierRestaurantEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierRestaurantDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierRestaurantEntity> items = mongoTemplate.find(query, SupplierRestaurantEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierRestaurantDomain> queryAllSimpleDomainCaseListSupplierRestaurant(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierRestaurantEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierRestaurantEntity> results = mongoTemplate.find(query, SupplierRestaurantEntity.class);
        Page<SupplierRestaurantEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierRestaurant(resultsWithPage);
    }
}
