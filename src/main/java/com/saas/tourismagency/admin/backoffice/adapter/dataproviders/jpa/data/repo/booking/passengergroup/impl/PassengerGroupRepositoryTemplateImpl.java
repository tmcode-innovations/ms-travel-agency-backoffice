package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengergroup.PassengerGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.PassengerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.IPassengerGroupRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
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
public class PassengerGroupRepositoryTemplateImpl implements IPassengerGroupRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PassengerGroupConverter converter;

    @Override
    public PageDTO<PassengerGroupDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PassengerGroupEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PassengerGroupEntity> results = mongoTemplate.find(query, PassengerGroupEntity.class);
        Page<PassengerGroupEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<PassengerGroupDomain> query(Query query) {
        List<PassengerGroupEntity> passengers = mongoTemplate.find(query, PassengerGroupEntity.class);
        return converter.toDomainAll(passengers);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        PassengerGroupEntity entity = mongoTemplate.findOne(query, PassengerGroupEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<PassengerGroupEntity> items = mongoTemplate.find(query, PassengerGroupEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<PassengerGroupDomain> queryAllSimpleDomainCaseListPassengerGroup(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PassengerGroupEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PassengerGroupEntity> results = mongoTemplate.find(query, PassengerGroupEntity.class);
        Page<PassengerGroupEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListPassengerGroup(resultsWithPage);
    }

    @Override
    public List<PassengerGroupDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query) {
        List<PassengerGroupEntity> items = mongoTemplate.find(query, PassengerGroupEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(items);
    }

    @Override
    public List<PassengerGroupDomain> queryAllSimpleDomainCaseGetCollectionForLastIndex(Query query) {
        List<PassengerGroupEntity> items = mongoTemplate.find(query, PassengerGroupEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGetCollectionForLastIndex(items);
    }
}
