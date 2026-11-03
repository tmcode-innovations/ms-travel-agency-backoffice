package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.GroupPqtTouristEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPqtTouristMongoRepo extends MongoRepository<GroupPqtTouristEntity, String> {

    GroupPqtTouristEntity findByNameAndTenant(String name, String tenant);

    GroupPqtTouristEntity findByIdentifyAndTenant(String identify, String tenant);

    GroupPqtTouristEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    GroupPqtTouristEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
