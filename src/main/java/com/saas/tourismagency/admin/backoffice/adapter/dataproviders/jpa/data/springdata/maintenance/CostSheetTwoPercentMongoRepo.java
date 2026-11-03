package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CostSheetTwoPercentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostSheetTwoPercentMongoRepo extends MongoRepository<CostSheetTwoPercentEntity, String> {

    CostSheetTwoPercentEntity findByNameAndTenant(String name, String tenant);

    CostSheetTwoPercentEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    CostSheetTwoPercentEntity findByAvailableAndTenantAndDeleted(Boolean available, String tenant, Boolean deleted);

}
