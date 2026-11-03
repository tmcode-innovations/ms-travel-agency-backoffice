package com.saas.tourismagency.admin.backoffice.core.services.data.springdata;

import com.saas.tourismagency.admin.backoffice.core.services.data.entity.OAuthAccessTokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthAccessTokenJpaRepo  extends MongoRepository<OAuthAccessTokenEntity, String> {

    public Optional<OAuthAccessTokenEntity> findFirstByToken(byte[] token);

    public boolean existsByToken(byte[] token);

    public void deleteByToken(@Param("token") byte[] token);

}
