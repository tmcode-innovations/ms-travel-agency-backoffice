package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.RestaurantTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTypeMongoRepo extends MongoRepository<RestaurantTypeEntity, String> {

    RestaurantTypeEntity findByNameAndTenant(String name, String tenant);

    RestaurantTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    RestaurantTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RestaurantTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
