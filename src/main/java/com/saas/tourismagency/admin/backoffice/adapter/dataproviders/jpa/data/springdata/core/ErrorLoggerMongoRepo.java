package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.ErrorLoggerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLoggerMongoRepo extends MongoRepository<ErrorLoggerEntity, String> {

}
