package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordHistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPasswordHistoryMongoRepo extends MongoRepository<UserPasswordHistoryEntity, String> {

    public List<UserPasswordHistoryEntity> findByUserIdAndTenant(String userId, String tenant);

    public UserPasswordHistoryEntity findByPasswordHashAndUserIdAndTenant(String passwordHash, String userId, String tenant);

}
