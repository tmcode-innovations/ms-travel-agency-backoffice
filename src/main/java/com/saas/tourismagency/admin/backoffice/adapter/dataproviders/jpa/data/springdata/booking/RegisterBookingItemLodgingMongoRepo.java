package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemLodgingMongoRepo extends MongoRepository<RegisterBookingItemLodgingEntity, String> {

    RegisterBookingItemLodgingEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemLodgingEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemLodgingEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemLodgingEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
