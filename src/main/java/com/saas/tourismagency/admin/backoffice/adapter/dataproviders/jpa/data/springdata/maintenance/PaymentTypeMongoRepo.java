package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PaymentTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeMongoRepo extends MongoRepository<PaymentTypeEntity, String> {

    PaymentTypeEntity findByNameAndTenant(String name, String tenant);

    PaymentTypeEntity findByIdentifyAndTenant(String identify, String tenant);

    PaymentTypeEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    PaymentTypeEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
