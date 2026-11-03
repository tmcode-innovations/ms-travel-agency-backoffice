package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.AppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppMongoRepo extends MongoRepository<AppEntity, String> {

    AppEntity findByNameAndTenant(String name, String tenant);

    AppEntity findByIdentifyAndTenant(String identify, String tenant);

    AppEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    AppEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);

}
