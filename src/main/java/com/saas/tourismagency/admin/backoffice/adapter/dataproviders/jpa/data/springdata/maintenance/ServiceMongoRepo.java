package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMongoRepo extends MongoRepository<ServiceEntity, String> {

    ServiceEntity findByNameAndTenant(String name, String tenant);

    ServiceEntity findByIdentifyAndTenant(String identify, String tenant);

    ServiceEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ServiceEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
