package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinginstitution.RegisterBookingInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution.IRegisterBookingInstitutionRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;
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
public class RegisterBookingInstitutionRepTempImpl implements IRegisterBookingInstitutionRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingInstitutionConverter converter;

    @Override
    public PageDTO<RegisterBookingInstitutionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingInstitutionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingInstitutionEntity> results = mongoTemplate.find(query, RegisterBookingInstitutionEntity.class);
        Page<RegisterBookingInstitutionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingInstitutionDomain> query(Query query) {
        List<RegisterBookingInstitutionEntity> items = mongoTemplate.find(query, RegisterBookingInstitutionEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        RegisterBookingInstitutionEntity entity = mongoTemplate.findOne(query, RegisterBookingInstitutionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<RegisterBookingInstitutionEntity> items = mongoTemplate.find(query, RegisterBookingInstitutionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<RegisterBookingInstitutionDomain> queryAllSimpleDomainCaseListRegisterBookingInstitution(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingInstitutionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingInstitutionEntity> results = mongoTemplate.find(query, RegisterBookingInstitutionEntity.class);
        Page<RegisterBookingInstitutionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListRegisterBookingInstitution(resultsWithPage);
    }

    @Override
    public RegisterBookingInstitutionDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query) {
        RegisterBookingInstitutionEntity entity = mongoTemplate.findOne(query, RegisterBookingInstitutionEntity.class);
        return converter.toDomainIdSimpleDomainCaseValidationExistBooking(entity);
    }

    @Override
    public void updateChangeDateItineraryDate(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, RegisterBookingInstitutionEntity.class);
    }
}
