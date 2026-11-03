package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documentaccounttype.DocumentAccountTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentAccountTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.IDocumentAccountTypeRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
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
public class DocumentAccountTypeRepoTempImpl implements IDocumentAccountTypeRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DocumentAccountTypeConverter converter;

    @Override
    public PageDTO<DocumentAccountTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DocumentAccountTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DocumentAccountTypeEntity> results = mongoTemplate.find(query, DocumentAccountTypeEntity.class);
        Page<DocumentAccountTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DocumentAccountTypeDomain> query(Query query) {
        List<DocumentAccountTypeEntity> items = mongoTemplate.find(query, DocumentAccountTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DocumentAccountTypeEntity entity = mongoTemplate.findOne(query, DocumentAccountTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DocumentAccountTypeEntity> items = mongoTemplate.find(query, DocumentAccountTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<DocumentAccountTypeDomain> queryAllSimpleDomainCaseListDocumentAccountType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DocumentAccountTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DocumentAccountTypeEntity> results = mongoTemplate.find(query, DocumentAccountTypeEntity.class);
        Page<DocumentAccountTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDocumentAccountType(resultsWithPage);
    }
}
