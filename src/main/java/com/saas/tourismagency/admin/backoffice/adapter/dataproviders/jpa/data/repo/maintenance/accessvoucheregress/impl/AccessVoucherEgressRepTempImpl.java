package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.accessvoucheregress.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.accessvoucheregress.AccessVoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.AccessVoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.accessvoucheregress.IAccessVoucherEgressRepTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.AccessVoucherEgressDomain;
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
public class AccessVoucherEgressRepTempImpl implements IAccessVoucherEgressRepTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private AccessVoucherEgressConverter converter;

    @Override
    public PageDTO<AccessVoucherEgressDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AccessVoucherEgressEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AccessVoucherEgressEntity> results = mongoTemplate.find(query, AccessVoucherEgressEntity.class);
        Page<AccessVoucherEgressEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<AccessVoucherEgressDomain> query(Query query) {
        List<AccessVoucherEgressEntity> items = mongoTemplate.find(query, AccessVoucherEgressEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        AccessVoucherEgressEntity entity = mongoTemplate.findOne(query, AccessVoucherEgressEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<AccessVoucherEgressEntity> items = mongoTemplate.find(query, AccessVoucherEgressEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<AccessVoucherEgressDomain> queryAllSimpleDomain(Query query) {
        List<AccessVoucherEgressEntity> items = mongoTemplate.find(query, AccessVoucherEgressEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public PageDTO<AccessVoucherEgressDomain> queryAllSimpleDomainCaseListAccessVoucherEgress(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, AccessVoucherEgressEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<AccessVoucherEgressEntity> results = mongoTemplate.find(query, AccessVoucherEgressEntity.class);
        Page<AccessVoucherEgressEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListAccessVoucherEgress(resultsWithPage);
    }
}
