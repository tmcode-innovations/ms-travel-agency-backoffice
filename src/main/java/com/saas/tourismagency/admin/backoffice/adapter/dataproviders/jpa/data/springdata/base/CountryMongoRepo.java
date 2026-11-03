package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryMongoRepo extends MongoRepository<CountryEntity, String> {


}
