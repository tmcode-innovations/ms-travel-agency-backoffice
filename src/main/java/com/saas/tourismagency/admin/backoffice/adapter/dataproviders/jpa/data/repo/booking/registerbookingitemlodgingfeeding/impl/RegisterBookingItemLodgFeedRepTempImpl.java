package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingFeedingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.IRegisterBookingItemLodgingFeedingRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingFeedingDomain;
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
public class RegisterBookingItemLodgFeedRepTempImpl implements IRegisterBookingItemLodgingFeedingRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemLodgingFeedingConverter converter;

    @Override
    public PageDTO<RegisterBookingItemLodgingFeedingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemLodgingFeedingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemLodgingFeedingEntity> results = mongoTemplate.find(query, RegisterBookingItemLodgingFeedingEntity.class);
        Page<RegisterBookingItemLodgingFeedingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemLodgingFeedingDomain> query(Query query) {
        List<RegisterBookingItemLodgingFeedingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgingFeedingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public List<RegisterBookingItemLodgingFeedingDomain> queryAllRegisterBookingItemLodgingSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemLodgingFeedingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgingFeedingEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public List<RegisterBookingItemLodgingFeedingDomain> queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemLodgingFeedingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgingFeedingEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemLodgingFeedingEntity.class);
    }
}
