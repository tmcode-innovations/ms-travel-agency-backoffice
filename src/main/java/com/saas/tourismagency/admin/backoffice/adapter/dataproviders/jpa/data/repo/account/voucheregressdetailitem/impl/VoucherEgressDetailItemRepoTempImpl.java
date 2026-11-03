package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetailitem.VoucherEgressDetailItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.IVoucherEgressDetailItemRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailItemDomain;
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
public class VoucherEgressDetailItemRepoTempImpl implements IVoucherEgressDetailItemRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private VoucherEgressDetailItemConverter converter;

    @Override
    public PageDTO<VoucherEgressDetailItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherEgressDetailItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherEgressDetailItemEntity> results = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        Page<VoucherEgressDetailItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<VoucherEgressDetailItemDomain> query(Query query) {
        List<VoucherEgressDetailItemEntity> files = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        VoucherEgressDetailItemEntity entity = mongoTemplate.findOne(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<VoucherEgressDetailItemEntity> items = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseShowVoucherEgressDetailItem(Query query) {
        List<VoucherEgressDetailItemEntity> items = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherEgressDetailItem(items);
    }

    @Override
    public PageDTO<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseListVoucherEgressDetailItem(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherEgressDetailItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherEgressDetailItemEntity> results = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        Page<VoucherEgressDetailItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListVoucherEgressDetailItem(resultsWithPage);
    }

    @Override
    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseNoItemVoucherEgressDetailItem(Query query) {
        List<VoucherEgressDetailItemEntity> items = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseNoItemVoucherEgressDetailItem(items);
    }

    @Override
    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseShowVoucherEgressDetailItemByDelete(Query query) {
        List<VoucherEgressDetailItemEntity> items = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherEgressDetailItemByDelete(items);
    }

    @Override
    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseUpdateCalculateByRegisterGroup(Query query) {
        List<VoucherEgressDetailItemEntity> items = mongoTemplate.find(query, VoucherEgressDetailItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseUpdateCalculateByRegisterGroup(items);
    }
}
