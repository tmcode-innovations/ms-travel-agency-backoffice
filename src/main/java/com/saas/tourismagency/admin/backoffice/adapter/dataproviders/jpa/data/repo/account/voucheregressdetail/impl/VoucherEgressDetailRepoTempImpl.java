package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetail.VoucherEgressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.IVoucherEgressDetailRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherEgressDetailRepoTempImpl implements IVoucherEgressDetailRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private VoucherEgressDetailConverter converter;

    @Override
    public PageDTO<VoucherEgressDetailDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherEgressDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherEgressDetailEntity> results = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        Page<VoucherEgressDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<VoucherEgressDetailDomain> query(Query query) {
        List<VoucherEgressDetailEntity> files = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        VoucherEgressDetailEntity entity = mongoTemplate.findOne(query, VoucherEgressDetailEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<VoucherEgressDetailEntity> items = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseShowVoucherEgressDetail(Query query) {
        List<VoucherEgressDetailEntity> items = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherEgressDetail(items);
    }

    @Override
    public PageDTO<VoucherEgressDetailDomain> queryAllSimpleDomainCaseListVoucherEgressDetail(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, VoucherEgressDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<VoucherEgressDetailEntity> results = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        Page<VoucherEgressDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListVoucherEgressDetail(resultsWithPage);
    }

    @Override
    public VoucherEgressDetailDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        VoucherEgressDetailEntity entity = mongoTemplate.findOne(query, VoucherEgressDetailEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateVoucherEgressDetailBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, VoucherEgressDetailEntity.class);
    }

    @Override
    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseNoItemVoucherEgressDetail(Query query) {
        List<VoucherEgressDetailEntity> items = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseNoItemVoucherEgressDetail(items);
    }

    @Override
    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseShowVoucherEgressDetailByDelete(Query query) {
        List<VoucherEgressDetailEntity> items = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowVoucherEgressDetailByDelete(items);
    }

    @Override
    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseExportPdfByVoucherEgress(Query query) {
        List<VoucherEgressDetailEntity> items = mongoTemplate.find(query, VoucherEgressDetailEntity.class);
        return converter.toDomainAllSimpleDomainCaseExportPdfByVoucherEgress(items);
    }
}
