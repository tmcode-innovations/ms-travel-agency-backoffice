package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationMongoRepo extends MongoRepository<QuotationEntity, String> {

    QuotationEntity findByNameAndTenant(String name, String tenant);

    QuotationEntity findByIdentifyAndTenant(String identify, String tenant);

    QuotationEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    QuotationEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
