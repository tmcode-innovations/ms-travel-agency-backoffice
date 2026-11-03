package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ServicePqtTouristEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePqtTouristMongoRepo extends MongoRepository<ServicePqtTouristEntity, String> {

    ServicePqtTouristEntity findByNameAndTenant(String name, String tenant);

    ServicePqtTouristEntity findByIdentifyAndTenant(String identify, String tenant);

    ServicePqtTouristEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ServicePqtTouristEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
