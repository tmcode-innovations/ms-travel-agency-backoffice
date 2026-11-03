package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingFeedingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemLodgingFeedingMongoRepo extends MongoRepository<RegisterBookingItemLodgingFeedingEntity, String> {

    RegisterBookingItemLodgingFeedingEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemLodgingFeedingEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemLodgingFeedingEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemLodgingFeedingEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
