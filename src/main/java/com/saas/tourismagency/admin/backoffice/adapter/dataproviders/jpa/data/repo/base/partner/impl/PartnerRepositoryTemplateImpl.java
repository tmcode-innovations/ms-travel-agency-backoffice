package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.IPartnerRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
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
public class PartnerRepositoryTemplateImpl implements IPartnerRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PartnerConverter converter;

    @Override
    public PageDTO<PartnerDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PartnerEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PartnerEntity> results = mongoTemplate.find(query, PartnerEntity.class);
        Page<PartnerEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }
}
