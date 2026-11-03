package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.IProgramRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
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
public class ProgramRepositoryTemplateImpl implements IProgramRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProgramConverter converter;

    @Override
    public PageDTO<ProgramDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramEntity> results = mongoTemplate.find(query, ProgramEntity.class);
        Page<ProgramEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProgramDomain> query(Query query) {
        List<ProgramEntity> items = mongoTemplate.find(query, ProgramEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProgramEntity entity = mongoTemplate.findOne(query, ProgramEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProgramEntity> items = mongoTemplate.find(query, ProgramEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ProgramDomain> queryAllSimpleDomainCaseListProgram(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramEntity> results = mongoTemplate.find(query, ProgramEntity.class);
        Page<ProgramEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListProgram(resultsWithPage);
    }

    // Statistics

    @Override
    public Long countProgram(Query query) {
        Long result = mongoTemplate.count(query, ProgramEntity.class);
        return result;
    }

    @Override
    public List<ProgramDomain> queryAllSimpleWithDay(Query query) {
        List<ProgramEntity> items = mongoTemplate.find(query, ProgramEntity.class);
        return converter.toDomainAllSimpleWithDay(items);
    }
}
