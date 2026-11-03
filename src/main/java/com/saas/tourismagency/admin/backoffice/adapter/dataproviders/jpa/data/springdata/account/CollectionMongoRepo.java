package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionMongoRepo extends MongoRepository<CollectionEntity, String> {


}
