package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramItemMongoRepo extends MongoRepository<ProgramItemEntity, String> {


}
