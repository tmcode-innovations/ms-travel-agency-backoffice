package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tourdate.TourDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.ITourDateRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
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
public class TourDateRepositoryTemplateImpl implements ITourDateRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TourDateConverter converter;

    @Override
    public PageDTO<TourDateDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TourDateEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TourDateEntity> results = mongoTemplate.find(query, TourDateEntity.class);
        Page<TourDateEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<TourDateDomain> query(Query query) {
        List<TourDateEntity> tourDates = mongoTemplate.find(query, TourDateEntity.class);
        return converter.toDomainAll(tourDates);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        TourDateEntity entity = mongoTemplate.findOne(query, TourDateEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<TourDateEntity> items = mongoTemplate.find(query, TourDateEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
