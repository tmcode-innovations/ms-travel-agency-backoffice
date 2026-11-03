package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.ITourRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
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
public class TourRepositoryTemplateImpl implements ITourRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TourConverter converter;

    @Override
    public PageDTO<TourDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TourEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TourEntity> results = mongoTemplate.find(query, TourEntity.class);
        Page<TourEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<TourDomain> query(Query query) {
        List<TourEntity> items = mongoTemplate.find(query, TourEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        TourEntity entity = mongoTemplate.findOne(query, TourEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<TourEntity> items = mongoTemplate.find(query, TourEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<TourDomain> queryAllSimpleDomainCaseListTour(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TourEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TourEntity> results = mongoTemplate.find(query, TourEntity.class);
        Page<TourEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListTour(resultsWithPage);
    }

    // Statistics

    @Override
    public Long countTour(Query query) {
        Long result = mongoTemplate.count(query, TourEntity.class);
        return result;
    }
}
