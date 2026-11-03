package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.FileItineraryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileItineraryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.IFileItineraryRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
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
public class FileItineraryRepositoryTemplateImpl implements IFileItineraryRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private FileItineraryConverter converter;

    @Override
    public PageDTO<FileItineraryDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, FileItineraryEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<FileItineraryEntity> results = mongoTemplate.find(query, FileItineraryEntity.class);
        Page<FileItineraryEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<FileItineraryDomain> query(Query query) {
        List<FileItineraryEntity> files = mongoTemplate.find(query, FileItineraryEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        FileItineraryEntity entity = mongoTemplate.findOne(query, FileItineraryEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<FileItineraryEntity> items = mongoTemplate.find(query, FileItineraryEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
