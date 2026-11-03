package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.IRegisterBookingLodgingRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
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
public class RegisterBookingLodgingRepositoryTemplateImpl implements IRegisterBookingLodgingRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingLodgingConverter converter;

    @Override
    public PageDTO<RegisterBookingLodgingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingLodgingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingLodgingEntity> results = mongoTemplate.find(query, RegisterBookingLodgingEntity.class);
        Page<RegisterBookingLodgingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingLodgingDomain> query(Query query) {
        List<RegisterBookingLodgingEntity> items = mongoTemplate.find(query, RegisterBookingLodgingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingLodgingEntity entity = mongoTemplate.findOne(query, RegisterBookingLodgingEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingLodgingEntity> items = mongoTemplate.find(query, RegisterBookingLodgingEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RegisterBookingLodgingDomain> queryAllSimpleDomainCaseListRegisterBookingLodging(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingLodgingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingLodgingEntity> results = mongoTemplate.find(query, RegisterBookingLodgingEntity.class);
        Page<RegisterBookingLodgingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterBookingLodging(resultsWithPage);
    }

    @Override
    public RegisterBookingLodgingDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query) {
        RegisterBookingLodgingEntity entity = mongoTemplate.findOne(query, RegisterBookingLodgingEntity.class);
        return converter.toDomainIdSimpleDomainCaseValidationExistBooking(entity);
    }

    @Override
    public RegisterBookingLodgingDomain queryOneRegisterBookingLodgingSimpleDomainCaseAllCupon(Query query) {
        RegisterBookingLodgingEntity entity = mongoTemplate.findOne(query, RegisterBookingLodgingEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(entity);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingLodgingEntity.class);
    }
}
