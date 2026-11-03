package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.profile.ProfileConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ProfileEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile.IProfileRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
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
public class ProfileRepositoryTemplateImpl implements IProfileRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProfileConverter converter;

    @Override
    public PageDTO<ProfileDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProfileEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProfileEntity> results = mongoTemplate.find(query, ProfileEntity.class);
        Page<ProfileEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProfileDomain> query(Query query) {
        List<ProfileEntity> items = mongoTemplate.find(query, ProfileEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProfileEntity entity = mongoTemplate.findOne(query, ProfileEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProfileEntity> items = mongoTemplate.find(query, ProfileEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ProfileDomain> queryAllSimpleDomainCaseListProfile(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProfileEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProfileEntity> results = mongoTemplate.find(query, ProfileEntity.class);
        Page<ProfileEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListProfile(resultsWithPage);
    }
}
