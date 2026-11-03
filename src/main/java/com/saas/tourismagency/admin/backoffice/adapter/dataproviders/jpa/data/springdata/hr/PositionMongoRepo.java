package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.PositionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionMongoRepo extends MongoRepository<PositionEntity, String> {

    PositionEntity findByNameAndTenant(String name, String tenant);

    PositionEntity findByIdentifyAndTenant(String identify, String tenant);

    PositionEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    PositionEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
