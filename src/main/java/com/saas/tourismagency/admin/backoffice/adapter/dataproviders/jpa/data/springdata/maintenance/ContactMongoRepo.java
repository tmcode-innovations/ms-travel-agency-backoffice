package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ContactEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMongoRepo extends MongoRepository<ContactEntity, String> {


}
