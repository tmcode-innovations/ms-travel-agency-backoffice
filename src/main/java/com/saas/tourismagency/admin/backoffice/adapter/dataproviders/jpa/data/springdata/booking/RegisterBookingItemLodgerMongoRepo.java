package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingItemLodgerMongoRepo extends MongoRepository<RegisterBookingItemLodgerEntity, String> {

    RegisterBookingItemLodgerEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingItemLodgerEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingItemLodgerEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingItemLodgerEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
