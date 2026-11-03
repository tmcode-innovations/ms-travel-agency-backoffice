package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.organization.OrganizationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.OrganizationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.IOrganizationRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
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
public class OrganizationRepositoryTemplateImpl implements IOrganizationRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private OrganizationConverter converter;

    @Override
    public PageDTO<OrganizationDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, OrganizationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<OrganizationEntity> results = mongoTemplate.find(query, OrganizationEntity.class);
        Page<OrganizationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }
}
