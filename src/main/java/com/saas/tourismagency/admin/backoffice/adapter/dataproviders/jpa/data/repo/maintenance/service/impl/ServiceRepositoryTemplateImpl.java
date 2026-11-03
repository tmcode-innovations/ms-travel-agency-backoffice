package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.IServiceRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
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
public class ServiceRepositoryTemplateImpl implements IServiceRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ServiceConverter converter;

    @Override
    public PageDTO<ServiceDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ServiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ServiceEntity> results = mongoTemplate.find(query, ServiceEntity.class);
        Page<ServiceEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ServiceDomain> query(Query query) {
        List<ServiceEntity> items = mongoTemplate.find(query, ServiceEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ServiceEntity entity = mongoTemplate.findOne(query, ServiceEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ServiceEntity> items = mongoTemplate.find(query, ServiceEntity.class);
        return converter.toDomainAllSimple(items);
    }

    // Statistics

    @Override
    public Long countService(Query query) {
        Long result = mongoTemplate.count(query, ServiceEntity.class);
        return result;
    }

    @Override
    public List<ServiceDomain> queryAllSimpleDomainCaseToolUpdateAbbrev(Query query) {
        List<ServiceEntity> items = mongoTemplate.find(query, ServiceEntity.class);
        return converter.toDomainAllSimpleToDomainCaseToolUpdateAbbrev(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateToolAbbrev(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ServiceEntity.class);
    }

    @Override
    public PageDTO<ServiceDomain> queryAllSimpleDomainCaseListService(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ServiceEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ServiceEntity> results = mongoTemplate.find(query, ServiceEntity.class);
        Page<ServiceEntity> resultsWithPage = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListService(resultsWithPage);
    }
}
