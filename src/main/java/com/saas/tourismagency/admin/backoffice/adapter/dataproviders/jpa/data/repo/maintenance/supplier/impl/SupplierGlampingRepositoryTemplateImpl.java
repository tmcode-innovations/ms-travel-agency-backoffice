package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierGlampingRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGlampingDomain;
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
public class SupplierGlampingRepositoryTemplateImpl implements ISupplierGlampingRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierGlampingConverter converter;

    @Override
    public PageDTO<SupplierGlampingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierGlampingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierGlampingEntity> results = mongoTemplate.find(query, SupplierGlampingEntity.class);
        Page<SupplierGlampingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierGlampingDomain> query(Query query) {
        List<SupplierGlampingEntity> items = mongoTemplate.find(query, SupplierGlampingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierGlampingEntity entity = mongoTemplate.findOne(query, SupplierGlampingEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierGlampingEntity> items = mongoTemplate.find(query, SupplierGlampingEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierGlampingDomain> queryAllSimpleWithType(Query query) {
        List<SupplierGlampingEntity> items = mongoTemplate.find(query, SupplierGlampingEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierGlampingDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierGlampingEntity> items = mongoTemplate.find(query, SupplierGlampingEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierGlampingDomain> queryAllSimpleDomainCaseListSupplierGlamping(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierGlampingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierGlampingEntity> results = mongoTemplate.find(query, SupplierGlampingEntity.class);
        Page<SupplierGlampingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierGlamping(resultsWithPage);
    }
}
