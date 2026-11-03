package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTypeMongoRepo extends MongoRepository<SupplierTypeEntity, String> {

    SupplierTypeEntity findByNameAndTenant(String name, String tenant);

    SupplierTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    SupplierTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    SupplierTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
