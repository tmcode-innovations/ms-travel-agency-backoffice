package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierGlampingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierGlampingMongoRepo extends MongoRepository<SupplierGlampingEntity, String> {

    SupplierGlampingEntity findByNameAndTypeInheritanceAndTenant(String name, String typeInheritance, String tenant);

    SupplierGlampingEntity findByIdentifyAndTypeInheritanceAndTenant(String identify, String typeInheritance, String tenant);

    SupplierGlampingEntity findByNameAndTypeInheritanceAndTenantAndDeleted(String name, String typeInheritance, String tenant, Boolean deleted);

    SupplierGlampingEntity findByIdentifyAndTypeInheritanceAndTenantAndDeleted(String identify, String typeInheritance, String tenant, Boolean deleted);
}
