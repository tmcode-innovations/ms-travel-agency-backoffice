package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.IChangeTypeRepoTemp;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ChangeTypeRepoTempImpl implements IChangeTypeRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ChangeTypeConverter converter;

    @Override
    public PageDTO<ChangeTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ChangeTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ChangeTypeEntity> results = mongoTemplate.find(query, ChangeTypeEntity.class);
        Page<ChangeTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ChangeTypeDomain> query(Query query) {
        List<ChangeTypeEntity> files = mongoTemplate.find(query, ChangeTypeEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ChangeTypeEntity entity = mongoTemplate.findOne(query, ChangeTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ChangeTypeEntity> items = mongoTemplate.find(query, ChangeTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ChangeTypeDomain> queryAllSimpleDomainCaseShowChangeType(Query query) {
        List<ChangeTypeEntity> items = mongoTemplate.find(query, ChangeTypeEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowChangeType(items);
    }

    @Override
    public PageDTO<ChangeTypeDomain> queryAllSimpleDomainCaseListChangeType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ChangeTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ChangeTypeEntity> results = mongoTemplate.find(query, ChangeTypeEntity.class);
        Page<ChangeTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListChangeType(resultsWithPage);
    }

    @Override
    public List<ChangeTypeEntity> aggregateChangeTypeByCurrentDate(LocalDate localDate) {
        LookupOperation lookupOperation = Aggregation.lookup("account_date_process", "dateProcess", "_id", "joinedData");
        Criteria criteria = new Criteria()
                .andOperator(
                        Criteria.where("deleted").is(false),
                        Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()),
                        Criteria.where("joinedData.date").is(localDate)
                );
        MatchOperation matchOperation = Aggregation.match(criteria);
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchOperation);
        List<ChangeTypeEntity> result = mongoTemplate.aggregate(aggregation, "account_change_types", ChangeTypeEntity.class).getMappedResults();
        return result;
    }

    @Override
    public List<ChangeTypeDomain> queryAllSimpleDomainCaseToolUpdateChangeType(Query query) {
        List<ChangeTypeEntity> items = mongoTemplate.find(query, ChangeTypeEntity.class);
        return converter.toDomainAllSimpleToDomainCaseToolUpdateChangeType(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateToolChangeType(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ChangeTypeEntity.class);
    }
}
