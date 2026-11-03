package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemglamping.RegisterBookingItemGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping.IRegisterBookingItemGlamRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
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
public class RegisterBookingItemGlamRepositoryTemplateImpl implements IRegisterBookingItemGlamRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemGlampingConverter converter;

    @Override
    public PageDTO<RegisterBookingItemGlampingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemGlampingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemGlampingEntity> results = mongoTemplate.find(query, RegisterBookingItemGlampingEntity.class);
        Page<RegisterBookingItemGlampingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemGlampingDomain> query(Query query) {
        List<RegisterBookingItemGlampingEntity> items = mongoTemplate.find(query, RegisterBookingItemGlampingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemGlampingEntity.class);
    }
}
