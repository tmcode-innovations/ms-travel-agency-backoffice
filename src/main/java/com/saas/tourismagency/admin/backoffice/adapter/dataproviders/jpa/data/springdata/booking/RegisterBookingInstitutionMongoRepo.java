package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingInstitutionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingInstitutionMongoRepo extends MongoRepository<RegisterBookingInstitutionEntity, String> {

    RegisterBookingInstitutionEntity findByNameAndTypeInheritanceBookingAndTenant(String name, String typeInheritanceBooking, String tenant);

    RegisterBookingInstitutionEntity findByIdentifyAndTypeInheritanceBookingAndTenant(String identify, String typeInheritanceBooking, String tenant);

    RegisterBookingInstitutionEntity findByNameAndTypeInheritanceBookingAndTenantAndDeleted(String name, String typeInheritanceBooking, String tenant, Boolean deleted);

    RegisterBookingInstitutionEntity findByIdentifyAndTypeInheritanceBookingAndTenantAndDeleted(String identify, String typeInheritanceBooking, String tenant, Boolean deleted);
}
