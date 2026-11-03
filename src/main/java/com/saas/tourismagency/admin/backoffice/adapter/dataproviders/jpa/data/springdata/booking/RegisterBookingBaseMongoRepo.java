package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingBaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterBookingBaseMongoRepo extends MongoRepository<RegisterBookingBaseEntity, String> {


}
