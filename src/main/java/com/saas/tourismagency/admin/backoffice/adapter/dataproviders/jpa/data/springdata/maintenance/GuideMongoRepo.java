package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideMongoRepo extends MongoRepository<GuideEntity, String> {

    GuideEntity findByNameAndTenant(String name, String tenant);

    GuideEntity findByIdentifyAndTenant(String identify, String tenant);

    GuideEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    GuideEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
