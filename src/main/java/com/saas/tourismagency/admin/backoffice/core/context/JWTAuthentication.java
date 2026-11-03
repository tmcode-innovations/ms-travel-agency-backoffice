package com.saas.tourismagency.admin.backoffice.core.context;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.JWTClaims;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthentication implements Authentication {

    private final UserDomain user;
    private final List<SimpleGrantedAuthority> authorities;

    public JWTAuthentication(JWTClaims tokenInfo) {
        user = new UserDomain();
        user.setId(tokenInfo.getUserId());
        user.setUserName(tokenInfo.getUserUserName());
        user.setEmail(tokenInfo.getUserEmail());
        user.setFullName(tokenInfo.getUserFullName());
        user.setTenant(tokenInfo.getTenant());
        authorities = new ArrayList<>();
    }


    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public UserPasswordDomain getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserDomain getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return user.getUserName();
    }

}
