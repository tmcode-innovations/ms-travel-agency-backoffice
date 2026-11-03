package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMongoRepo extends MongoRepository<ProfileEntity, String> {

    ProfileEntity findByNameAndTenant(String name, String tenant);

    ProfileEntity findByIdentifyAndTenant(String identify, String tenant);

    ProfileEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ProfileEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);

}
