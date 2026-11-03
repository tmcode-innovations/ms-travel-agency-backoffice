package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistConsecutiveNumberMongoRepo extends MongoRepository<PersistConsecutiveNumberEntity, String> {


}
