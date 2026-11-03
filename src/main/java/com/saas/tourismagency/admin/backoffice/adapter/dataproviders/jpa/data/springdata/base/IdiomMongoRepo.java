package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomMongoRepo extends MongoRepository<IdiomEntity, String> {

    IdiomEntity findByNameAndTenant(String name, String tenant);

    IdiomEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

}
