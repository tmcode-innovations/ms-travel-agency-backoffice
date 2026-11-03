package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.configurationsupplieroperator.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.configurationsupplieroperator.ConfigurationSupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.ConfigurationSupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.configurationsupplieroperator.IConfigurationSupplierOperatorRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
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
public class ConfigurationSupplierOperatorRepTempImpl implements IConfigurationSupplierOperatorRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ConfigurationSupplierOperatorConverter converter;

    @Override
    public PageDTO<ConfigurationSupplierOperatorDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ConfigurationSupplierOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ConfigurationSupplierOperatorEntity> results = mongoTemplate.find(query, ConfigurationSupplierOperatorEntity.class);
        Page<ConfigurationSupplierOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ConfigurationSupplierOperatorDomain> query(Query query) {
        List<ConfigurationSupplierOperatorEntity> items = mongoTemplate.find(query, ConfigurationSupplierOperatorEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ConfigurationSupplierOperatorEntity entity = mongoTemplate.findOne(query, ConfigurationSupplierOperatorEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ConfigurationSupplierOperatorEntity> items = mongoTemplate.find(query, ConfigurationSupplierOperatorEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ConfigurationSupplierOperatorDomain> queryAllSimpleDomain(Query query) {
        List<ConfigurationSupplierOperatorEntity> items = mongoTemplate.find(query, ConfigurationSupplierOperatorEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public ConfigurationSupplierOperatorDomain queryOneIdSimpleDomainCaseGenerateRequestOrderService(Query query) {
        ConfigurationSupplierOperatorEntity entity = mongoTemplate.findOne(query, ConfigurationSupplierOperatorEntity.class);
        return converter.toDomainIdSimpleDomainCaseGenerateRequestOrderService(entity);
    }

    @Override
    public PageDTO<ConfigurationSupplierOperatorDomain> queryAllSimpleDomainCaseListConfigurationSupplierOperator(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ConfigurationSupplierOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ConfigurationSupplierOperatorEntity> results = mongoTemplate.find(query, ConfigurationSupplierOperatorEntity.class);
        Page<ConfigurationSupplierOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListConfigurationSupplierOperator(resultsWithPage);
    }
}
