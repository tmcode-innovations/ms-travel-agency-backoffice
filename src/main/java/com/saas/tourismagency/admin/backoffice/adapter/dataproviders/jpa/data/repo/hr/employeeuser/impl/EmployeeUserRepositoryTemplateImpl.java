package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employeeuser.EmployeeUserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeUserEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.IEmployeeUserRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
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
public class EmployeeUserRepositoryTemplateImpl implements IEmployeeUserRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private EmployeeUserConverter converter;

    @Override
    public PageDTO<EmployeeUserDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, EmployeeUserEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<EmployeeUserEntity> results = mongoTemplate.find(query, EmployeeUserEntity.class);
        Page<EmployeeUserEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<EmployeeUserDomain> query(Query query) {
        List<EmployeeUserEntity> EmployeeUsers = mongoTemplate.find(query, EmployeeUserEntity.class);
        return converter.toDomainAll(EmployeeUsers);
    }
}
