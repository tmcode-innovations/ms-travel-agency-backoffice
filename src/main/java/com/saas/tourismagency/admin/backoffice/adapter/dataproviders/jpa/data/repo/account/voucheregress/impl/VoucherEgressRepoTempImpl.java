package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregress.VoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress.IVoucherEgressRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherEgressRepoTempImpl implements IVoucherEgressRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private VoucherEgressConverter converter;

    @Override
    public PageDTO<VoucherEgressDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherEgressEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherEgressEntity> results = mongoTemplate.find(query, VoucherEgressEntity.class);
        Page<VoucherEgressEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<VoucherEgressDomain> query(Query query) {
        List<VoucherEgressEntity> files = mongoTemplate.find(query, VoucherEgressEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        VoucherEgressEntity entity = mongoTemplate.findOne(query, VoucherEgressEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<VoucherEgressEntity> items = mongoTemplate.find(query, VoucherEgressEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<VoucherEgressDomain> queryAllSimpleDomainCaseShowVoucherEgress(Query query) {
        List<VoucherEgressEntity> items = mongoTemplate.find(query, VoucherEgressEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherEgress(items);
    }

    @Override
    public PageDTO<VoucherEgressDomain> queryAllSimpleDomainCaseListVoucherEgress(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherEgressEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherEgressEntity> results = mongoTemplate.find(query, VoucherEgressEntity.class);
        Page<VoucherEgressEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListVoucherEgress(resultsWithPage);
    }

    @Override
    public VoucherEgressDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        VoucherEgressEntity entity = mongoTemplate.findOne(query, VoucherEgressEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateVoucherEgressBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, VoucherEgressEntity.class);
    }

    @Override
    public List<VoucherEgressDomain> queryAllSimpleDomainCaseVoucherEgressByParent(Query query) {
        List<VoucherEgressEntity> items = mongoTemplate.find(query, VoucherEgressEntity.class);
        return converter.toDomainAllSimpleToDomainCaseVoucherEgressByParent(items);
    }

    @Override
    public List<VoucherEgressDomain> queryAllPending(Query query) {
        List<VoucherEgressEntity> items = mongoTemplate.find(query, VoucherEgressEntity.class);
        return converter.toDomainAllCasePending(items);
    }
}
