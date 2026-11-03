package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.accountpayment.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.accountpayment.AccountPaymentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.AccountPaymentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.accountpayment.IAccountPaymentRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
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
public class AccountPaymentRepoTempImpl implements IAccountPaymentRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private AccountPaymentConverter converter;

    @Override
    public PageDTO<AccountPaymentDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AccountPaymentEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AccountPaymentEntity> results = mongoTemplate.find(query, AccountPaymentEntity.class);
        Page<AccountPaymentEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<AccountPaymentDomain> query(Query query) {
        List<AccountPaymentEntity> files = mongoTemplate.find(query, AccountPaymentEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        AccountPaymentEntity entity = mongoTemplate.findOne(query, AccountPaymentEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<AccountPaymentEntity> items = mongoTemplate.find(query, AccountPaymentEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<AccountPaymentDomain> queryAllSimpleDomainCaseShowAccountPayment(Query query) {
        List<AccountPaymentEntity> items = mongoTemplate.find(query, AccountPaymentEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowAccountPayment(items);
    }

    @Override
    public PageDTO<AccountPaymentDomain> queryAllSimpleDomainCaseListAccountPayment(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AccountPaymentEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AccountPaymentEntity> results = mongoTemplate.find(query, AccountPaymentEntity.class);
        Page<AccountPaymentEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListAccountPayment(resultsWithPage);
    }

    @Override
    public List<AccountPaymentDomain> queryByProformaIdFullData(Query query) {
        List<AccountPaymentEntity> items = mongoTemplate.find(query, AccountPaymentEntity.class);
        return converter.toDomainAllByProformaIdFullData(items);
    }
}
