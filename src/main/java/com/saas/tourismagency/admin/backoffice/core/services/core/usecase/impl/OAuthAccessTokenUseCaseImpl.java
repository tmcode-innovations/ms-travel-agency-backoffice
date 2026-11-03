package com.saas.tourismagency.admin.backoffice.core.services.core.usecase.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.core.services.core.usecase.OAuthAccessTokenUseCase;
import com.saas.tourismagency.admin.backoffice.core.services.data.entity.OAuthAccessTokenEntity;
import com.saas.tourismagency.admin.backoffice.core.services.data.repo.OAuthAccessTokenRepo;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class OAuthAccessTokenUseCaseImpl implements OAuthAccessTokenUseCase {

    @Autowired
    private OAuthAccessTokenRepo oAuthAccessTokenRepo;
    @Autowired
    private UserConverter userConverter;


    @Override
    public void save(String token, UserDomain userDomain, LocalDateTime issued, LocalDateTime expired) {
        OAuthAccessTokenEntity authToken = new OAuthAccessTokenEntity();
        authToken.setToken(token.getBytes(StandardCharsets.UTF_8));
        authToken.setExpiresAt(expired);
        authToken.setIssuedAt(issued);
        authToken.setUser(userConverter.toEntity(userDomain));
        oAuthAccessTokenRepo.create(authToken);
    }

    @Override
    public boolean existsToken(String token) {
        return oAuthAccessTokenRepo.existsToken(token.getBytes(StandardCharsets.UTF_8));
    }

    @Transactional(readOnly = true)
    @Override
    public OAuthAccessTokenEntity findToken(String token) {
        return oAuthAccessTokenRepo.findToken(token.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void deleteToken(String token) {
        oAuthAccessTokenRepo.deleteByToken(token.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void deleteById(String id) {
        oAuthAccessTokenRepo.destroyById(id);
    }
}
