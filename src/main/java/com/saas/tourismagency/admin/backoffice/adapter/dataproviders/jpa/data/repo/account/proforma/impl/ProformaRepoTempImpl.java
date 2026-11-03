package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proforma.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proforma.ProformaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proforma.IProformaRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
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
public class ProformaRepoTempImpl implements IProformaRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ProformaConverter converter;

    @Override
    public PageDTO<ProformaDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ProformaEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ProformaEntity> results = mongoTemplate.find(query, ProformaEntity.class);
        Page<ProformaEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ProformaDomain> query(Query query) {
        List<ProformaEntity> entities = mongoTemplate.find(query, ProformaEntity.class);
        return converter.toDomainAll(entities);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ProformaEntity entity = mongoTemplate.findOne(query, ProformaEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ProformaEntity> items = mongoTemplate.find(query, ProformaEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateAmortizationById(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ProformaEntity.class);
    }
}
