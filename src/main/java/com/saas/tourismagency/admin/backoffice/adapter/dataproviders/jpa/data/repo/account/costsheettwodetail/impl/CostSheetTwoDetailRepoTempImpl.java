package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwodetail.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwodetail.CostSheetTwoDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwodetail.ICostSheetTwoDetailRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDetailDomain;
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
public class CostSheetTwoDetailRepoTempImpl implements ICostSheetTwoDetailRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostSheetTwoDetailConverter converter;

    @Override
    public PageDTO<CostSheetTwoDetailDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostSheetTwoDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostSheetTwoDetailEntity> results = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        Page<CostSheetTwoDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CostSheetTwoDetailDomain> query(Query query) {
        List<CostSheetTwoDetailEntity> files = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CostSheetTwoDetailEntity entity = mongoTemplate.findOne(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CostSheetTwoDetailEntity> items = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetail(Query query) {
        List<CostSheetTwoDetailEntity> items = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostSheetTwoDetail(items);
    }

    @Override
    public PageDTO<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseListCostSheetTwoDetail(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostSheetTwoDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostSheetTwoDetailEntity> results = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        Page<CostSheetTwoDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCostSheetTwoDetail(resultsWithPage);
    }

    @Override
    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailOnlyNoItem(Query query) {
        List<CostSheetTwoDetailEntity> items = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailOnlyNoItem(items);
    }

    @Override
    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailUpdateCalculate(Query query) {
        List<CostSheetTwoDetailEntity> items = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailUpdateCalculate(items);
    }

    @Override
    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailByDelete(Query query) {
        List<CostSheetTwoDetailEntity> items = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostSheetTwoDetail(items);
    }

    @Override
    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailByExport(Query query) {
        List<CostSheetTwoDetailEntity> items = mongoTemplate.find(query, CostSheetTwoDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailByExport(items);
    }
}
