package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programtype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programtype.ProgramTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programtype.IProgramTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
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
public class ProgramTypeRepositoryTemplateImpl implements IProgramTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProgramTypeConverter converter;

    @Override
    public PageDTO<ProgramTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramTypeEntity> results = mongoTemplate.find(query, ProgramTypeEntity.class);
        Page<ProgramTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProgramTypeDomain> query(Query query) {
        List<ProgramTypeEntity> items = mongoTemplate.find(query, ProgramTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProgramTypeEntity entity = mongoTemplate.findOne(query, ProgramTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProgramTypeEntity> items = mongoTemplate.find(query, ProgramTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ProgramTypeDomain> queryAllSimpleDomainCaseListProgramType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProgramTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProgramTypeEntity> results = mongoTemplate.find(query, ProgramTypeEntity.class);
        Page<ProgramTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListProgramType(resultsWithPage);
    }
}
