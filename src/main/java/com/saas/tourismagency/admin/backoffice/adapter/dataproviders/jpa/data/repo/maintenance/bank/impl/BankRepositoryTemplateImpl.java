package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.bank.BankConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.IBankRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
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
public class BankRepositoryTemplateImpl implements IBankRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private BankConverter converter;

    @Override
    public PageDTO<BankDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, BankEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<BankEntity> results = mongoTemplate.find(query, BankEntity.class);
        Page<BankEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<BankDomain> query(Query query) {
        List<BankEntity> items = mongoTemplate.find(query, BankEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        BankEntity entity = mongoTemplate.findOne(query, BankEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<BankEntity> items = mongoTemplate.find(query, BankEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<BankDomain> queryAllSimpleDomainCaseListBank(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, BankEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<BankEntity> results = mongoTemplate.find(query, BankEntity.class);
        Page<BankEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListBank(resultsWithPage);
    }
}
