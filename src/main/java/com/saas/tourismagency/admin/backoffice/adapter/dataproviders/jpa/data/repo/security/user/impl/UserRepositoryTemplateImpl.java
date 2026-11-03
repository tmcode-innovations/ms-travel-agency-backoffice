package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.IUserRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
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
public class UserRepositoryTemplateImpl implements IUserRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private UserConverter converter;

    @Override
    public PageDTO<UserDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, UserEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<UserEntity> results = mongoTemplate.find(query, UserEntity.class);
        Page<UserEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        UserEntity entity = mongoTemplate.findOne(query, UserEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<UserEntity> items = mongoTemplate.find(query, UserEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<UserDomain> queryAllSimpleDomainCaseListUser(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, UserEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<UserEntity> results = mongoTemplate.find(query, UserEntity.class);
        Page<UserEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListUser(resultsWithPage);
    }
}
