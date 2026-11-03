package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.IPersonRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
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
public class PersonRepositoryTemplateImpl implements IPersonRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PersonConverter converter;

    @Override
    public PageDTO<PersonDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, PersonEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<PersonEntity> results = mongoTemplate.find(query, PersonEntity.class);
        Page<PersonEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<IdBaseDomain> queryIds(Query query) {
        query.fields().include("id");
        List<PersonEntity> results = mongoTemplate.find(query, PersonEntity.class);
        return results.stream().map(entity -> converter.toDomainIdBase(entity)).toList();
    }
}
