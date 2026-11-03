package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.notification.NotificationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NotificationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.INotificationRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
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
public class NotificationRepositoryTemplateImpl implements INotificationRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private NotificationConverter converter;

    @Override
    public PageDTO<NotificationDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, NotificationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<NotificationEntity> results = mongoTemplate.find(query, NotificationEntity.class);
        Page<NotificationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<NotificationDomain> query(Query query) {
        List<NotificationEntity> items = mongoTemplate.find(query, NotificationEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        NotificationEntity entity = mongoTemplate.findOne(query, NotificationEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<NotificationEntity> items = mongoTemplate.find(query, NotificationEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<NotificationDomain> queryAllSimpleDomainCaseGeneric(Query query) {
        List<NotificationEntity> items = mongoTemplate.find(query, NotificationEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGeneric(items);
    }

    @Override
    public PageDTO<NotificationDomain> queryAllSimpleDomainCaseListNotification(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, NotificationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<NotificationEntity> results = mongoTemplate.find(query, NotificationEntity.class);
        Page<NotificationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListNotification(resultsWithPage);
    }
}
