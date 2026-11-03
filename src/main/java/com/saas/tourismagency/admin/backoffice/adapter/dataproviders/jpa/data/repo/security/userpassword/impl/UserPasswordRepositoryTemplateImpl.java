package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpassword.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpassword.UserPasswordConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpassword.IUserPasswordRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
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
public class UserPasswordRepositoryTemplateImpl implements IUserPasswordRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private UserPasswordConverter converter;

    @Override
    public PageDTO<UserPasswordDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, UserPasswordEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<UserPasswordEntity> results = mongoTemplate.find(query, UserPasswordEntity.class);
        Page<UserPasswordEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<UserPasswordDomain> queryBy(Query query) {
        List<UserPasswordEntity> userPasswords = mongoTemplate.find(query, UserPasswordEntity.class);
        return converter.toDomainAll(userPasswords);
    }
}
