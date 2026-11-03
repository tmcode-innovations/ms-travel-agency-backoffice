package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.permission.PermissionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.PermissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission.IPermissionRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
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
public class PermissionRepositoryTemplateImpl implements IPermissionRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PermissionConverter converter;

    @Override
    public PageDTO<PermissionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PermissionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PermissionEntity> results = mongoTemplate.find(query, PermissionEntity.class);
        Page<PermissionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<PermissionDomain> query(Query query) {
        List<PermissionEntity> items = mongoTemplate.find(query, PermissionEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        PermissionEntity entity = mongoTemplate.findOne(query, PermissionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<PermissionEntity> items = mongoTemplate.find(query, PermissionEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
