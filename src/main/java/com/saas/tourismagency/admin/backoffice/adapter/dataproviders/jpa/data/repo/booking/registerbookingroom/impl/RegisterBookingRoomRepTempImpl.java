package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingroom.RegisterBookingRoomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRoomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.IRegisterBookingRoomRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRoomDomain;
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
public class RegisterBookingRoomRepTempImpl implements IRegisterBookingRoomRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterBookingRoomConverter converter;

    @Override
    public PageDTO<RegisterBookingRoomDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, RegisterBookingRoomEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<RegisterBookingRoomEntity> results = mongoTemplate.find(query, RegisterBookingRoomEntity.class);
        Page<RegisterBookingRoomEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<RegisterBookingRoomDomain> query(Query query) {
        List<RegisterBookingRoomEntity> items = mongoTemplate.find(query, RegisterBookingRoomEntity.class);
        return converter.toDomainAll(items);
    }
}
