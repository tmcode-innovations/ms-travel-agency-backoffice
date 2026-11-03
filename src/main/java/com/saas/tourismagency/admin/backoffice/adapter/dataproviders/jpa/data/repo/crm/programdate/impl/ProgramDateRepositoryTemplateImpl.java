package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programdate.ProgramDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.IProgramDateRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
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
public class ProgramDateRepositoryTemplateImpl implements IProgramDateRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProgramDateConverter converter;

    @Override
    public PageDTO<ProgramDateDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramDateEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramDateEntity> results = mongoTemplate.find(query, ProgramDateEntity.class);
        Page<ProgramDateEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProgramDateDomain> query(Query query) {
        List<ProgramDateEntity> programDates = mongoTemplate.find(query, ProgramDateEntity.class);
        return converter.toDomainAll(programDates);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProgramDateEntity entity = mongoTemplate.findOne(query, ProgramDateEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProgramDateEntity> items = mongoTemplate.find(query, ProgramDateEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ProgramDateDomain> queryAllSimpleDomain(Query query) {
        List<ProgramDateEntity> items = mongoTemplate.find(query, ProgramDateEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public void updateToursByIdSimpleDomainCaseDeleteTour(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ProgramDateEntity.class);
    }

    @Override
    public List<ProgramDateDomain> queryAllSimpleDomainCaseDeleteTour(Query query) {
        List<ProgramDateEntity> items = mongoTemplate.find(query, ProgramDateEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteTour(items);
    }
}
