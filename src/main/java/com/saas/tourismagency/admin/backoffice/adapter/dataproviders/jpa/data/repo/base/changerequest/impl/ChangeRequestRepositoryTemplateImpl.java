package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.changerequest.ChangeRequestConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.IChangeRequestRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class ChangeRequestRepositoryTemplateImpl implements IChangeRequestRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ChangeRequestConverter converter;

    @Override
    public PageDTO<ChangeRequestDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ChangeRequestEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ChangeRequestEntity> results = mongoTemplate.find(query, ChangeRequestEntity.class);
        Page<ChangeRequestEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ChangeRequestDomain> query(Query query) {
        List<ChangeRequestEntity> items = mongoTemplate.find(query, ChangeRequestEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ChangeRequestEntity entity = mongoTemplate.findOne(query, ChangeRequestEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ChangeRequestEntity> items = mongoTemplate.find(query, ChangeRequestEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ChangeRequestDomain> queryAllSimpleDomainCaseShowChangeRequest(Query query) {
        List<ChangeRequestEntity> items = mongoTemplate.find(query, ChangeRequestEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowChangeRequest(items);
    }

}
