package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtouritem.CostTourItemBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.CostTourItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem.CostTourItemBookingRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain;
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
public class CostTourItemBookingRepositoryTemplateImpl implements CostTourItemBookingRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CostTourItemBookingConverter converter;

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
    public List<CostTourItemDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(items);
    }

    @Override
    public List<CostTourItemDomain> queryAllSimpleDomainCaseResendFileToSaleByCostTourAndRegisterGroup(Query query) {
        List<CostTourItemEntity> items = mongoTemplate.find(query, CostTourItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendFileToSaleByCostTourAndRegisterGroup(items);
    }
}
