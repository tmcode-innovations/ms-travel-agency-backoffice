package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringressdetail.VoucherIngressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail.IVoucherIngressDetailRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
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
public class VoucherIngressDetailRepoTempImpl implements IVoucherIngressDetailRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private VoucherIngressDetailConverter converter;

    @Override
    public PageDTO<VoucherIngressDetailDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherIngressDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherIngressDetailEntity> results = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        Page<VoucherIngressDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<VoucherIngressDetailDomain> query(Query query) {
        List<VoucherIngressDetailEntity> files = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        VoucherIngressDetailEntity entity = mongoTemplate.findOne(query, VoucherIngressDetailEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<VoucherIngressDetailEntity> items = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<VoucherIngressDetailDomain> queryAllSimpleDomainCaseShowVoucherIngressDetail(Query query) {
        List<VoucherIngressDetailEntity> items = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherIngressDetail(items);
    }

    @Override
    public PageDTO<VoucherIngressDetailDomain> queryAllSimpleDomainCaseListVoucherIngressDetail(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherIngressDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherIngressDetailEntity> results = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        Page<VoucherIngressDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListVoucherIngressDetail(resultsWithPage);
    }

    @Override
    public List<VoucherIngressDetailDomain> queryAllSimpleDomainCaseShowVoucherIngressDetailByRegisterGroup(Query query) {
        List<VoucherIngressDetailEntity> items = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherIngressDetailByRegisterGroup(items);
    }

    @Override
    public List<VoucherIngressDetailDomain> queryAllSimpleDomainCaseShowVoucherIngressDetailByDelete(Query query) {
        List<VoucherIngressDetailEntity> items = mongoTemplate.find(query, VoucherIngressDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherIngressDetailByDelete(items);
    }
}
