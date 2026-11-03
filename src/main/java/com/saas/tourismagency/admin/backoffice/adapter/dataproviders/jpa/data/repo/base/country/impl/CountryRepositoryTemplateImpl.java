package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.ICountryRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
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
public class CountryRepositoryTemplateImpl implements ICountryRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CountryConverter converter;

    @Override
    public PageDTO<CountryDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CountryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CountryEntity> results = mongoTemplate.find(query, CountryEntity.class);
        Page<CountryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CountryDomain> query(Query query) {
        List<CountryEntity> items = mongoTemplate.find(query, CountryEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CountryEntity entity = mongoTemplate.findOne(query, CountryEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CountryEntity> items = mongoTemplate.find(query, CountryEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<CountryDomain> queryAllSimpleDomainCaseListCountry(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CountryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CountryEntity> results = mongoTemplate.find(query, CountryEntity.class);
        Page<CountryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCountry(resultsWithPage);
    }
}
