package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramTypeMongoRepo extends MongoRepository<ProgramTypeEntity, String> {

    ProgramTypeEntity findByNameAndTenant(String name, String tenant);

    ProgramTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    ProgramTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ProgramTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
