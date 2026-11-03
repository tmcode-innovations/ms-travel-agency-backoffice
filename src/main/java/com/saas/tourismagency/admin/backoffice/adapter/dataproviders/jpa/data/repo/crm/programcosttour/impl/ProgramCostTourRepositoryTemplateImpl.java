package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programcosttour.ProgramCostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramCostTourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.ProgramCostTourRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramCostTourDomain;
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
public class ProgramCostTourRepositoryTemplateImpl implements ProgramCostTourRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProgramCostTourConverter converter;

    @Override
    public PageDTO<ProgramCostTourDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramCostTourEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramCostTourEntity> results = mongoTemplate.find(query, ProgramCostTourEntity.class);
        Page<ProgramCostTourEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProgramCostTourDomain> query(Query query) {
        List<ProgramCostTourEntity> costTours = mongoTemplate.find(query, ProgramCostTourEntity.class);
        return converter.toDomainAll(costTours);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProgramCostTourEntity entity = mongoTemplate.findOne(query, ProgramCostTourEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProgramCostTourEntity> items = mongoTemplate.find(query, ProgramCostTourEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ProgramCostTourDomain> queryAllSimpleDomainCaseShowProgramCostTour(Query query) {
        List<ProgramCostTourEntity> items = mongoTemplate.find(query, ProgramCostTourEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowProgramCostTour(items);
    }
}
