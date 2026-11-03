package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.app.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.app.AppConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.AppEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.app.IAppRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
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
public class AppRepositoryTemplateImpl implements IAppRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private AppConverter converter;

    @Override
    public PageDTO<AppDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AppEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AppEntity> results = mongoTemplate.find(query, AppEntity.class);
        Page<AppEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<AppDomain> query(Query query) {
        List<AppEntity> items = mongoTemplate.find(query, AppEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        AppEntity entity = mongoTemplate.findOne(query, AppEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<AppEntity> items = mongoTemplate.find(query, AppEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
