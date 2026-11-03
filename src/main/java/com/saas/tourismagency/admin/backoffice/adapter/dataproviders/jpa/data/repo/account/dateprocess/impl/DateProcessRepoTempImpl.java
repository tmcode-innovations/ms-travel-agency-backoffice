package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DateProcessEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.IDateProcessRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
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
public class DateProcessRepoTempImpl implements IDateProcessRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DateProcessConverter converter;

    @Override
    public PageDTO<DateProcessDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DateProcessEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DateProcessEntity> results = mongoTemplate.find(query, DateProcessEntity.class);
        Page<DateProcessEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DateProcessDomain> query(Query query) {
        List<DateProcessEntity> files = mongoTemplate.find(query, DateProcessEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DateProcessEntity entity = mongoTemplate.findOne(query, DateProcessEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DateProcessEntity> items = mongoTemplate.find(query, DateProcessEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<DateProcessDomain> queryAllSimpleDomainCaseShowDateProcess(Query query) {
        List<DateProcessEntity> items = mongoTemplate.find(query, DateProcessEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowDateProcess(items);
    }

    @Override
    public PageDTO<DateProcessDomain> queryAllSimpleDomainCaseListDateProcess(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DateProcessEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DateProcessEntity> results = mongoTemplate.find(query, DateProcessEntity.class);
        Page<DateProcessEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDateProcess(resultsWithPage);
    }
}
