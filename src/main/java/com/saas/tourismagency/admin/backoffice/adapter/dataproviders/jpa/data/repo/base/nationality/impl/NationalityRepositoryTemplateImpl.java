package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NationalityEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.INationalityRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
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
public class NationalityRepositoryTemplateImpl implements INationalityRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private NationalityConverter converter;

    @Override
    public PageDTO<NationalityDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, NationalityEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<NationalityEntity> results = mongoTemplate.find(query, NationalityEntity.class);
        Page<NationalityEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<NationalityDomain> query(Query query) {
        List<NationalityEntity> items = mongoTemplate.find(query, NationalityEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        NationalityEntity entity = mongoTemplate.findOne(query, NationalityEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<NationalityEntity> items = mongoTemplate.find(query, NationalityEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public PageDTO<NationalityDomain> queryAllSimpleDomainCaseListNationality(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, NationalityEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<NationalityEntity> results = mongoTemplate.find(query, NationalityEntity.class);
        Page<NationalityEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListNationality(resultsWithPage);
    }
}
