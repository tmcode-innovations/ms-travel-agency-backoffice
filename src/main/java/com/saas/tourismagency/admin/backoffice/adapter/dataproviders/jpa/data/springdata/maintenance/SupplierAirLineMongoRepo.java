package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAirLineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierAirLineMongoRepo extends MongoRepository<SupplierAirLineEntity, String> {

    SupplierAirLineEntity findByNameAndTypeInheritanceAndTenant(String name, String typeInheritance, String tenant);

    SupplierAirLineEntity findByIdentifyAndTypeInheritanceAndTenant(String identify, String typeInheritance, String tenant);

    SupplierAirLineEntity findByNameAndTypeInheritanceAndTenantAndDeleted(String name, String typeInheritance, String tenant, Boolean deleted);

    SupplierAirLineEntity findByIdentifyAndTypeInheritanceAndTenantAndDeleted(String identify, String typeInheritance, String tenant, Boolean deleted);
}
