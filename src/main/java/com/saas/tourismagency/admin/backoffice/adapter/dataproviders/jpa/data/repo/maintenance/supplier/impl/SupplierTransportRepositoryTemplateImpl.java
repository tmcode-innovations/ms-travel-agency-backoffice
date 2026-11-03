package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierTransportConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTransportEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierTransportRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTransportDomain;
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
public class SupplierTransportRepositoryTemplateImpl implements ISupplierTransportRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierTransportConverter converter;

    @Override
    public PageDTO<SupplierTransportDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierTransportEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierTransportEntity> results = mongoTemplate.find(query, SupplierTransportEntity.class);
        Page<SupplierTransportEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierTransportDomain> query(Query query) {
        List<SupplierTransportEntity> items = mongoTemplate.find(query, SupplierTransportEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierTransportEntity entity = mongoTemplate.findOne(query, SupplierTransportEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierTransportEntity> items = mongoTemplate.find(query, SupplierTransportEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SupplierTransportDomain> queryAllSimpleWithType(Query query) {
        List<SupplierTransportEntity> items = mongoTemplate.find(query, SupplierTransportEntity.class);
        return converter.toDomainAllSimpleWithType(items);
    }

    @Override
    public List<SupplierTransportDomain> queryAllSimpleWithDepartmentRegion(Query query) {
        List<SupplierTransportEntity> items = mongoTemplate.find(query, SupplierTransportEntity.class);
        return converter.toDomainAllSimpleWithDepartmentRegion(items);
    }

    @Override
    public PageDTO<SupplierTransportDomain> queryAllSimpleDomainCaseListSupplierTransport(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierTransportEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierTransportEntity> results = mongoTemplate.find(query, SupplierTransportEntity.class);
        Page<SupplierTransportEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListSupplierTransport(resultsWithPage);
    }
}
