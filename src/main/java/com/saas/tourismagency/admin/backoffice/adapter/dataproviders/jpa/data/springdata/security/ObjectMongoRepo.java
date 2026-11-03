package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ObjectEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectMongoRepo extends MongoRepository<ObjectEntity, String> {

    ObjectEntity findByNameAndTenant(String name, String tenant);

    ObjectEntity findByIdentifyAndTenant(String identify, String tenant);

    ObjectEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ObjectEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);

}
