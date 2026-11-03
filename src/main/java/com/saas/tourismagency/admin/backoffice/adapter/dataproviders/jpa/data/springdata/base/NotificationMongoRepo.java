package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMongoRepo extends MongoRepository<NotificationEntity, String> {

    NotificationEntity findByNameAndTenant(String name, String tenant);

    NotificationEntity findByIdentifyAndTenant(String identify, String tenant);

    NotificationEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    NotificationEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
