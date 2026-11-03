package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierLodgingRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
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
public class SupplierLodgingRepositoryTemplateImpl implements ISupplierLodgingRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierLodgingConverter converter;

    @Override
    public PageDTO<SupplierLodgingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierLodgingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierLodgingEntity> results = mongoTemplate.find(query, SupplierLodgingEntity.class);
        Page<SupplierLodgingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierLodgingDomain> query(Query query) {
        List<SupplierLodgingEntity> items = mongoTemplate.find(query, SupplierLodgingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierLodgingEntity entity = mongoTemplate.findOne(query, SupplierLodgingEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierLodgingEntity> items = mongoTemplate.find(query, SupplierLodgingEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierLodgingDomain> queryAllSimpleWithType(Query query) {
        List<SupplierLodgingEntity> items = mongoTemplate.find(query, SupplierLodgingEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierLodgingDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierLodgingEntity> items = mongoTemplate.find(query, SupplierLodgingEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierLodgingDomain> queryAllSimpleDomainCaseListSupplierLodging(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierLodgingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierLodgingEntity> results = mongoTemplate.find(query, SupplierLodgingEntity.class);
        Page<SupplierLodgingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierLodging(resultsWithPage);
    }
}
