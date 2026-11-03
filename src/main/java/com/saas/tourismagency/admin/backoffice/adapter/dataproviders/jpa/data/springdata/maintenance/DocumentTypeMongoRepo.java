package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeMongoRepo extends MongoRepository<DocumentTypeEntity, String> {

    DocumentTypeEntity findByNameAndTenant(String name, String tenant);

    DocumentTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    DocumentTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    DocumentTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
