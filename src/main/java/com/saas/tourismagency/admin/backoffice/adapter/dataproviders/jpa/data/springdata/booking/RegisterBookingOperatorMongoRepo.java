package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingOperatorMongoRepo extends MongoRepository<RegisterBookingOperatorEntity, String> {

    RegisterBookingOperatorEntity findByNameAndTypeInheritanceBookingAndTenant(String name, String typeInheritanceBooking, String tenant);

    RegisterBookingOperatorEntity findByIdentifyAndTypeInheritanceBookingAndTenant(String identify, String typeInheritanceBooking, String tenant);

    RegisterBookingOperatorEntity findByNameAndTypeInheritanceBookingAndTenantAndDeleted(String name, String typeInheritanceBooking, String tenant, Boolean deleted);

    RegisterBookingOperatorEntity findByIdentifyAndTypeInheritanceBookingAndTenantAndDeleted(String identify, String typeInheritanceBooking, String tenant, Boolean deleted);
}
