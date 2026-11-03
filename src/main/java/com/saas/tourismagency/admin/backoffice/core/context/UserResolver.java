package com.saas.tourismagency.admin.backoffice.core.context;

import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserResolver {

    public static JWTAuthentication resolve() {
        return ((JWTAuthentication) SecurityContextHolder.getContext().getAuthentication());
    }

    public static UserDomain resolveUser() {
        return ((JWTAuthentication) SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
    }

    public static UserPasswordDomain resolveCredentials() {
        return ((JWTAuthentication) SecurityContextHolder.getContext().getAuthentication()).getCredentials();
    }

}
