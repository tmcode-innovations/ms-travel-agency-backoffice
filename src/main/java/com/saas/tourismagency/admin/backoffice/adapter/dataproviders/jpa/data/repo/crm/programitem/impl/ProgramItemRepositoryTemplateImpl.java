package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programitem.ProgramItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem.IProgramItemRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
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
public class ProgramItemRepositoryTemplateImpl implements IProgramItemRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProgramItemConverter converter;

    @Override
    public PageDTO<ProgramItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramItemEntity> results = mongoTemplate.find(query, ProgramItemEntity.class);
        Page<ProgramItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProgramItemDomain> query(Query query) {
        List<ProgramItemEntity> programItems = mongoTemplate.find(query, ProgramItemEntity.class);
        return converter.toDomainAll(programItems);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProgramItemEntity entity = mongoTemplate.findOne(query, ProgramItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProgramItemEntity> items = mongoTemplate.find(query, ProgramItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ProgramItemDomain> queryAllSimpleDomain(Query query) {
        List<ProgramItemEntity> items = mongoTemplate.find(query, ProgramItemEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<ProgramItemDomain> queryAllSimpleDomainCaseCreateItinerary(Query query) {
        List<ProgramItemEntity> items = mongoTemplate.find(query, ProgramItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseCreateItinerary(items);
    }

    @Override
    public List<ProgramItemDomain> queryAllSimpleDomainCaseDeleteTours(Query query) {
        List<ProgramItemEntity> items = mongoTemplate.find(query, ProgramItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteTours(items);
    }
}
