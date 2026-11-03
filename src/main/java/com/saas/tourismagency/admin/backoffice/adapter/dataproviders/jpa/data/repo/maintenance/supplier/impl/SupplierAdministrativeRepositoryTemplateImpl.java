package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierAdministrativeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAdministrativeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierAdministrativeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAdministrativeDomain;
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
public class SupplierAdministrativeRepositoryTemplateImpl implements ISupplierAdministrativeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierAdministrativeConverter converter;

    @Override
    public PageDTO<SupplierAdministrativeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierAdministrativeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierAdministrativeEntity> results = mongoTemplate.find(query, SupplierAdministrativeEntity.class);
        Page<SupplierAdministrativeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierAdministrativeDomain> query(Query query) {
        List<SupplierAdministrativeEntity> items = mongoTemplate.find(query, SupplierAdministrativeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierAdministrativeEntity entity = mongoTemplate.findOne(query, SupplierAdministrativeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierAdministrativeEntity> items = mongoTemplate.find(query, SupplierAdministrativeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierAdministrativeDomain> queryAllSimpleWithType(Query query) {
        List<SupplierAdministrativeEntity> items = mongoTemplate.find(query, SupplierAdministrativeEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierAdministrativeDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierAdministrativeEntity> items = mongoTemplate.find(query, SupplierAdministrativeEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierAdministrativeDomain> queryAllSimpleDomainCaseListSupplierAdministrative(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierAdministrativeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierAdministrativeEntity> results = mongoTemplate.find(query, SupplierAdministrativeEntity.class);
        Page<SupplierAdministrativeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierAdministrative(resultsWithPage);
    }
}
