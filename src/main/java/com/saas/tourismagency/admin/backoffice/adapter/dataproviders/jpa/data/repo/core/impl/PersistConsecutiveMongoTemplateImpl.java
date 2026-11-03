package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.PersistConsecutiveMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class PersistConsecutiveMongoTemplateImpl implements PersistConsecutiveMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersistConsecutiveNumberEntity query(Query query) {
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = mongoTemplate.findOne(query, PersistConsecutiveNumberEntity.class);
        return persistConsecutiveNumberEntity;
    }
}
