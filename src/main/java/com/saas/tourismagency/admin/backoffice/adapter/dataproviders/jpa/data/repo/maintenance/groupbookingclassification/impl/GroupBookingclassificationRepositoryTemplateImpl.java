package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.groupbookingclassification.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.groupbookingclassification.GroupBookingClassificationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GroupBookingClassificationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.groupbookingclassification.IGroupBookingClassificationRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GroupBookingClassificationDomain;
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
public class GroupBookingclassificationRepositoryTemplateImpl implements IGroupBookingClassificationRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private GroupBookingClassificationConverter converter;

    @Override
    public PageDTO<GroupBookingClassificationDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, GroupBookingClassificationEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<GroupBookingClassificationEntity> results = mongoTemplate.find(query, GroupBookingClassificationEntity.class);
        Page<GroupBookingClassificationEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<GroupBookingClassificationDomain> query(Query query) {
        List<GroupBookingClassificationEntity> items = mongoTemplate.find(query, GroupBookingClassificationEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        GroupBookingClassificationEntity entity = mongoTemplate.findOne(query, GroupBookingClassificationEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<GroupBookingClassificationEntity > items = mongoTemplate.find(query, GroupBookingClassificationEntity.class);
        return converter.toDomainAllSimple(items);
    }
}
