package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemrestaurant.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemrestaurant.IRegisterBookingItemRestaurantRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
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
public class RegisterBookingItemRestaurantRepTempImpl implements IRegisterBookingItemRestaurantRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemRestaurantConverter converter;

    @Override
    public PageDTO<RegisterBookingItemRestaurantDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemRestaurantEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemRestaurantEntity> results = mongoTemplate.find(query, RegisterBookingItemRestaurantEntity.class);
        Page<RegisterBookingItemRestaurantEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemRestaurantDomain> query(Query query) {
        List<RegisterBookingItemRestaurantEntity> items = mongoTemplate.find(query, RegisterBookingItemRestaurantEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public List<RegisterBookingItemRestaurantDomain> queryAllRegisterBookingItemRestaurantSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemRestaurantEntity> items = mongoTemplate.find(query, RegisterBookingItemRestaurantEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemRestaurantEntity.class);
    }
}
