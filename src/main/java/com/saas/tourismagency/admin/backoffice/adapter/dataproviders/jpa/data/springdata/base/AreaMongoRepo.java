package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMongoRepo extends MongoRepository<AreaEntity, String> {

    AreaEntity findByNameAndTenant(String name, String tenant);

    AreaEntity findByIdentifyAndTenant(String identify, String tenant);

    AreaEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    AreaEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
