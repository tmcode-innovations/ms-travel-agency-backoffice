package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.passenger.PassengerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.PassengerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.IPassengerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
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
public class PassengerRepositoryTemplateImpl implements IPassengerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PassengerConverter converter;

    @Override
    public PageDTO<PassengerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PassengerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PassengerEntity> results = mongoTemplate.find(query, PassengerEntity.class);
        Page<PassengerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<PassengerDomain> query(Query query) {
        List<PassengerEntity> passengers = mongoTemplate.find(query, PassengerEntity.class);
        return converter.toDomainAll(passengers);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        PassengerEntity entity = mongoTemplate.findOne(query, PassengerEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<PassengerEntity> items = mongoTemplate.find(query, PassengerEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<PassengerDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query) {
        List<PassengerEntity> items = mongoTemplate.find(query, PassengerEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByQuotation(items);
    }

    @Override
    public List<PassengerDomain> queryAllSimpleDomainCaseGetCollectionForLastIndex(Query query) {
        List<PassengerEntity> items = mongoTemplate.find(query, PassengerEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGetCollectionForLastIndex(items);
    }

    @Override
    public List<PassengerDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query) {
        List<PassengerEntity> items = mongoTemplate.find(query, PassengerEntity.class);
        return converter.toDomainAllSimpleToDomainCaseResendQuotationToBooking(items);
    }

    public List<PassengerDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query) {
        List<PassengerEntity> items = mongoTemplate.find(query, PassengerEntity.class);
        return converter.toDomainAllSimpleToDomainCaseChangeStateOpened(items);
    }
}
