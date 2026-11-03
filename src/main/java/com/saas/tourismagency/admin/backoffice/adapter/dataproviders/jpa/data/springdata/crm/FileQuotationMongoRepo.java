package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileQuotationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileQuotationMongoRepo extends MongoRepository<FileQuotationEntity, String> {

    FileQuotationEntity findByNameAndTenant(String name, String tenant);

    FileQuotationEntity findByIdentifyAndTenant(String identify, String tenant);

    FileQuotationEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    FileQuotationEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
