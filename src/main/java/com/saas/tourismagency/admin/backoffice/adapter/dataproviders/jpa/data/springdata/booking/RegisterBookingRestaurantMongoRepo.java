package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingRestaurantMongoRepo extends MongoRepository<RegisterBookingRestaurantEntity, String> {

    RegisterBookingRestaurantEntity findByNameAndTypeInheritanceBookingAndTenant(String name, String typeInheritanceBooking, String tenant);

    RegisterBookingRestaurantEntity findByIdentifyAndTypeInheritanceBookingAndTenant(String identify, String typeInheritanceBooking, String tenant);

    RegisterBookingRestaurantEntity findByNameAndTypeInheritanceBookingAndTenantAndDeleted(String name, String typeInheritanceBooking, String tenant, Boolean deleted);

    RegisterBookingRestaurantEntity findByIdentifyAndTypeInheritanceBookingAndTenantAndDeleted(String identify, String typeInheritanceBooking, String tenant, Boolean deleted);
}
