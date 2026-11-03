package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformadetail.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformadetail.ProformaDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformadetail.IProformaDetailRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
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
public class ProformaDetailRepoTempImpl implements IProformaDetailRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProformaDetailConverter converter;

    @Override
    public PageDTO<ProformaDetailDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProformaDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProformaDetailEntity> results = mongoTemplate.find(query, ProformaDetailEntity.class);
        Page<ProformaDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProformaDetailDomain> query(Query query) {
        List<ProformaDetailEntity> files = mongoTemplate.find(query, ProformaDetailEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProformaDetailEntity entity = mongoTemplate.findOne(query, ProformaDetailEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProformaDetailEntity> items = mongoTemplate.find(query, ProformaDetailEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<ProformaDetailDomain> queryAllSimpleDomainCaseShowProformaDetail(Query query) {
        List<ProformaDetailEntity> items = mongoTemplate.find(query, ProformaDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowProformaDetail(items);
    }

    @Override
    public List<ProformaDetailDomain> queryAllSimpleDomainCaseShowNoItem(Query query) {
        List<ProformaDetailEntity> items = mongoTemplate.find(query, ProformaDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowNoItem(items);
    }
}
