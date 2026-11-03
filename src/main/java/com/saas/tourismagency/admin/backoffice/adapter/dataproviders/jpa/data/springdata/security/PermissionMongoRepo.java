package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.PermissionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMongoRepo extends MongoRepository<PermissionEntity, String> {

    PermissionEntity findByNameAndTenant(String name, String tenant);

    PermissionEntity findByIdentifyAndTenant(String identify, String tenant);

    PermissionEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    PermissionEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);

}
