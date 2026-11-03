package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingiteminstitution.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingiteminstitution.IRegisterBookingItemInstitutionRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemInstitutionDomain;
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
public class RegisterBookingItemInstitutionRepTempImpl implements IRegisterBookingItemInstitutionRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingItemInstitutionConverter converter;

    @Override
    public PageDTO<RegisterBookingItemInstitutionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingItemInstitutionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingItemInstitutionEntity> results = mongoTemplate.find(query, RegisterBookingItemInstitutionEntity.class);
        Page<RegisterBookingItemInstitutionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingItemInstitutionDomain> query(Query query) {
        List<RegisterBookingItemInstitutionEntity> items = mongoTemplate.find(query, RegisterBookingItemInstitutionEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingItemInstitutionEntity entity = mongoTemplate.findOne(query, RegisterBookingItemInstitutionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingItemInstitutionEntity> items = mongoTemplate.find(query, RegisterBookingItemInstitutionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<RegisterBookingItemInstitutionDomain> queryAllSimpleDomain(Query query) {
        List<RegisterBookingItemInstitutionEntity> items = mongoTemplate.find(query, RegisterBookingItemInstitutionEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<RegisterBookingItemInstitutionDomain> queryAllSimpleDomainCaseOperationAvailable(Query query) {
        List<RegisterBookingItemInstitutionEntity> items = mongoTemplate.find(query, RegisterBookingItemInstitutionEntity.class);
        return converter.toDomainAllSimpleToDomainCaseOperationAvailable(items);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingItemInstitutionEntity.class);
    }
}
