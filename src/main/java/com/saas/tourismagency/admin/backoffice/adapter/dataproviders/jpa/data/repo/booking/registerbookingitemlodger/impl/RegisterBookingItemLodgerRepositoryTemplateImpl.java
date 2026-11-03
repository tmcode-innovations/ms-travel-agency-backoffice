package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodger.RegisterBookingItemLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.IRegisterBookingItemLodgerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerDomain;
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
public class RegisterBookingItemLodgerRepositoryTemplateImpl implements IRegisterBookingItemLodgerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemLodgerConverter converter;

    @Override
    public PageDTO<RegisterBookingItemLodgerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemLodgerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemLodgerEntity> results = mongoTemplate.find(query, RegisterBookingItemLodgerEntity.class);
        Page<RegisterBookingItemLodgerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemLodgerDomain> query(Query query) {
        List<RegisterBookingItemLodgerEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingItemLodgerEntity entity = mongoTemplate.findOne(query, RegisterBookingItemLodgerEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingItemLodgerEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<RegisterBookingItemLodgerDomain> queryAllSimpleDomain(Query query) {
        List<RegisterBookingItemLodgerEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<RegisterBookingItemLodgerDomain> queryAllRegisterBookingItemLodgerSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemLodgerEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgerEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemLodgerEntity.class);
    }
}
