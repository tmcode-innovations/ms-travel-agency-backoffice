package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DetractionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetractionMongoRepo extends MongoRepository<DetractionEntity, String> {

    DetractionEntity findByNameAndTenant(String name, String tenant);

    DetractionEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

}
