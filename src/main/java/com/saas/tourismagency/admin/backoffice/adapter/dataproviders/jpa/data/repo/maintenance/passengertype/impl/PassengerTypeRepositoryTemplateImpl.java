package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.passengertype.PassengerTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PassengerTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.IPassengerTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
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
public class PassengerTypeRepositoryTemplateImpl implements IPassengerTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PassengerTypeConverter converter;

    @Override
    public PageDTO<PassengerTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PassengerTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PassengerTypeEntity> results = mongoTemplate.find(query, PassengerTypeEntity.class);
        Page<PassengerTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<PassengerTypeDomain> query(Query query) {
        List<PassengerTypeEntity> items = mongoTemplate.find(query, PassengerTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        PassengerTypeEntity entity = mongoTemplate.findOne(query, PassengerTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<PassengerTypeEntity> items = mongoTemplate.find(query, PassengerTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<PassengerTypeDomain> queryAllSimpleDomainCaseListPassengerType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PassengerTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PassengerTypeEntity> results = mongoTemplate.find(query, PassengerTypeEntity.class);
        Page<PassengerTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListPassengerType(resultsWithPage);
    }
}
