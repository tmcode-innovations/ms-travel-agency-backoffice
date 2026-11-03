package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.IDepartmentRegionRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class DepartmentRegionRepositoryTemplateImpl implements IDepartmentRegionRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DepartmentRegionConverter converter;

    @Override
    public PageDTO<DepartmentRegionDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DepartmentRegionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DepartmentRegionEntity> results = mongoTemplate.find(query, DepartmentRegionEntity.class);
        Page<DepartmentRegionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DepartmentRegionDomain> query(Query query) {
        List<DepartmentRegionEntity> departmentRegions = mongoTemplate.find(query, DepartmentRegionEntity.class);
        return converter.toDomainAll(departmentRegions);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DepartmentRegionEntity entity = mongoTemplate.findOne(query, DepartmentRegionEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DepartmentRegionEntity> items = mongoTemplate.find(query, DepartmentRegionEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<DepartmentRegionDomain> queryAllSimpleDomainCaseListDepartmentRegion(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DepartmentRegionEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DepartmentRegionEntity> results = mongoTemplate.find(query, DepartmentRegionEntity.class);
        Page<DepartmentRegionEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListDepartmentRegion(resultsWithPage);
    }
}
