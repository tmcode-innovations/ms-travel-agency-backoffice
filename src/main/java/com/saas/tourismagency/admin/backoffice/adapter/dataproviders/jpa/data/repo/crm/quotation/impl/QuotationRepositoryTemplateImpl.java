package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.IQuotationRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
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
public class QuotationRepositoryTemplateImpl implements IQuotationRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private QuotationConverter converter;

    @Override
    public PageDTO<QuotationDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, QuotationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<QuotationEntity> results = mongoTemplate.find(query, QuotationEntity.class);
        Page<QuotationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<QuotationDomain> query(Query query) {
        List<QuotationEntity> files = mongoTemplate.find(query, QuotationEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        QuotationEntity entity = mongoTemplate.findOne(query, QuotationEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<QuotationEntity> items = mongoTemplate.find(query, QuotationEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public QuotationDomain queryOneIdSimpleDomainCaseCreateItinerary(Query query) {
        QuotationEntity entity = mongoTemplate.findOne(query, QuotationEntity.class);
        return converter.toDomainIdSimpleDomainCaseCreateItinerary(entity);
    }

    @Override
    public PageDTO<QuotationDomain> queryAllSimpleDomainCaseListQuotation(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, QuotationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<QuotationEntity> results = mongoTemplate.find(query, QuotationEntity.class);
        Page<QuotationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListQuotation(resultsWithPage);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateAmount(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, QuotationEntity.class);
    }

    @Override
    public void updateIdSimpleDomainCaseDeleteItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, QuotationEntity.class);
    }

    @Override
    public QuotationDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        QuotationEntity entity = mongoTemplate.findOne(query, QuotationEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public QuotationDomain queryOneIdSimpleDomainCaseGeneric(Query query) {
        QuotationEntity entity = mongoTemplate.findOne(query, QuotationEntity.class);
        return converter.toDomainIdSimpleDomainCaseGeneric(entity);
    }

    // Statistics

    @Override
    public Long countQuotation(Query query) {
        Long result = mongoTemplate.count(query, QuotationEntity.class);
        return result;
    }

    @Override
    public void updateIdSimpleDomainCaseUserDataModification(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, QuotationEntity.class);
    }

    @Override
    public QuotationDomain queryOneIdSimpleDomainCaseGetModificationUserData(Query query) {
        QuotationEntity entity = mongoTemplate.findOne(query, QuotationEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationUserData(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseCustomerModification(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, QuotationEntity.class);
    }

    @Override
    public List<QuotationDomain> queryAllSimpleDomainCaseToolUpdateEmployee(Query query) {
        List<QuotationEntity> results = mongoTemplate.find(query, QuotationEntity.class);
        return converter.toDomainAllSimpleToDomainCaseToolUpdateEmployee(results);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateToolEmployee(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, QuotationEntity.class);
    }
}
