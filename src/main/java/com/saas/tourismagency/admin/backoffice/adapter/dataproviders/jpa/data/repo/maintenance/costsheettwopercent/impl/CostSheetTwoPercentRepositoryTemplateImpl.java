package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.costsheettwopercent.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.costsheettwopercent.CostSheetTwoPercentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CostSheetTwoPercentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.costsheettwopercent.ICostSheetTwoPercentRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
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
public class CostSheetTwoPercentRepositoryTemplateImpl implements ICostSheetTwoPercentRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostSheetTwoPercentConverter converter;

    @Override
    public PageDTO<CostSheetTwoPercentDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostSheetTwoPercentEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostSheetTwoPercentEntity> results = mongoTemplate.find(query, CostSheetTwoPercentEntity.class);
        Page<CostSheetTwoPercentEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CostSheetTwoPercentDomain> query(Query query) {
        List<CostSheetTwoPercentEntity> costSheetTwoPercents = mongoTemplate.find(query, CostSheetTwoPercentEntity.class);
        return converter.toDomainAll(costSheetTwoPercents);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CostSheetTwoPercentEntity entity = mongoTemplate.findOne(query, CostSheetTwoPercentEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CostSheetTwoPercentEntity> items = mongoTemplate.find(query, CostSheetTwoPercentEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<CostSheetTwoPercentDomain> queryAllSimpleDomainCaseListCostSheetTwoPercent(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostSheetTwoPercentEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostSheetTwoPercentEntity> results = mongoTemplate.find(query, CostSheetTwoPercentEntity.class);
        Page<CostSheetTwoPercentEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCostSheetTwoPercent(resultsWithPage);
    }
}
