package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingrestaurant.RegisterBookingRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.IRegisterBookingRestaurantRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRestaurantDomain;
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
public class RegisterBookingRestaurantRepTempImpl implements IRegisterBookingRestaurantRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingRestaurantConverter converter;

    @Override
    public PageDTO<RegisterBookingRestaurantDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingRestaurantEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingRestaurantEntity> results = mongoTemplate.find(query, RegisterBookingRestaurantEntity.class);
        Page<RegisterBookingRestaurantEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingRestaurantDomain> query(Query query) {
        List<RegisterBookingRestaurantEntity> items = mongoTemplate.find(query, RegisterBookingRestaurantEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingRestaurantEntity entity = mongoTemplate.findOne(query, RegisterBookingRestaurantEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingRestaurantEntity> items = mongoTemplate.find(query, RegisterBookingRestaurantEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RegisterBookingRestaurantDomain> queryAllSimpleDomainCaseListRegisterBookingRestaurant(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingRestaurantEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingRestaurantEntity> results = mongoTemplate.find(query, RegisterBookingRestaurantEntity.class);
        Page<RegisterBookingRestaurantEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterBookingRestaurant(resultsWithPage);
    }

    @Override
    public RegisterBookingRestaurantDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query) {
        RegisterBookingRestaurantEntity entity = mongoTemplate.findOne(query, RegisterBookingRestaurantEntity.class);
        return converter.toDomainIdSimpleDomainCaseValidationExistBooking(entity);
    }

    @Override
    public RegisterBookingRestaurantDomain queryOneRegisterBookingRestaurantSimpleDomainCaseAllCupon(Query query) {
        RegisterBookingRestaurantEntity entity = mongoTemplate.findOne(query, RegisterBookingRestaurantEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(entity);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingRestaurantEntity.class);
    }
}
