package com.saas.tourismagency.admin.backoffice.core.services.core.usecase;

import com.saas.tourismagency.admin.backoffice.core.services.data.entity.OAuthAccessTokenEntity;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;

import java.time.Instant;
import java.time.LocalDateTime;

public interface OAuthAccessTokenUseCase {

    public void save(String token, UserDomain userDomain, LocalDateTime issued, LocalDateTime expired);

    public boolean existsToken(String token);

    public OAuthAccessTokenEntity findToken(String token);

    public void deleteToken(String token);

    public void deleteById(String id);

}
