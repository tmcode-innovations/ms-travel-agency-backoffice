package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PassengerTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerTypeMongoRepo extends MongoRepository<PassengerTypeEntity, String> {

    PassengerTypeEntity findByNameAndTenant(String name, String tenant);

    PassengerTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    PassengerTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    PassengerTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
