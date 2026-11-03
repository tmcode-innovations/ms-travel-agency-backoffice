package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itineraryitem.ItineraryItemCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.IItineraryItemCrmRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
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
public class ItineraryItemCrmRepositoryTemplateImpl implements IItineraryItemCrmRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ItineraryItemCrmConverter converter;

    @Override
    public PageDTO<ItineraryItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ItineraryItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ItineraryItemEntity> results = mongoTemplate.find(query, ItineraryItemEntity.class);
        Page<ItineraryItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ItineraryItemDomain> query(Query query) {
        List<ItineraryItemEntity> itineraryItems = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAll(itineraryItems);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ItineraryItemEntity entity = mongoTemplate.findOne(query, ItineraryItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomain(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseShowItinerary(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowItinerary(items);
    }

    @Override
    public ItineraryItemDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query) {
        ItineraryItemEntity entity = mongoTemplate.findOne(query, ItineraryItemEntity.class);
        return converter.toDomainIdSimpleDomainCaseUpdateOrderItinerary(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseUpdateOrderItinerary(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGetCalculateCost(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGetCalculateCost(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGetCollectionForLastIndex(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGetCollectionForLastIndex(items);
    }

    @Override
    public ItineraryItemDomain queryOneIdSimpleDomainCaseGeneric(Query query) {
        ItineraryItemEntity entity = mongoTemplate.findOne(query, ItineraryItemEntity.class);
        return converter.toDomainIdSimpleDomainCaseUpdateOrderItinerary(entity);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteTours(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteTours(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotation(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendQuotationToBooking(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseChangeStateOpened(items);
    }
}
