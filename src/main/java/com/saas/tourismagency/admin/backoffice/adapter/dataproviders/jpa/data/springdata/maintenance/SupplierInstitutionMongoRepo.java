package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierInstitutionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierInstitutionMongoRepo extends MongoRepository<SupplierInstitutionEntity, String> {

    SupplierInstitutionEntity findByNameAndTypeInheritanceAndTenant(String name, String typeInheritance, String tenant);

    SupplierInstitutionEntity findByIdentifyAndTypeInheritanceAndTenant(String identify, String typeInheritance, String tenant);

    SupplierInstitutionEntity findByNameAndTypeInheritanceAndTenantAndDeleted(String name, String typeInheritance, String tenant, Boolean deleted);

    SupplierInstitutionEntity findByIdentifyAndTypeInheritanceAndTenantAndDeleted(String identify, String typeInheritance, String tenant, Boolean deleted);
}
