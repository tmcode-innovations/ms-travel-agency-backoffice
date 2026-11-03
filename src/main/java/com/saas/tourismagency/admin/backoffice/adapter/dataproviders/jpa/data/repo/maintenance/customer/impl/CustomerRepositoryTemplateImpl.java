package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.ICustomerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
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
public class CustomerRepositoryTemplateImpl implements ICustomerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CustomerConverter converter;

    @Override
    public PageDTO<CustomerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CustomerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CustomerEntity> results = mongoTemplate.find(query, CustomerEntity.class);
        Page<CustomerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CustomerDomain> query(Query query) {
        List<CustomerEntity> items = mongoTemplate.find(query, CustomerEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CustomerEntity entity = mongoTemplate.findOne(query, CustomerEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CustomerEntity> items = mongoTemplate.find(query, CustomerEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public CustomerDomain queryOneName(Query query) {
        CustomerEntity entity = mongoTemplate.findOne(query, CustomerEntity.class);
        return converter.toDomainNameBase(entity);
    }

    // Statistics

    @Override
    public Long countCustomer(Query query) {
        Long result = mongoTemplate.count(query, CustomerEntity.class);
        return result;
    }

    @Override
    public PageDTO<CustomerDomain> queryAllSimpleDomainCaseListCustomer(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CustomerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CustomerEntity> results = mongoTemplate.find(query, CustomerEntity.class);
        Page<CustomerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCustomer(resultsWithPage);
    }
}
