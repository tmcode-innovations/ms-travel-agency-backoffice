package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterGroupMongoRepo extends MongoRepository<RegisterGroupEntity, String> {

    RegisterGroupEntity findByNameAndTenant(String name, String tenant);

    RegisterGroupEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterGroupEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterGroupEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
