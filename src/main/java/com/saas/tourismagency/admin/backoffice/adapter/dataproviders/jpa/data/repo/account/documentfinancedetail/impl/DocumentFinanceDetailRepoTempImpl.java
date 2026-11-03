package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinancedetail.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinancedetail.DocumentFinanceDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinancedetail.IDocumentFinanceDetailRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
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
public class DocumentFinanceDetailRepoTempImpl implements IDocumentFinanceDetailRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DocumentFinanceDetailConverter converter;

    @Override
    public PageDTO<DocumentFinanceDetailDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, DocumentFinanceDetailEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<DocumentFinanceDetailEntity> results = mongoTemplate.find(query, DocumentFinanceDetailEntity.class);
        Page<DocumentFinanceDetailEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<DocumentFinanceDetailDomain> query(Query query) {
        List<DocumentFinanceDetailEntity> files = mongoTemplate.find(query, DocumentFinanceDetailEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        DocumentFinanceDetailEntity entity = mongoTemplate.findOne(query, DocumentFinanceDetailEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<DocumentFinanceDetailEntity> items = mongoTemplate.find(query, DocumentFinanceDetailEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<DocumentFinanceDetailDomain> queryAllSimpleDomainCaseShowDocumentFinanceDetail(Query query) {
        List<DocumentFinanceDetailEntity> items = mongoTemplate.find(query, DocumentFinanceDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowDocumentFinanceDetail(items);
    }

    @Override
    public List<DocumentFinanceDetailDomain> queryAllSimpleDomainCaseShowNoItem(Query query) {
        List<DocumentFinanceDetailEntity> items = mongoTemplate.find(query, DocumentFinanceDetailEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowNoItem(items);
    }
}
