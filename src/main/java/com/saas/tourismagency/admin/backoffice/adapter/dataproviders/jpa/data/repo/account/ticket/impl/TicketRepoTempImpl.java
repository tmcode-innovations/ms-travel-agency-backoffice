package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.ticket.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.ticket.TicketConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.TicketEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.ticket.ITicketRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.TicketDomain;
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
public class TicketRepoTempImpl implements ITicketRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TicketConverter converter;


    @Override
    public PageDTO<TicketDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TicketEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TicketEntity> results = mongoTemplate.find(query, TicketEntity.class);
        Page<TicketEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<TicketDomain> query(Query query) {
        List<TicketEntity> files = mongoTemplate.find(query, TicketEntity.class);
        return converter.toDomainAll(files);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        TicketEntity entity = mongoTemplate.findOne(query, TicketEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<TicketEntity> items = mongoTemplate.find(query, TicketEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, TicketEntity.class);
    }

    @Override
    public TicketDomain queryOneIdSimpleDomainCaseGetModificationData(Query query) {
        TicketEntity entity = mongoTemplate.findOne(query, TicketEntity.class);
        return converter.toDomainIdSimpleDomainCaseGetModificationData(entity);
    }

    @Override
    public PageDTO<TicketDomain> queryAllSimpleDomainCaseListTicket(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, TicketEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<TicketEntity> results = mongoTemplate.find(query, TicketEntity.class);
        Page<TicketEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseListTicket(resultsWithPage);
    }
}
