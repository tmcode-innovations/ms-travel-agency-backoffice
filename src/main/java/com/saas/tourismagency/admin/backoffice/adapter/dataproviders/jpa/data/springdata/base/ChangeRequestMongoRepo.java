package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestMongoRepo extends MongoRepository<ChangeRequestEntity, String> {

    ChangeRequestEntity findByNameAndTenant(String name, String tenant);

    ChangeRequestEntity findByIdentifyAndTenant(String identify, String tenant);

    ChangeRequestEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ChangeRequestEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
