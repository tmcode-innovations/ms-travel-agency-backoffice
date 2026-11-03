package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.IDocumentTypeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
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
public class DocumentTypeRepositoryTemplateImpl implements IDocumentTypeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DocumentTypeConverter converter;

    @Override
    public PageDTO<DocumentTypeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DocumentTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DocumentTypeEntity> results = mongoTemplate.find(query, DocumentTypeEntity.class);
        Page<DocumentTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DocumentTypeDomain> query(Query query) {
        List<DocumentTypeEntity> items = mongoTemplate.find(query, DocumentTypeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DocumentTypeEntity entity = mongoTemplate.findOne(query, DocumentTypeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DocumentTypeEntity > items = mongoTemplate.find(query, DocumentTypeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<DocumentTypeDomain> queryAllSimpleDomainCaseListDocumentType(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DocumentTypeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DocumentTypeEntity> results = mongoTemplate.find(query, DocumentTypeEntity.class);
        Page<DocumentTypeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDocumentType(resultsWithPage);
    }
}
