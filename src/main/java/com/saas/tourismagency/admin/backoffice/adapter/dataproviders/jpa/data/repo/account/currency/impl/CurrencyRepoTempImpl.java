package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CurrencyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.ICurrencyRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class CurrencyRepoTempImpl implements ICurrencyRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CurrencyConverter converter;

    @Override
    public PageDTO<CurrencyDomain> query(Pageable pageable, Query query) {
        query.with(pageable);
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CurrencyEntity.class);
        query.limit(pageable.getPageSize());
        List<CurrencyEntity> results = mongoTemplate.find(query, CurrencyEntity.class);
        Page<CurrencyEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CurrencyDomain> query(Query query) {
        List<CurrencyEntity> items = mongoTemplate.find(query, CurrencyEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CurrencyEntity entity = mongoTemplate.findOne(query, CurrencyEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CurrencyEntity> items = mongoTemplate.find(query, CurrencyEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<CurrencyDomain> queryAllSimpleDomainCaseListCurrency(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CurrencyEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CurrencyEntity> results = mongoTemplate.find(query, CurrencyEntity.class);
        Page<CurrencyEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCurrency(resultsWithPage);
    }
}
