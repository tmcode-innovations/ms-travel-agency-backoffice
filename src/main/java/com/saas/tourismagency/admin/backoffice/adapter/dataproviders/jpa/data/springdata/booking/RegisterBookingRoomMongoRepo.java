package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingRoomMongoRepo extends MongoRepository<RegisterBookingRoomEntity, String> {

    RegisterBookingRoomEntity findByNameAndTenant(String name, String tenant);

    RegisterBookingRoomEntity findByIdentifyAndTenant(String identify, String tenant);

    RegisterBookingRoomEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    RegisterBookingRoomEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
