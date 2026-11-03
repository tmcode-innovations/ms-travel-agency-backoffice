package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierInstitutionRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierInstitutionDomain;
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
public class SupplierInstitutionRepositoryTemplateImpl implements ISupplierInstitutionRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierInstitutionConverter converter;

    @Override
    public PageDTO<SupplierInstitutionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierInstitutionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierInstitutionEntity> results = mongoTemplate.find(query, SupplierInstitutionEntity.class);
        Page<SupplierInstitutionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierInstitutionDomain> query(Query query) {
        List<SupplierInstitutionEntity> items = mongoTemplate.find(query, SupplierInstitutionEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierInstitutionEntity entity = mongoTemplate.findOne(query, SupplierInstitutionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierInstitutionEntity> items = mongoTemplate.find(query, SupplierInstitutionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierInstitutionDomain> queryAllSimpleWithType(Query query) {
        List<SupplierInstitutionEntity> items = mongoTemplate.find(query, SupplierInstitutionEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierInstitutionDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierInstitutionEntity> items = mongoTemplate.find(query, SupplierInstitutionEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierInstitutionDomain> queryAllSimpleDomainCaseListSupplierInstitution(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierInstitutionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierInstitutionEntity> results = mongoTemplate.find(query, SupplierInstitutionEntity.class);
        Page<SupplierInstitutionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierInstitution(resultsWithPage);
    }
}
