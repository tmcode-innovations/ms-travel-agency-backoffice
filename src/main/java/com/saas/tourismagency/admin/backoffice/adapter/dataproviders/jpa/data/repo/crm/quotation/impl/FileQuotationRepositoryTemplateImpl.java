package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.FileQuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileQuotationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.IFileQuotationRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileQuotationDomain;
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
public class FileQuotationRepositoryTemplateImpl implements IFileQuotationRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private FileQuotationConverter converter;

    @Override
    public PageDTO<FileQuotationDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, FileQuotationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<FileQuotationEntity> results = mongoTemplate.find(query, FileQuotationEntity.class);
        Page<FileQuotationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<FileQuotationDomain> query(Query query) {
        List<FileQuotationEntity> files = mongoTemplate.find(query, FileQuotationEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        FileQuotationEntity entity = mongoTemplate.findOne(query, FileQuotationEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<FileQuotationEntity> items = mongoTemplate.find(query, FileQuotationEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
