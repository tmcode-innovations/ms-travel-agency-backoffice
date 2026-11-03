package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringress.VoucherIngressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.IVoucherIngressRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class VoucherIngressRepoTempImpl implements IVoucherIngressRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private VoucherIngressConverter converter;

    @Override
    public PageDTO<VoucherIngressDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherIngressEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherIngressEntity> results = mongoTemplate.find(query, VoucherIngressEntity.class);
        Page<VoucherIngressEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<VoucherIngressDomain> query(Query query) {
        List<VoucherIngressEntity> files = mongoTemplate.find(query, VoucherIngressEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        VoucherIngressEntity entity = mongoTemplate.findOne(query, VoucherIngressEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<VoucherIngressEntity> items = mongoTemplate.find(query, VoucherIngressEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<VoucherIngressDomain> queryAllSimpleDomainCaseShowVoucherIngress(Query query) {
        List<VoucherIngressEntity> items = mongoTemplate.find(query, VoucherIngressEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherIngress(items);
    }

    @Override
    public PageDTO<VoucherIngressDomain> queryAllSimpleDomainCaseListVoucherIngress(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherIngressEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherIngressEntity> results = mongoTemplate.find(query, VoucherIngressEntity.class);
        Page<VoucherIngressEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListVoucherIngress(resultsWithPage);
    }

    @Override
    public VoucherIngressDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        VoucherIngressEntity entity = mongoTemplate.findOne(query, VoucherIngressEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateVoucherIngressBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, VoucherIngressEntity.class);
    }
}
