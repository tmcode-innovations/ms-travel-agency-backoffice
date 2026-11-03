package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentAccountTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAccountTypeMongoRepo extends MongoRepository<DocumentAccountTypeEntity, String> {

    DocumentAccountTypeEntity findByNameAndTenant(String name, String tenant);

    DocumentAccountTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    DocumentAccountTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    DocumentAccountTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
