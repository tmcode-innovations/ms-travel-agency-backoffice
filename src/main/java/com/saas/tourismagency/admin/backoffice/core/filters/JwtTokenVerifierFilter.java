package com.saas.tourismagency.admin.backoffice.core.filters;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.JWTClaims;
import com.saas.tourismagency.admin.backoffice.core.context.JWTAuthentication;
import com.saas.tourismagency.admin.backoffice.core.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Slf4j
public class JwtTokenVerifierFilter implements Filter {

    private final String PREFIX_BEARER = "Bearer ";

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtTokenVerifierFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("Start Jwt Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            String authHeader = req.getHeader(AUTHORIZATION);
            if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(PREFIX_BEARER)) {
                String token = authHeader.replace(PREFIX_BEARER, "");
                if(jwtUtil.tokensAvailable(token)){
                    JWTClaims tokenInfo = jwtUtil.validateToken(token);
                    Authentication auth = new JWTAuthentication(tokenInfo);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException
                | MalformedJwtException | SignatureException e) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }
}

