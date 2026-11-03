package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.StateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.IStateRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.StateDomain;
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
public class StateRepositoryTemplateImpl implements IStateRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private StateConverter converter;

    @Override
    public PageDTO<StateDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, StateEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<StateEntity> results = mongoTemplate.find(query, StateEntity.class);
        Page<StateEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<StateDomain> query(Query query) {
        List<StateEntity> states = mongoTemplate.find(query, StateEntity.class);
        return converter.toDomainAll(states);
    }

    @Override
    public PageDTO<StateDomain> queryAllSimpleDomainCaseListState(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, StateEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<StateEntity> results = mongoTemplate.find(query, StateEntity.class);
        Page<StateEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListState(resultsWithPage);
    }
}
