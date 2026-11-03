package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodging.RegisterBookingItemLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging.IRegisterBookingItemLodgingRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
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
public class RegisterBookingItemLodgingRepositoryTemplateImpl implements IRegisterBookingItemLodgingRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemLodgingConverter converter;

    @Override
    public PageDTO<RegisterBookingItemLodgingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemLodgingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemLodgingEntity> results = mongoTemplate.find(query, RegisterBookingItemLodgingEntity.class);
        Page<RegisterBookingItemLodgingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemLodgingDomain> query(Query query) {
        List<RegisterBookingItemLodgingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgingEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public List<RegisterBookingItemLodgingDomain> queryAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(Query query) {
        List<RegisterBookingItemLodgingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgingEntity.class);
        return converter.toDomainAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(items);
    }

    @Override
    public List<RegisterBookingItemLodgingDomain> queryAllRegisterBookingItemLodgingSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingItemLodgingEntity> items = mongoTemplate.find(query, RegisterBookingItemLodgingEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemLodgingEntity.class);
    }

}
