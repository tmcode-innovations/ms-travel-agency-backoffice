package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguide.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguide.ExpenseGuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ExpenseGuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguide.IExpenseGuideRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
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
public class ExpenseGuideRepoTempImpl implements IExpenseGuideRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ExpenseGuideConverter converter;

    @Override
    public PageDTO<ExpenseGuideDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ExpenseGuideEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ExpenseGuideEntity> results = mongoTemplate.find(query, ExpenseGuideEntity.class);
        Page<ExpenseGuideEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ExpenseGuideDomain> query(Query query) {
        List<ExpenseGuideEntity> files = mongoTemplate.find(query, ExpenseGuideEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ExpenseGuideEntity entity = mongoTemplate.findOne(query, ExpenseGuideEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ExpenseGuideEntity> items = mongoTemplate.find(query, ExpenseGuideEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<ExpenseGuideDomain> queryAllSimpleDomainCaseListExpenseGuide(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ExpenseGuideEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ExpenseGuideEntity> results = mongoTemplate.find(query, ExpenseGuideEntity.class);
        Page<ExpenseGuideEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListExpenseGuide(resultsWithPage);
    }

}
