package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingLodgingMongoRepo extends MongoRepository<RegisterBookingLodgingEntity, String> {

    RegisterBookingLodgingEntity findByNameAndTypeInheritanceBookingAndTenant(String name, String typeInheritanceBooking, String tenant);

    RegisterBookingLodgingEntity findByIdentifyAndTypeInheritanceBookingAndTenant(String identify, String typeInheritanceBooking, String tenant);

    RegisterBookingLodgingEntity findByNameAndTypeInheritanceBookingAndTenantAndDeleted(String name, String typeInheritanceBooking, String tenant, Boolean deleted);

    RegisterBookingLodgingEntity findByIdentifyAndTypeInheritanceBookingAndTenantAndDeleted(String identify, String typeInheritanceBooking, String tenant, Boolean deleted);
}
