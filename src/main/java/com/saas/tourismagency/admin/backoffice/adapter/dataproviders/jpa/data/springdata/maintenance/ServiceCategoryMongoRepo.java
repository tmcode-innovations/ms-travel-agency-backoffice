package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceCategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCategoryMongoRepo extends MongoRepository<ServiceCategoryEntity, String> {

    ServiceCategoryEntity findByNameAndTenant(String name, String tenant);

    ServiceCategoryEntity findByIdentifyAndTenant(String identify, String tenant);

    ServiceCategoryEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ServiceCategoryEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
