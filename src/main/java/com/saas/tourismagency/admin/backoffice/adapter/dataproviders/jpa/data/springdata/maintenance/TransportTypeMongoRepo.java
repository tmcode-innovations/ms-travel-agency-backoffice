package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TransportTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeMongoRepo extends MongoRepository<TransportTypeEntity, String> {

    TransportTypeEntity findByNameAndTenant(String name, String tenant);

    TransportTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    TransportTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    TransportTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
