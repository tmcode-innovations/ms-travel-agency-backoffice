package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.EntryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryMongoRepo extends MongoRepository<EntryEntity, String> {

    EntryEntity findByNameAndTenant(String name, String tenant);

    EntryEntity findByIdentifyAndTenant(String identify, String tenant);

    EntryEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    EntryEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
