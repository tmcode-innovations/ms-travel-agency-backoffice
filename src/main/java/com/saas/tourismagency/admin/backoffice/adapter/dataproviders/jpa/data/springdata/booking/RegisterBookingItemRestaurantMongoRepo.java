package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemRestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemRestaurantMongoRepo extends MongoRepository<RegisterBookingItemRestaurantEntity, String> {

    RegisterBookingItemRestaurantEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemRestaurantEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemRestaurantEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemRestaurantEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
