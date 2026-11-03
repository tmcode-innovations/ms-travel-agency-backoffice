package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.errorlogger.ErrorLoggerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.ErrorLoggerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.IErrorLoggerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.core.ErrorLoggerDomain;
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
public class ErrorLoggerRepositoryTemplateImpl implements IErrorLoggerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ErrorLoggerConverter converter;

    @Override
    public PageDTO<ErrorLoggerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ErrorLoggerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ErrorLoggerEntity> results = mongoTemplate.find(query, ErrorLoggerEntity.class);
        Page<ErrorLoggerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }
}
