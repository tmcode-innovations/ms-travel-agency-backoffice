package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierLodgerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgerDomain;
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
public class SupplierLodgerRepositoryTemplateImpl implements ISupplierLodgerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierLodgerConverter converter;

    @Override
    public PageDTO<SupplierLodgerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierLodgerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierLodgerEntity> results = mongoTemplate.find(query, SupplierLodgerEntity.class);
        Page<SupplierLodgerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierLodgerDomain> query(Query query) {
        List<SupplierLodgerEntity> items = mongoTemplate.find(query, SupplierLodgerEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierLodgerEntity entity = mongoTemplate.findOne(query, SupplierLodgerEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierLodgerEntity> items = mongoTemplate.find(query, SupplierLodgerEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierLodgerDomain> queryAllSimpleWithType(Query query) {
        List<SupplierLodgerEntity> items = mongoTemplate.find(query, SupplierLodgerEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierLodgerDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierLodgerEntity> items = mongoTemplate.find(query, SupplierLodgerEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierLodgerDomain> queryAllSimpleDomainCaseListSupplierLodger(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierLodgerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierLodgerEntity> results = mongoTemplate.find(query, SupplierLodgerEntity.class);
        Page<SupplierLodgerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierLodger(resultsWithPage);
    }
}
