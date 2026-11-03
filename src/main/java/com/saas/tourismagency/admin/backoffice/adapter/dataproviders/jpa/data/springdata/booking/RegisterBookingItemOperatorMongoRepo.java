package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemOperatorMongoRepo extends MongoRepository<RegisterBookingItemOperatorEntity, String> {

    RegisterBookingItemOperatorEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemOperatorEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemOperatorEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemOperatorEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
