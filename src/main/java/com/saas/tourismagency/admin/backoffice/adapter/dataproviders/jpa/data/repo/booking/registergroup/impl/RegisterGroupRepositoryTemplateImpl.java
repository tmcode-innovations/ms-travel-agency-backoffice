package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRoomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.IRegisterGroupRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class RegisterGroupRepositoryTemplateImpl implements IRegisterGroupRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterGroupConverter converter;

    @Override
    public PageDTO<RegisterGroupDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingRoomEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterGroupEntity> results = mongoTemplate.find(query, RegisterGroupEntity.class);
        Page<RegisterGroupEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterGroupDomain> query(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterGroupEntity entity = mongoTemplate.findOne(query, RegisterGroupEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public RegisterGroupDomain queryOneIdSimpleDomainCaseCreateItinerary(Query query) {
        RegisterGroupEntity entity = mongoTemplate.findOne(query, RegisterGroupEntity.class);
        return converter.toDomainIdSimpleDomainCaseCreateItinerary(entity);
    }

    @Override
    public PageDTO<RegisterGroupDomain> queryAllSimpleDomainCaseListRegisterGroup(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterGroupEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterGroupEntity> results = mongoTemplate.find(query, RegisterGroupEntity.class);
        Page<RegisterGroupEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterGroup(resultsWithPage);
    }

    @Override
    public List<RegisterGroupDomain> queryAllSimpleDomainCaseListRegisterGroup(Query query) {
        List<RegisterGroupEntity> results = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllSimpleToDomainCaseListRegisterGroup(results);
    }

    public void updateIdSimpleDomainCaseDeleteItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterGroupEntity.class);
    }

    // Statistics

    @Override
    public Long countRegisterGroup(Query query) {
        Long result = mongoTemplate.count(query, RegisterGroupEntity.class);
        return result;
    }

    @Override
    public List<RegisterGroupEntity> aggregateFindRegisterGroupNotInCostSheetTwo() {

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("account_cost_sheet_two")
                .localField("registerGroup")
                .foreignField("_id")
                .as("joinedRegisterGroup");

        UnwindOperation unwindOperation = Aggregation.unwind("customerDetails", true);

        listCriteria.add(Criteria.where("joinedRegisterGroup").isNull());

        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, unwindOperation, matchOperation);

        return mongoTemplate.aggregate(aggregation, "booking_register_groups", RegisterGroupEntity.class).getMappedResults();
    }

    @Override
    public List<RegisterGroupDomain> findAllRegisterGroupForCostSheetTwo(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllSimpleToDomainCaseListRegisterGroupWithoutCostSheetTwo(items);
    }

    @Override
    public List<RegisterGroupDomain> queryAllAperture(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllCaseAperture(items);
    }

    @Override
    public List<RegisterGroupDomain> queryAllApertureDocumentFinance(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllCaseApertureDocumentFinance(items);
    }

    @Override
    public List<RegisterGroupDomain> queryAllApertureVoucherIngress(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllCaseApertureVoucherIngress(items);
    }

    @Override
    public List<RegisterGroupDomain> queryAllApertureVoucherEgress(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllCaseApertureVoucherEgress(items);
    }

    @Override
    public List<RegisterGroupDomain> queryAllApertureReport(Query query) {
        List<RegisterGroupEntity> items = mongoTemplate.find(query, RegisterGroupEntity.class);
        return converter.toDomainAllCaseApertureVoucherEgress(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUserDataModification(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterGroupEntity.class);
    }

    @Override
    public RegisterGroupDomain queryOneIdSimpleDomainCaseGetModificationUserData(Query query) {
        RegisterGroupEntity entity = mongoTemplate.findOne(query, RegisterGroupEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationUserData(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseCustomerModification(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterGroupEntity.class);
    }

    @Override
    public RegisterGroupDomain queryOneIdSimpleDomainCaseGetModificationCustomerData(Query query) {
        RegisterGroupEntity entity = mongoTemplate.findOne(query, RegisterGroupEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationCustomerData(entity);
    }
}
