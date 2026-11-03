package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.transporttype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.transporttype.TransportTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TransportTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.transporttype.ITransportTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TransportTypeDomain;
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
public class TransportTypeRepositoryTemplateImpl implements ITransportTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TransportTypeConverter converter;

    @Override
    public PageDTO<TransportTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TransportTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TransportTypeEntity> results = mongoTemplate.find(query, TransportTypeEntity.class);
        Page<TransportTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<TransportTypeDomain> query(Query query) {
        List<TransportTypeEntity> items = mongoTemplate.find(query, TransportTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        TransportTypeEntity entity = mongoTemplate.findOne(query, TransportTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<TransportTypeEntity> items = mongoTemplate.find(query, TransportTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<TransportTypeDomain> queryAllSimpleDomainCaseListTransportType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TransportTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TransportTypeEntity> results = mongoTemplate.find(query, TransportTypeEntity.class);
        Page<TransportTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListTransportType(resultsWithPage);
    }
}
