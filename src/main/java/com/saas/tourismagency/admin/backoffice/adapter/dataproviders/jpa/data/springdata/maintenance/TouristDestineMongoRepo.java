package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TouristDestineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristDestineMongoRepo extends MongoRepository<TouristDestineEntity, String> {

    TouristDestineEntity findByNameAndTenant(String name, String tenant);

    TouristDestineEntity findByIdentifyAndTenant(String identify, String tenant);

    TouristDestineEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    TouristDestineEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
