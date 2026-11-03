package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryItemCrmMongoRepo extends MongoRepository<ItineraryItemEntity, String> {
    
}
