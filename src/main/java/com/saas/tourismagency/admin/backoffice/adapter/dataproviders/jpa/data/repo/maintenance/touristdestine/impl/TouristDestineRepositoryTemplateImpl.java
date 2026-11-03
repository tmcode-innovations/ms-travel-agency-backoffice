package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceCategoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TouristDestineEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.ITouristDestineRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
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
public class TouristDestineRepositoryTemplateImpl implements ITouristDestineRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TouristDestineConverter converter;

    @Override
    public PageDTO<TouristDestineDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TouristDestineEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TouristDestineEntity> results = mongoTemplate.find(query, TouristDestineEntity.class);
        Page<TouristDestineEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<TouristDestineDomain> query(Query query) {
        List<TouristDestineEntity> items = mongoTemplate.find(query, TouristDestineEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        TouristDestineEntity entity = mongoTemplate.findOne(query, TouristDestineEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<TouristDestineEntity> items = mongoTemplate.find(query, TouristDestineEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<TouristDestineDomain> queryAllSimpleDomainCaseListTouristDestine(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TouristDestineEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TouristDestineEntity> results = mongoTemplate.find(query, TouristDestineEntity.class);
        Page<TouristDestineEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListTouristDestine(resultsWithPage);
    }
}
