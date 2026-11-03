package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.registerdocumentvoucher.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.registerdocumentvoucher.RegisterDocumentVoucherConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.RegisterDocumentVoucherEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.registerdocumentvoucher.IRegisterDocumentVoucherRepoTemp;
import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterDocumentVoucherRepoTempImpl implements IRegisterDocumentVoucherRepoTemp {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RegisterDocumentVoucherConverter converter;

    @Override
    public PageDTO<RegisterDocumentVoucherDomain> query(Pageable pageable, Query query) {
        query.with(pageable);
        List<RegisterDocumentVoucherEntity> entities = mongoTemplate.find(query, RegisterDocumentVoucherEntity.class);
        Page<RegisterDocumentVoucherEntity> pageableEntities = new PageImpl<RegisterDocumentVoucherEntity>(entities, pageable, mongoTemplate.count(query, RegisterDocumentVoucherEntity.class));
        return converter.toDomainPage(pageableEntities);
    }
}
