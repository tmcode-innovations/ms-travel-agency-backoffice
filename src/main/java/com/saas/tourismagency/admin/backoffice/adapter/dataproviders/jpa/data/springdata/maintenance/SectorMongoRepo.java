package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SectorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorMongoRepo extends MongoRepository<SectorEntity, String> {

    SectorEntity findByNameAndTenant(String name, String tenant);

    SectorEntity findByIdentifyAndTenant(String identify, String tenant);

    SectorEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    SectorEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
