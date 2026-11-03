package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtour.CostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
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
public class CostTourRepositoryTemplateImpl implements CostTourRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostTourConverter converter;

    @Override
    public PageDTO<CostTourDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostTourEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostTourEntity> results = mongoTemplate.find(query, CostTourEntity.class);
        Page<CostTourEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CostTourDomain> query(Query query) {
        List<CostTourEntity> costTours = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAll(costTours);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CostTourEntity entity = mongoTemplate.findOne(query, CostTourEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CostTourEntity> items = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<CostTourDomain> queryAllSimpleDomainCaseShowCostTour(Query query) {
        List<CostTourEntity> items = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostTour(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateSubTotalAmount(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, CostTourEntity.class);
    }

    @Override
    public List<CostTourDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query) {
        List<CostTourEntity> items = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotation(items);
    }

    @Override
    public List<CostTourDomain> queryAllSimpleDomainCaseDeleteByQuotationAndItineraryDate(Query query) {
        List<CostTourEntity> items = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotationAndItineraryDate(items);
    }

    @Override
    public List<CostTourDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query) {
        List<CostTourEntity> items = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendQuotationToBooking(items);
    }

    public List<CostTourDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query) {
        List<CostTourEntity> items = mongoTemplate.find(query, CostTourEntity.class);
        return converter.toDomainAllSimpleToDomainCaseChangeStateOpened(items);
    }
}
