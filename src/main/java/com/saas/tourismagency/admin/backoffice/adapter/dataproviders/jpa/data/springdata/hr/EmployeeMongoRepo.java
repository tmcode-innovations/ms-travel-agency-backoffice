package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMongoRepo extends MongoRepository<EmployeeEntity, String> {

    EmployeeEntity findByNameAndTenant(String name, String tenant);

    EmployeeEntity findByIdentifyAndTenant(String identify, String tenant);

    EmployeeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    EmployeeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
