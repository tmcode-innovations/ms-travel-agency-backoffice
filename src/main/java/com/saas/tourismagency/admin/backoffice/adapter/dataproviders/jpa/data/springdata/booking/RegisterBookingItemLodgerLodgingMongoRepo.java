package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerLodgingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemLodgerLodgingMongoRepo extends MongoRepository<RegisterBookingItemLodgerLodgingEntity, String> {

    RegisterBookingItemLodgerLodgingEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemLodgerLodgingEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemLodgerLodgingEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemLodgerLodgingEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
