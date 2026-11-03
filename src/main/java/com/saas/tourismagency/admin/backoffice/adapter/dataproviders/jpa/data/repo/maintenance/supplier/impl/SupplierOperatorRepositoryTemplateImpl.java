package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierOperatorRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
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
public class SupplierOperatorRepositoryTemplateImpl implements ISupplierOperatorRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierOperatorConverter converter;

    @Override
    public PageDTO<SupplierOperatorDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierOperatorEntity> results = mongoTemplate.find(query, SupplierOperatorEntity.class);
        Page<SupplierOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierOperatorDomain> query(Query query) {
        List<SupplierOperatorEntity> items = mongoTemplate.find(query, SupplierOperatorEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierOperatorEntity entity = mongoTemplate.findOne(query, SupplierOperatorEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierOperatorEntity> items = mongoTemplate.find(query, SupplierOperatorEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierOperatorDomain> queryAllSimpleWithType(Query query) {
        List<SupplierOperatorEntity> items = mongoTemplate.find(query, SupplierOperatorEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierOperatorDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierOperatorEntity> items = mongoTemplate.find(query, SupplierOperatorEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierOperatorDomain> queryAllSimpleDomainCaseListSupplierOperator(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierOperatorEntity> results = mongoTemplate.find(query, SupplierOperatorEntity.class);
        Page<SupplierOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierOperator(resultsWithPage);
    }
}
