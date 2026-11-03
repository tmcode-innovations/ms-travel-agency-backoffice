package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingbase.RegisterBookingBaseConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.IRegisterBookingBaseRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
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
public class RegisterBookingBaseRepositoryTemplateImpl implements IRegisterBookingBaseRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingBaseConverter converter;

    @Override
    public PageDTO<RegisterBookingBaseDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingBaseEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingBaseEntity> results = mongoTemplate.find(query, RegisterBookingBaseEntity.class);
        Page<RegisterBookingBaseEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingBaseDomain> query(Query query) {
        List<RegisterBookingBaseEntity> items = mongoTemplate.find(query, RegisterBookingBaseEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public List<RegisterBookingBaseDomain> queryAllRegisterBookingBaseAndActiveSimpleDomainCaseAllCupon(Query query) {
        List<RegisterBookingBaseEntity> items = mongoTemplate.find(query, RegisterBookingBaseEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllCupon(items);
    }

    @Override
    public List<RegisterBookingBaseDomain> queryAllByRegisterGroupAndActiveSimpleDomainCaseAnnulled(Query query) {
        List<RegisterBookingBaseEntity> items = mongoTemplate.find(query, RegisterBookingBaseEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAnnulled(items);
    }

    @Override
    public void updateStateBookingAndActiveRegisterBookingIdSimpleDomainCaseAnnulled(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingBaseEntity.class);
    }

    @Override
    public List<RegisterBookingBaseDomain> queryAllRegisterBookingBaseAndActiveSimpleDomainCaseAllGenerateBooking(Query query) {
        List<RegisterBookingBaseEntity> items = mongoTemplate.find(query, RegisterBookingBaseEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCaseAllGenerateBooking(items);
    }

}
