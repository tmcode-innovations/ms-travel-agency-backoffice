package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengerbooking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengerbooking.PassengerBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerBookingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengerbooking.IPassengerBookingRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerBookingRepositoryTemplateImpl implements IPassengerBookingRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PassengerBookingConverter converter;

    @Override
    public PageDTO<PassengerBookingDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PassengerBookingEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PassengerBookingEntity> results = mongoTemplate.find(query, PassengerBookingEntity.class);
        Page<PassengerBookingEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<PassengerBookingDomain> query(Query query) {
        List<PassengerBookingEntity> passengers = mongoTemplate.find(query, PassengerBookingEntity.class);
        return converter.toDomainAll(passengers);
    }
}
