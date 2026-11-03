package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.touritem.TourItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.ITourItemRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
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
public class TourItemRepositoryTemplateImpl implements ITourItemRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TourItemConverter converter;

    @Override
    public PageDTO<TourItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TourItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TourItemEntity> results = mongoTemplate.find(query, TourItemEntity.class);
        Page<TourItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<TourItemDomain> query(Query query) {
        List<TourItemEntity> tourItems = mongoTemplate.find(query, TourItemEntity.class);
        return converter.toDomainAll(tourItems);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        TourItemEntity entity = mongoTemplate.findOne(query, TourItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<TourItemEntity> items = mongoTemplate.find(query, TourItemEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
