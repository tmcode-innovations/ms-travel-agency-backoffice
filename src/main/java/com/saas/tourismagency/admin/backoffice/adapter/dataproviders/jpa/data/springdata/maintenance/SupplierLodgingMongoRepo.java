package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierLodgingMongoRepo extends MongoRepository<SupplierLodgingEntity, String> {

    SupplierLodgingEntity findByNameAndTypeInheritanceAndTenant(String name, String typeInheritance, String tenant);

    SupplierLodgingEntity findByIdentifyAndTypeInheritanceAndTenant(String identify, String typeInheritance, String tenant);

    SupplierLodgingEntity findByNameAndTypeInheritanceAndTenantAndDeleted(String name, String typeInheritance, String tenant, Boolean deleted);

    SupplierLodgingEntity findByIdentifyAndTypeInheritanceAndTenantAndDeleted(String identify, String typeInheritance, String tenant, Boolean deleted);
}
