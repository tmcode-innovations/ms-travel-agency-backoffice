package com.saas.tourismagency.admin.backoffice.core.services.data.repo;

import com.saas.tourismagency.admin.backoffice.core.services.data.entity.OAuthAccessTokenEntity;
import com.saas.tourismagency.admin.backoffice.core.services.data.springdata.OAuthAccessTokenJpaRepo;
import dev.mm.javaee.core.repo.DefaultJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthAccessTokenRepo  extends DefaultJPARepository<OAuthAccessTokenEntity,String, OAuthAccessTokenJpaRepo> {

    @Autowired
    public OAuthAccessTokenRepo(OAuthAccessTokenJpaRepo oAuthAccessTokenJpaRepo) {
        super(oAuthAccessTokenJpaRepo);
    }

    public OAuthAccessTokenEntity findToken(byte[] token) {
        return repo().findFirstByToken(token).orElse(null);
    }

    public boolean existsToken(byte[] token) {
        return repo().existsByToken(token);
    }

    public void deleteByToken(byte[] token) {
        repo().deleteByToken(token);
    }

}
