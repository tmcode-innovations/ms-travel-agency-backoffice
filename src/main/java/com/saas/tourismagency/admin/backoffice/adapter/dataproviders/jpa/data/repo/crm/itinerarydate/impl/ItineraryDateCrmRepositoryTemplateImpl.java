package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itinerarydate.ItineraryDateCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.IItineraryDateCrmRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
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
public class ItineraryDateCrmRepositoryTemplateImpl implements IItineraryDateCrmRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ItineraryDateCrmConverter converter;

    @Override
    public PageDTO<ItineraryDateDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ItineraryDateEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ItineraryDateEntity> results = mongoTemplate.find(query, ItineraryDateEntity.class);
        Page<ItineraryDateEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ItineraryDateDomain> query(Query query) {
        List<ItineraryDateEntity> itineraryDates = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAll(itineraryDates);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ItineraryDateEntity entity = mongoTemplate.findOne(query, ItineraryDateEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseShowItinerary(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowItinerary(items);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseUpdateOrderItinerary(items);
    }

    @Override
    public ItineraryDateDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query) {
        ItineraryDateEntity entity = mongoTemplate.findOne(query, ItineraryDateEntity.class);
        return converter.toDomainIdSimpleDomainCaseUpdateOrderItinerary(entity);
    }

    @Override
    public void updateByIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ItineraryDateEntity.class);
    }

    @Override
    public ItineraryDateDomain queryOneIdSimpleDomainCaseDeleteItineraryDate(Query query) {
        ItineraryDateEntity entity = mongoTemplate.findOne(query, ItineraryDateEntity.class);
        return converter.toDomainIdSimpleDomainCaseDeleteItineraryDate(entity);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteItineraryDate(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteItineraryDate(items);
    }

    @Override
    public ItineraryDateDomain queryOneIdSimpleDomainCaseGeneric(Query query) {
        ItineraryDateEntity entity = mongoTemplate.findOne(query, ItineraryDateEntity.class);
        return converter.toDomainIdSimpleDomainCaseGeneric(entity);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseGeneric(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGeneric(items);
    }

    @Override
    public ItineraryDateDomain queryOneIdSimpleDomainCaseDeleteTour(Query query) {
        ItineraryDateEntity entity = mongoTemplate.findOne(query, ItineraryDateEntity.class);
        return converter.toDomainIdSimpleDomainCaseDeleteTour(entity);
    }

    @Override
    public void updateToursByIdSimpleDomainCaseDeleteTour(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ItineraryDateEntity.class);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteTour(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteTour(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateOrderItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ItineraryDateEntity.class);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotation(items);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendQuotationToBooking(items);
    }

    @Override
    public List<ItineraryDateDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query) {
        List<ItineraryDateEntity> items = mongoTemplate.find(query, ItineraryDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseChangeStateOpened(items);
    }
}
