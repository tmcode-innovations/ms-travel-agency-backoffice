package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.company.CompanyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.CompanyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ICompanyRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
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
public class CompanyRepositoryTemplateImpl implements ICompanyRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private CompanyConverter converter;

    @Override
    public PageDTO<CompanyDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CompanyEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CompanyEntity> results = mongoTemplate.find(query, CompanyEntity.class);
        Page<CompanyEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public PageDTO<CompanyDomain> queryAllSimpleDomainCaseListCompany(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, CompanyEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<CompanyEntity> results = mongoTemplate.find(query, CompanyEntity.class);
        Page<CompanyEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListCompany(resultsWithPage);
    }
}
