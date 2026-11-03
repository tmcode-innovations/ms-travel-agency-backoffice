package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingLodgerMongoRepo extends MongoRepository<RegisterBookingLodgerEntity, String> {

    RegisterBookingLodgerEntity findByNameAndTypeInheritanceBookingAndTenant(String name, String typeInheritanceBooking, String tenant);

    RegisterBookingLodgerEntity findByIdentifyAndTypeInheritanceBookingAndTenant(String identify, String typeInheritanceBooking, String tenant);

    RegisterBookingLodgerEntity findByNameAndTypeInheritanceBookingAndTenantAndDeleted(String name, String typeInheritanceBooking, String tenant, Boolean deleted);

    RegisterBookingLodgerEntity findByIdentifyAndTypeInheritanceBookingAndTenantAndDeleted(String identify, String typeInheritanceBooking, String tenant, Boolean deleted);

}
