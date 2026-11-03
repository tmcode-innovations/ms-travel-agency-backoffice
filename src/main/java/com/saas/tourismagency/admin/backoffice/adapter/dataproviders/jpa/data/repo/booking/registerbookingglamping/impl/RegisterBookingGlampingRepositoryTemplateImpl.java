package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.IRegisterBookingGlamRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
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
public class RegisterBookingGlampingRepositoryTemplateImpl implements IRegisterBookingGlamRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingGlampingConverter converter;

    @Override
    public PageDTO<RegisterBookingGlampingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingGlampingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingGlampingEntity> results = mongoTemplate.find(query, RegisterBookingGlampingEntity.class);
        Page<RegisterBookingGlampingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingGlampingDomain> query(Query query) {
        List<RegisterBookingGlampingEntity> items = mongoTemplate.find(query, RegisterBookingGlampingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingGlampingEntity entity = mongoTemplate.findOne(query, RegisterBookingGlampingEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingGlampingEntity> items = mongoTemplate.find(query, RegisterBookingGlampingEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RegisterBookingGlampingDomain> queryAllSimpleDomainCaseListRegisterBookingGlamping(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingGlampingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingGlampingEntity> results = mongoTemplate.find(query, RegisterBookingGlampingEntity.class);
        Page<RegisterBookingGlampingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterBookingGlamping(resultsWithPage);
    }

    @Override
    public RegisterBookingGlampingDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query) {
        RegisterBookingGlampingEntity entity = mongoTemplate.findOne(query, RegisterBookingGlampingEntity.class);
        return converter.toDomainIdSimpleDomainCaseValidationExistBooking(entity);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingGlampingEntity.class);
    }
}
