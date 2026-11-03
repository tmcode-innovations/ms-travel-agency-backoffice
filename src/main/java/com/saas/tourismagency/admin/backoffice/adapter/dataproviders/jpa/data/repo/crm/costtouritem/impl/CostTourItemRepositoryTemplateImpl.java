package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtouritem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtouritem.CostTourItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtouritem.CostTourItemRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain;
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
public class CostTourItemRepositoryTemplateImpl implements CostTourItemRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostTourItemConverter converter;

    @Override
    public PageDTO<CostTourItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostTourItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostTourItemEntity> results = mongoTemplate.find(query, CostTourItemEntity.class);
        Page<CostTourItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CostTourItemDomain> query(Query query) {
        List<CostTourItemEntity> itineraryItems = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAll(itineraryItems);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CostTourItemEntity entity = mongoTemplate.findOne(query, CostTourItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<CostTourItemDomain> queryAllSimpleDomainCaseShowCostTourItem(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostTourItem(items);
    }

    @Override
    public List<CostTourItemDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotation(items);
    }

    @Override
    public List<CostTourItemDomain> queryAllSimpleDomainCaseResendQuotationToBookingByCostTourAndQuotation(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendQuotationToBookingByCostTourAndQuotation(items);
    }

    public List<CostTourItemDomain> queryAllSimpleDomainCaseChangeStateOpenedByCostTourAndQuotation(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseChangeStateOpenedByCostTourAndQuotation(items);
    }
}
