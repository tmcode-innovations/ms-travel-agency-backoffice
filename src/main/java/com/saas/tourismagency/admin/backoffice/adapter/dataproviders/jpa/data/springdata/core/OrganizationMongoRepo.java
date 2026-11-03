package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.OrganizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationMongoRepo extends MongoRepository<OrganizationEntity, String> {

    OrganizationEntity findByName(String name);

    OrganizationEntity findByIdentify(String identify);

    OrganizationEntity findByNameAndTenant(String name, String tenant);

    OrganizationEntity findByIdentifyAndTenant(String identify, String tenant);
}
