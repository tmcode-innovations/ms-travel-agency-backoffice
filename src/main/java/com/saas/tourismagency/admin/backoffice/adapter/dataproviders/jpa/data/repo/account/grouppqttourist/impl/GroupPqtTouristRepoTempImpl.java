package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.grouppqttourist.GroupPqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.GroupPqtTouristEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.IGroupPqtTouristRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
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
public class GroupPqtTouristRepoTempImpl implements IGroupPqtTouristRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private GroupPqtTouristConverter converter;

    @Override
    public PageDTO<GroupPqtTouristDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, GroupPqtTouristEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<GroupPqtTouristEntity> results = mongoTemplate.find(query, GroupPqtTouristEntity.class);
        Page<GroupPqtTouristEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public PageDTO<GroupPqtTouristDomain> queryAllSimpleDomainCaseListGroupPqtTourist(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, GroupPqtTouristEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<GroupPqtTouristEntity> results = mongoTemplate.find(query, GroupPqtTouristEntity.class);
        Page<GroupPqtTouristEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListGroupPqtTourist(resultsWithPage);
    }
}
