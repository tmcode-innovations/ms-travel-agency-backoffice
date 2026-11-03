package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.IEmployeeRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.SimpleEmployeeDomain;
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
public class EmployeeRepositoryTemplateImpl implements IEmployeeRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private EmployeeConverter converter;

    @Override
    public PageDTO<EmployeeDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, EmployeeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<EmployeeEntity> results = mongoTemplate.find(query, EmployeeEntity.class);
        Page<EmployeeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<EmployeeDomain> query(Query query) {
        List<EmployeeEntity> items = mongoTemplate.find(query, EmployeeEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        EmployeeEntity entity = mongoTemplate.findOne(query, EmployeeEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<EmployeeEntity> items = mongoTemplate.find(query, EmployeeEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<SimpleEmployeeDomain> queryAllSimpleWithConditionalArea(Query query) {
        List<EmployeeEntity> items = mongoTemplate.find(query, EmployeeEntity.class);
        return converter.toDomainAllSimpleWithConditionalArea(items);
    }

    @Override
    public PageDTO<EmployeeDomain> queryAllSimpleDomainCaseListEmployee(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, EmployeeEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<EmployeeEntity> results = mongoTemplate.find(query, EmployeeEntity.class);
        Page<EmployeeEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListEmployee(resultsWithPage);
    }
}
