package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwo.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwo.CostSheetTwoConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwo.ICostSheetTwoRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostSheetTwoRepoTempImpl implements ICostSheetTwoRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostSheetTwoConverter converter;

    @Override
    public PageDTO<CostSheetTwoDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostSheetTwoEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostSheetTwoEntity> results = mongoTemplate.find(query, CostSheetTwoEntity.class);
        Page<CostSheetTwoEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CostSheetTwoDomain> query(Query query) {
        List<CostSheetTwoEntity> files = mongoTemplate.find(query, CostSheetTwoEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CostSheetTwoEntity entity = mongoTemplate.findOne(query, CostSheetTwoEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CostSheetTwoEntity> items = mongoTemplate.find(query, CostSheetTwoEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<CostSheetTwoDomain> queryAllSimpleDomainCaseShowCostSheetTwo(Query query) {
        List<CostSheetTwoEntity> items = mongoTemplate.find(query, CostSheetTwoEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostSheetTwo(items);
    }

    @Override
    public PageDTO<CostSheetTwoDomain> queryAllSimpleDomainCaseListCostSheetTwo(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostSheetTwoEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostSheetTwoEntity> results = mongoTemplate.find(query, CostSheetTwoEntity.class);
        Page<CostSheetTwoEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCostSheetTwo(resultsWithPage);
    }

    @Override
    public CostSheetTwoDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        CostSheetTwoEntity entity = mongoTemplate.findOne(query, CostSheetTwoEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateCostSheetTwoBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, CostSheetTwoEntity.class);
    }
}
