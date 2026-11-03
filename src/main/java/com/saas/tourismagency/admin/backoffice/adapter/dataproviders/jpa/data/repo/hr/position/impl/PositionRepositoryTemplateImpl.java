package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.position.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.position.PositionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.PositionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.position.IPositionRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
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
public class PositionRepositoryTemplateImpl implements IPositionRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PositionConverter converter;

    @Override
    public PageDTO<PositionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PositionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PositionEntity> results = mongoTemplate.find(query, PositionEntity.class);
        Page<PositionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public PageDTO<PositionDomain> queryAllSimpleDomainCaseListPosition(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PositionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PositionEntity> results = mongoTemplate.find(query, PositionEntity.class);
        Page<PositionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListPosition(resultsWithPage);
    }
}
