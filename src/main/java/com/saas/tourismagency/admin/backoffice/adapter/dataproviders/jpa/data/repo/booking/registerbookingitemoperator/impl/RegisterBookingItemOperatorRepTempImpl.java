package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemoperator.RegisterBookingItemOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.IRegisterBookingItemOperatorRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
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
public class RegisterBookingItemOperatorRepTempImpl implements IRegisterBookingItemOperatorRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemOperatorConverter converter;

    @Override
    public PageDTO<RegisterBookingItemOperatorDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemOperatorEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemOperatorEntity> results = mongoTemplate.find(query, RegisterBookingItemOperatorEntity.class);
        Page<RegisterBookingItemOperatorEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemOperatorDomain> query(Query query) {
        List<RegisterBookingItemOperatorEntity> items = mongoTemplate.find(query, RegisterBookingItemOperatorEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingItemOperatorEntity entity = mongoTemplate.findOne(query, RegisterBookingItemOperatorEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingItemOperatorEntity> items = mongoTemplate.find(query, RegisterBookingItemOperatorEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<RegisterBookingItemOperatorDomain> queryAllSimpleDomain(Query query) {
        List<RegisterBookingItemOperatorEntity> items = mongoTemplate.find(query, RegisterBookingItemOperatorEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<RegisterBookingItemOperatorDomain> queryAllSimpleDomainCaseOperationAvailable(Query query) {
        List<RegisterBookingItemOperatorEntity> items = mongoTemplate.find(query, RegisterBookingItemOperatorEntity.class);
        return converter.toDomainAllSimpleToDomainCaseOperationAvailable(items);
    }

    @Override
    public List<RegisterBookingItemOperatorDomain> queryAllRegisterBookingItemOperatorSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemOperatorEntity> items = mongoTemplate.find(query, RegisterBookingItemOperatorEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemOperatorEntity.class);
    }
}
