package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankMongoRepo extends MongoRepository<BankEntity, String> {

    BankEntity findByNameAndTenant(String name, String tenant);

    BankEntity findByIdentifyAndTenant(String identify, String tenant);

    BankEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    BankEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);

}
