package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GroupBookingClassificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupBookingClassificationMongoRepo extends MongoRepository<GroupBookingClassificationEntity, String> {

    GroupBookingClassificationEntity findByNameAndTenant(String name, String tenant);

    GroupBookingClassificationEntity findByIdentifyAndTenant(String identify, String tenant);

    GroupBookingClassificationEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    GroupBookingClassificationEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
