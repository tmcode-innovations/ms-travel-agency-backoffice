package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.object.ObjectConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ObjectEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.IObjectRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
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
public class ObjectRepositoryTemplateImpl implements IObjectRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ObjectConverter converter;

    @Override
    public PageDTO<ObjectDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ObjectEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ObjectEntity> results = mongoTemplate.find(query, ObjectEntity.class);
        Page<ObjectEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ObjectDomain> query(Query query) {
        List<ObjectEntity> items = mongoTemplate.find(query, ObjectEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ObjectEntity entity = mongoTemplate.findOne(query, ObjectEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ObjectEntity> items = mongoTemplate.find(query, ObjectEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
