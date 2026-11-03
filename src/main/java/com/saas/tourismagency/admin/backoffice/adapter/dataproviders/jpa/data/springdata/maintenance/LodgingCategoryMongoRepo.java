package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.LodgingCategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LodgingCategoryMongoRepo extends MongoRepository<LodgingCategoryEntity, String> {

    LodgingCategoryEntity findByNameAndTenant(String name, String tenant);

    LodgingCategoryEntity findByIdentifyAndTenant(String identify, String tenant);

    LodgingCategoryEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    LodgingCategoryEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
