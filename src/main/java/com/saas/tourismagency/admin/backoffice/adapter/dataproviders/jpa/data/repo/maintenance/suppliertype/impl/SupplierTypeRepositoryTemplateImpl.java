package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.ISupplierTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
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
public class SupplierTypeRepositoryTemplateImpl implements ISupplierTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierTypeConverter converter;

    @Override
    public PageDTO<SupplierTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierTypeEntity> results = mongoTemplate.find(query, SupplierTypeEntity.class);
        Page<SupplierTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierTypeDomain> query(Query query) {
        List<SupplierTypeEntity> items = mongoTemplate.find(query, SupplierTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierTypeEntity entity = mongoTemplate.findOne(query, SupplierTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierTypeEntity> items = mongoTemplate.find(query, SupplierTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierTypeDomain> queryAllSimpleByIdentify(Query query) {
        List<SupplierTypeEntity> items = mongoTemplate.find(query, SupplierTypeEntity.class);
        return converter.toDomainAllSimpleToDomainCaseByIdentify(items);
    }

    @Override
    public PageDTO<SupplierTypeDomain> queryAllSimpleDomainCaseListSupplierType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierTypeEntity> results = mongoTemplate.find(query, SupplierTypeEntity.class);
        Page<SupplierTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierType(resultsWithPage);
    }
}
