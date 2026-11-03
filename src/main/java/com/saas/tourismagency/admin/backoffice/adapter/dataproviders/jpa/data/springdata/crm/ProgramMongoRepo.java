package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramMongoRepo extends MongoRepository<ProgramEntity, String> {

    ProgramEntity findByNameAndTenant(String name, String tenant);

    ProgramEntity findByIdentifyAndTenant(String identify, String tenant);

    ProgramEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ProgramEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
