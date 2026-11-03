package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgvMongoRepo extends MongoRepository<IgvEntity, String> {

    IgvEntity findByNameAndTenant(String name, String tenant);

    IgvEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    IgvEntity findByAvailableAndTenantAndDeleted(Boolean available, String tenant, Boolean deleted);

}
