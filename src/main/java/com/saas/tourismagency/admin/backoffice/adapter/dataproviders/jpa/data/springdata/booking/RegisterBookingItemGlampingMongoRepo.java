package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemGlampingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemGlampingMongoRepo extends MongoRepository<RegisterBookingItemGlampingEntity, String> {

    RegisterBookingItemGlampingEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemGlampingEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemGlampingEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemGlampingEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
