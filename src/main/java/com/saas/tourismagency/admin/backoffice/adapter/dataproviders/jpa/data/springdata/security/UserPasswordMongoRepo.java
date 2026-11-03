package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPasswordMongoRepo extends MongoRepository<UserPasswordEntity, String> {


}
