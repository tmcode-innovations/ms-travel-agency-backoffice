package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTransportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTransportMongoRepo extends MongoRepository<SupplierTransportEntity, String> {

    SupplierTransportEntity findByNameAndTypeInheritanceAndTenant(String name, String typeInheritance, String tenant);

    SupplierTransportEntity findByIdentifyAndTypeInheritanceAndTenant(String identify, String typeInheritance, String tenant);

    SupplierTransportEntity findByNameAndTypeInheritanceAndTenantAndDeleted(String name, String typeInheritance, String tenant, Boolean deleted);

    SupplierTransportEntity findByIdentifyAndTypeInheritanceAndTenantAndDeleted(String identify, String typeInheritance, String tenant, Boolean deleted);
}
