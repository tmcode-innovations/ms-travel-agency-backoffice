package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourMongoRepo extends MongoRepository<TourEntity, String> {

    TourEntity findByNameAndTenant(String name, String tenant);

    TourEntity findByIdentifyAndTenant(String identify, String tenant);

    TourEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    TourEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
