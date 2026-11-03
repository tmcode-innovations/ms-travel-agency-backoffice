package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguidedetail.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguidedetail.ExpenseGuideDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ExpenseGuideDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguidedetail.IExpenseGuideDetailRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseGuideDetailRepoTempImpl implements IExpenseGuideDetailRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ExpenseGuideDetailConverter converter;

    @Override
    public PageDTO<ExpenseGuideDetailDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ExpenseGuideDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ExpenseGuideDetailEntity> results = mongoTemplate.find(query, ExpenseGuideDetailEntity.class);
        Page<ExpenseGuideDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ExpenseGuideDetailDomain> query(Query query) {
        List<ExpenseGuideDetailEntity> entities = mongoTemplate.find(query, ExpenseGuideDetailEntity.class);
        return converter.toDomainAll(entities);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ExpenseGuideDetailEntity entity = mongoTemplate.findOne(query, ExpenseGuideDetailEntity.class);
        if (entity == null) {
            return null;
        }
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ExpenseGuideDetailEntity> entities = mongoTemplate.find(query, ExpenseGuideDetailEntity.class);
        return converter.toDomainAllSimple(entities);
    }
}
