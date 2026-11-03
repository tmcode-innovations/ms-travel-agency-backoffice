package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ProvinceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.IProvinceRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
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
public class ProvinceRepositoryTemplateImpl implements IProvinceRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProvinceConverter converter;

    @Override
    public PageDTO<ProvinceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProvinceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProvinceEntity> results = mongoTemplate.find(query, ProvinceEntity.class);
        Page<ProvinceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProvinceDomain> query(Query query) {
        List<ProvinceEntity> provinces = mongoTemplate.find(query, ProvinceEntity.class);
        return converter.toDomainAll(provinces);
    }

    @Override
    public PageDTO<ProvinceDomain> queryAllSimpleDomainCaseListProvince(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProvinceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProvinceEntity> results = mongoTemplate.find(query, ProvinceEntity.class);
        Page<ProvinceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListProvince(resultsWithPage);
    }
}
