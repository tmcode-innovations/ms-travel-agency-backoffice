package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepo extends MongoRepository<UserEntity, String> {

    public UserEntity findByUserName(String userName);

    public UserEntity findByEmail(String email);

    public UserEntity findByUserNameOrEmail(String userName, String email);

    public UserEntity findByUserNameAndTenant(String userName, String tenant);

    public UserEntity findByEmailAndTenant(String email, String tenant);

    public UserEntity findByTenantAndUserNameOrEmail(String tenant, String userName, String email);

}
