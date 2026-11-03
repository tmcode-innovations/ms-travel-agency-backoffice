package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierAirLineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAirLineEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierAirLineRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAirLineDomain;
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
public class SupplierAirLineRepositoryTemplateImpl implements ISupplierAirLineRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierAirLineConverter converter;

    @Override
    public PageDTO<SupplierAirLineDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierAirLineEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierAirLineEntity> results = mongoTemplate.find(query, SupplierAirLineEntity.class);
        Page<SupplierAirLineEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierAirLineDomain> query(Query query) {
        List<SupplierAirLineEntity> items = mongoTemplate.find(query, SupplierAirLineEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierAirLineEntity entity = mongoTemplate.findOne(query, SupplierAirLineEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierAirLineEntity> items = mongoTemplate.find(query, SupplierAirLineEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierAirLineDomain> queryAllSimpleWithType(Query query) {
        List<SupplierAirLineEntity> items = mongoTemplate.find(query, SupplierAirLineEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierAirLineDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierAirLineEntity> items = mongoTemplate.find(query, SupplierAirLineEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierAirLineDomain> queryAllSimpleDomainCaseListSupplierAirLine(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierAirLineEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierAirLineEntity> results = mongoTemplate.find(query, SupplierAirLineEntity.class);
        Page<SupplierAirLineEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierAirLine(resultsWithPage);
    }
}
