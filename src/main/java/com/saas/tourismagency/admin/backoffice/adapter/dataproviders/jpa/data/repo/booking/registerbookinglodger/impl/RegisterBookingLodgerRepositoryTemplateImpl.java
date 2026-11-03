package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.IRegisterBookingLodgerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
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
public class RegisterBookingLodgerRepositoryTemplateImpl implements IRegisterBookingLodgerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingLodgerConverter converter;

    @Override
    public PageDTO<RegisterBookingLodgerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingLodgerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingLodgerEntity> results = mongoTemplate.find(query, RegisterBookingLodgerEntity.class);
        Page<RegisterBookingLodgerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingLodgerDomain> query(Query query) {
        List<RegisterBookingLodgerEntity> items = mongoTemplate.find(query, RegisterBookingLodgerEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingLodgerEntity entity = mongoTemplate.findOne(query, RegisterBookingLodgerEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingLodgerEntity> items = mongoTemplate.find(query, RegisterBookingLodgerEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RegisterBookingLodgerDomain> queryAllSimpleDomainCaseListRegisterBookingLodger(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingLodgerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingLodgerEntity> results = mongoTemplate.find(query, RegisterBookingLodgerEntity.class);
        Page<RegisterBookingLodgerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterBookingLodger(resultsWithPage);
    }

    @Override
    public RegisterBookingLodgerDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query) {
        RegisterBookingLodgerEntity entity = mongoTemplate.findOne(query, RegisterBookingLodgerEntity.class);
        return converter.toDomainIdSimpleDomainCaseValidationExistBooking(entity);
    }

    @Override
    public RegisterBookingLodgerDomain queryOneRegisterBookingLodgerSimpleDomainCaseAllCupon(Query query) {
        RegisterBookingLodgerEntity entity = mongoTemplate.findOne(query, RegisterBookingLodgerEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(entity);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingLodgerEntity.class);
    }
}
