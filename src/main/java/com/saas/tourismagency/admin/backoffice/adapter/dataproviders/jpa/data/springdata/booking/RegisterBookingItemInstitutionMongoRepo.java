package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemInstitutionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemInstitutionMongoRepo extends MongoRepository<RegisterBookingItemInstitutionEntity, String> {

    RegisterBookingItemInstitutionEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemInstitutionEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemInstitutionEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemInstitutionEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
