package com.saas.tourismagency.admin.backoffice.core.filters;

import com.saas.tourismagency.admin.backoffice.core.context.ApiKeysAuthentication;
import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
//public class ApiKeyAuthenticationFilter implements Filter {
public class ApiKeyAuthenticationFilter {

    /*@Autowired
    public ApiKeysAuthentication apiKeysAuthentication;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("Start Api Keys Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            Authentication authentication = apiKeysAuthentication.getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception exp) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, exp.getMessage());
            return;
        }
    }*/
}
