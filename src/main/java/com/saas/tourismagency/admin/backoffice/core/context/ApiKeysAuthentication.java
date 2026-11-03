package com.saas.tourismagency.admin.backoffice.core.context;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class ApiKeysAuthentication {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";

    @Value("${application.apikey.authtoken}")
    private String apiKeyAuthToken;

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(apiKeyAuthToken)) {
            throw new BadCredentialsException("Invalid API Key") ;
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }

}
