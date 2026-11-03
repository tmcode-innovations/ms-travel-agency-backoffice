package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging.IRegisterBookingItemLodgerLodgingRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
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
public class RegisterBookingItemLodgerLodRepTempImpl implements IRegisterBookingItemLodgerLodgingRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemLodgerLodgingConverter converter;

    @Override
    public PageDTO<RegisterBookingItemLodgerLodgingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemLodgerLodgingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemLodgerLodgingEntity> results = mongoTemplate.find(query, RegisterBookingItemLodgerLodgingEntity.class);
        Page<RegisterBookingItemLodgerLodgingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemLodgerLodgingDomain> query(Query query) {
        List<RegisterBookingItemLodgerLodgingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerLodgingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public List<RegisterBookingItemLodgerLodgingDomain> queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseOnluId(Query query) {
        List<RegisterBookingItemLodgerLodgingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerLodgingEntity.class);
        return converter.toDomainAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(items);
    }

    @Override
    public List<RegisterBookingItemLodgerLodgingDomain> queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemLodgerLodgingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerLodgingEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemLodgerLodgingEntity.class);
    }
}
