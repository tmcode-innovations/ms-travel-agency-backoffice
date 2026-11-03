package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryItemMongoRepo extends MongoRepository<ItineraryItemEntity, String> {


}
