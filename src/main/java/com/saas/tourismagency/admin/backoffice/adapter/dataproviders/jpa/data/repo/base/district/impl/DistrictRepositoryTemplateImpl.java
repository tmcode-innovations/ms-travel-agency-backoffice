package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DistrictEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.IDistrictRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
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
public class DistrictRepositoryTemplateImpl implements IDistrictRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DistrictConverter converter;

    @Override
    public PageDTO<DistrictDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DistrictEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DistrictEntity> results = mongoTemplate.find(query, DistrictEntity.class);
        Page<DistrictEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DistrictDomain> query(Query query) {
        List<DistrictEntity> districts = mongoTemplate.find(query, DistrictEntity.class);
        return converter.toDomainAll(districts);
    }

    @Override
    public PageDTO<DistrictDomain> queryAllSimpleDomainCaseListDistrict(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DistrictEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DistrictEntity> results = mongoTemplate.find(query, DistrictEntity.class);
        Page<DistrictEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDistrict(resultsWithPage);
    }
}
