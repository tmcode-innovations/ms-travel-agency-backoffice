package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ProvinceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceMongoRepo extends MongoRepository<ProvinceEntity, String> {


}
