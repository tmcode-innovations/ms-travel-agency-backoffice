package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpasswordhistory.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpasswordhistory.UserPasswordHistoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordHistoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpasswordhistory.IUserPasswordHistoryRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
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
public class UserPasswordHistoryRepositoryTemplateImpl implements IUserPasswordHistoryRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private UserPasswordHistoryConverter converter;

    @Override
    public PageDTO<UserPasswordHistoryDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, UserPasswordHistoryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<UserPasswordHistoryEntity> results = mongoTemplate.find(query, UserPasswordHistoryEntity.class);
        Page<UserPasswordHistoryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<UserPasswordHistoryDomain> queryBy(Query query) {
        List<UserPasswordHistoryEntity> userPasswords = mongoTemplate.find(query, UserPasswordHistoryEntity.class);
        return converter.toDomainAll(userPasswords);
    }
}
