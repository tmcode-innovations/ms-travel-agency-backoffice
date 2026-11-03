package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.IRegisterBookingOperatorRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
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
public class RegisterBookingOperatorRepTempImpl implements IRegisterBookingOperatorRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingOperatorConverter converter;

    @Override
    public PageDTO<RegisterBookingOperatorDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingOperatorEntity> results = mongoTemplate.find(query, RegisterBookingOperatorEntity.class);
        Page<RegisterBookingOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingOperatorDomain> query(Query query) {
        List<RegisterBookingOperatorEntity> items = mongoTemplate.find(query, RegisterBookingOperatorEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingOperatorEntity entity = mongoTemplate.findOne(query, RegisterBookingOperatorEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingOperatorEntity> items = mongoTemplate.find(query, RegisterBookingOperatorEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RegisterBookingOperatorDomain> queryAllSimpleDomainCaseListRegisterBookingOperator(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingOperatorEntity> results = mongoTemplate.find(query, RegisterBookingOperatorEntity.class);
        Page<RegisterBookingOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterBookingOperator(resultsWithPage);
    }

    @Override
    public RegisterBookingOperatorDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query) {
        RegisterBookingOperatorEntity entity = mongoTemplate.findOne(query, RegisterBookingOperatorEntity.class);
        return converter.toDomainIdSimpleDomainCaseValidationExistBooking(entity);
    }

    @Override
    public RegisterBookingOperatorDomain queryOneRegisterBookingOperatorSimpleDomainCaseAllCupon(Query query) {
        RegisterBookingOperatorEntity entity = mongoTemplate.findOne(query, RegisterBookingOperatorEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(entity);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingOperatorEntity.class);
    }
}
