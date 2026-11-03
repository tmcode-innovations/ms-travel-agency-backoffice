package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileItineraryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileItineraryMongoRepo extends MongoRepository<FileItineraryEntity, String> {

    FileItineraryEntity findByNameAndTenant(String name, String tenant);

    FileItineraryEntity findByIdentifyAndTenant(String identify, String tenant);

    FileItineraryEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    FileItineraryEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
