package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeUserMongoRepo extends MongoRepository<EmployeeUserEntity, String> {

    EmployeeUserEntity findByNameAndTenant(String name, String tenant);

    EmployeeUserEntity findByIdentifyAndTenant(String identify, String tenant);

    EmployeeUserEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    EmployeeUserEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
