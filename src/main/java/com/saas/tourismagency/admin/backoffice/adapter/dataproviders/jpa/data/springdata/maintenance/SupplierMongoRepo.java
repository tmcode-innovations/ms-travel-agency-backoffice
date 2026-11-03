package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierMongoRepo extends MongoRepository<SupplierEntity, String> {

    SupplierEntity findByNameAndTenant(String name, String tenant);

    SupplierEntity findByIdentifyAndTenant(String identify, String tenant);

    SupplierEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    SupplierEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
