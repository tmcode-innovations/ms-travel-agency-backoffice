package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.changerequest.ChangeRequestCommentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestCommentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.IChangeRequestCommentRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestCommentDomain;
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
public class ChangeRequestCommentRepositoryTemplateImpl implements IChangeRequestCommentRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ChangeRequestCommentConverter converter;

    @Override
    public PageDTO<ChangeRequestCommentDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ChangeRequestCommentEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ChangeRequestCommentEntity> results = mongoTemplate.find(query, ChangeRequestCommentEntity.class);
        Page<ChangeRequestCommentEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ChangeRequestCommentDomain> query(Query query) {
        List<ChangeRequestCommentEntity> items = mongoTemplate.find(query, ChangeRequestCommentEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ChangeRequestCommentEntity entity = mongoTemplate.findOne(query, ChangeRequestCommentEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ChangeRequestCommentEntity> items = mongoTemplate.find(query, ChangeRequestCommentEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ChangeRequestCommentDomain> queryAllSimpleDomainCaseShowChangeRequestComment(Query query) {
        List<ChangeRequestCommentEntity> items = mongoTemplate.find(query, ChangeRequestCommentEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowChangeRequestComment(items);
    }
}
