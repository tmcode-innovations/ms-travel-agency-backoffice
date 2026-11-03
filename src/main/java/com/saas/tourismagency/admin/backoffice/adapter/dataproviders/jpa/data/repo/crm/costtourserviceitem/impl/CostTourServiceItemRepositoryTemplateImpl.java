package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtourserviceitem.CostTourServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem.CostTourServiceItemRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
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
public class CostTourServiceItemRepositoryTemplateImpl implements CostTourServiceItemRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostTourServiceItemConverter converter;

    @Override
    public PageDTO<CostTourServiceItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CostTourServiceItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CostTourServiceItemEntity> results = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        Page<CostTourServiceItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<CostTourServiceItemDomain> query(Query query) {
        List<CostTourServiceItemEntity> itineraryItems = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        return converter.toDomainAll(itineraryItems);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        CostTourServiceItemEntity entity = mongoTemplate.findOne(query, CostTourServiceItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<CostTourServiceItemEntity> items = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseShowCostTourServiceItem(Query query) {
        List<CostTourServiceItemEntity> items = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowCostTourServiceItem(items);
    }

    @Override
    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query) {
        List<CostTourServiceItemEntity> items = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotation(items);
    }

    @Override
    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseResendQuotationToBookingByCostTourAndQuotation(Query query) {
        List<CostTourServiceItemEntity> items = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendQuotationToBookingByCostTourAndQuotation(items);
    }

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseChangeStateOpenedByCostTourAndQuotation(Query query) {
        List<CostTourServiceItemEntity> items = mongoTemplate.find(query, CostTourServiceItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseChangeStateOpenedByCostTourAndQuotation(items);
    }
}
