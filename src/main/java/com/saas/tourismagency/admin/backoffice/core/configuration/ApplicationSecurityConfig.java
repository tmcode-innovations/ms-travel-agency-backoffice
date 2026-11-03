package com.saas.tourismagency.admin.backoffice.core.configuration;

import com.saas.tourismagency.admin.backoffice.core.context.JWTAthenticationEntryPoint;
import com.saas.tourismagency.admin.backoffice.core.filters.ApiKeyAuthenticationFilter;
import com.saas.tourismagency.admin.backoffice.core.filters.JwtTokenVerifierFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig {

    @Autowired
    private JwtTokenVerifierFilter jwtTokenVerifierFilter;

    @Autowired
    private ApiKeyAuthenticationFilter apiKeyAuthenticationFilter;

    @Autowired
    private JWTAthenticationEntryPoint point;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain jwtTokenFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .addFilterBefore(jwtTokenVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth->{
                            // Actuator Health
                            auth.requestMatchers("/actuator/health").permitAll();
                            auth.requestMatchers("/backoffice-api-docs").permitAll();
                            auth.requestMatchers("/backoffice-documentation").permitAll();
                            // Tenant
                            auth.requestMatchers("/tenants/create").permitAll();
                            // Security
                            auth.requestMatchers("/security/authentications/login").permitAll();
                            auth.requestMatchers("/security/users/**").authenticated();
                            auth.requestMatchers("/security/profiles/**").authenticated();
                            auth.requestMatchers("/security/objects/**").authenticated();
                            auth.requestMatchers("/security/permissions/**").authenticated();
                            auth.requestMatchers("/security/apps/**").authenticated();
                            auth.requestMatchers("/security/change-password/**").authenticated();
                            // Dashboard
                            auth.requestMatchers("/dashboards/**").authenticated();
                            // Core
                            auth.requestMatchers("/core-nomenclatures/**").authenticated();
                            auth.requestMatchers("/companies/**").authenticated();
                            auth.requestMatchers("/organizations/**").authenticated();
                            auth.requestMatchers("/update-data/**").authenticated();
                            auth.requestMatchers("/seed-data/**").authenticated();
                            // Base
                            auth.requestMatchers("/areas/**").authenticated();
                            auth.requestMatchers("/base-nomenclatures/**").authenticated();
                            auth.requestMatchers("/countries/**").authenticated();
                            auth.requestMatchers("/department-regions/**").authenticated();
                            auth.requestMatchers("/districts/**").authenticated();
                            auth.requestMatchers("/idioms/**").authenticated();
                            auth.requestMatchers("/nationalities/**").authenticated();
                            auth.requestMatchers("/partners/**").authenticated();
                            auth.requestMatchers("/persons/**").authenticated();
                            auth.requestMatchers("/provinces/**").authenticated();
                            auth.requestMatchers("/states/**").authenticated();
                            auth.requestMatchers("/notifications/**").authenticated();
                            auth.requestMatchers("/change-requests/**").authenticated();
                            // Account
                            auth.requestMatchers("/account-nomenclatures/**").authenticated();
                            auth.requestMatchers("/account-payments/**").authenticated();
                            auth.requestMatchers("/change-types/**").authenticated();
                            auth.requestMatchers("/collections/**").authenticated();
                            auth.requestMatchers("/cost-sheet-two/**").authenticated();
                            auth.requestMatchers("/credit-note-commissions/**").authenticated();
                            auth.requestMatchers("/credit-notes/**").authenticated();
                            auth.requestMatchers("/currencies/**").authenticated();
                            auth.requestMatchers("/date-process/**").authenticated();
                            auth.requestMatchers("/debit-notes/**").authenticated();
                            auth.requestMatchers("/document-finances/**").authenticated();
                            auth.requestMatchers("/expense-guides/**").authenticated();
                            auth.requestMatchers("/group-pqt-tourists/**").authenticated();
                            auth.requestMatchers("/invoice-commissions/**").authenticated();
                            auth.requestMatchers("/invoices/**").authenticated();
                            auth.requestMatchers("/proformas/**").authenticated();
                            auth.requestMatchers("/proforma-credit-notes/**").authenticated();
                            auth.requestMatchers("/proforma-invoices/**").authenticated();
                            auth.requestMatchers("/register-document-vouchers/**").authenticated();
                            auth.requestMatchers("/service-pqt-tourists/**").authenticated();
                            auth.requestMatchers("/tickets/**").authenticated();
                            auth.requestMatchers("/voucher-egress/**").authenticated();
                            auth.requestMatchers("/voucher-ingress/**").authenticated();
                            auth.requestMatchers("/transfer-control-service/**").authenticated();
                            // Booking
                            auth.requestMatchers("/booking-nomenclatures/**").authenticated();
                            auth.requestMatchers("/itinerary-dates/**").authenticated();
                            auth.requestMatchers("/itinerary-items/**").authenticated();
                            auth.requestMatchers("/passenger-bookings/**").authenticated();
                            auth.requestMatchers("/passenger-groups/**").authenticated();
                            auth.requestMatchers("/register-booking-bases/**").authenticated();
                            auth.requestMatchers("/register-booking-item-lodgers/**").authenticated();
                            auth.requestMatchers("/register-booking-item-lodger-lodgings/**").authenticated();
                            auth.requestMatchers("/register-booking-item-lodgings/**").authenticated();
                            auth.requestMatchers("/register-booking-item-lodging-feedings/**").authenticated();
                            auth.requestMatchers("/register-booking-item-operators/**").authenticated();
                            auth.requestMatchers("/register-booking-item-restaurants/**").authenticated();
                            auth.requestMatchers("/register-booking-lodgers/**").authenticated();
                            auth.requestMatchers("/register-booking-lodgings/**").authenticated();
                            auth.requestMatchers("/register-booking-operators/**").authenticated();
                            auth.requestMatchers("/register-booking-restaurants/**").authenticated();
                            auth.requestMatchers("/register-booking-glampings/**").authenticated();
                            auth.requestMatchers("/register-booking-institutions/**").authenticated();
                            auth.requestMatchers("/register-groups/**").authenticated();
                            auth.requestMatchers("/register-booking-rooms/**").authenticated();
                            // Crm
                            auth.requestMatchers("/crm-nomenclatures/**").authenticated();
                            auth.requestMatchers("/programs/**").authenticated();
                            auth.requestMatchers("/program-types/**").authenticated();
                            auth.requestMatchers("/quotations/**").authenticated();
                            auth.requestMatchers("/tours/**").authenticated();
                            // Hr
                            auth.requestMatchers("/employees/**").authenticated();
                            auth.requestMatchers("/positions/**").authenticated();
                            // Maintenance
                            auth.requestMatchers("/banks/**").authenticated();
                            auth.requestMatchers("/contacts/**").authenticated();
                            auth.requestMatchers("/customers/**").authenticated();
                            auth.requestMatchers("/detractions/**").authenticated();
                            auth.requestMatchers("/document-account-types/**").authenticated();
                            auth.requestMatchers("/document-types/**").authenticated();
                            auth.requestMatchers("/entries/**").authenticated();
                            auth.requestMatchers("/group-booking-classifications/**").authenticated();
                            auth.requestMatchers("/guides/**").authenticated();
                            auth.requestMatchers("/igvs/**").authenticated();
                            auth.requestMatchers("/lodging-categories/**").authenticated();
                            auth.requestMatchers("/maintenance-nomenclatures/**").authenticated();
                            auth.requestMatchers("/payment-types/**").authenticated();
                            auth.requestMatchers("/restaurants-types/**").authenticated();
                            auth.requestMatchers("/sectors/**").authenticated();
                            auth.requestMatchers("/service-categories/**").authenticated();
                            auth.requestMatchers("/services/**").authenticated();
                            auth.requestMatchers("/supplier-administratives/**").authenticated();
                            auth.requestMatchers("/supplier-air-lines/**").authenticated();
                            auth.requestMatchers("/suppliers/**").authenticated();
                            auth.requestMatchers("/supplier-guides/**").authenticated();
                            auth.requestMatchers("/supplier-lodgers/**").authenticated();
                            auth.requestMatchers("/supplier-lodgings/**").authenticated();
                            auth.requestMatchers("/supplier-operators/**").authenticated();
                            auth.requestMatchers("/supplier-restaurants/**").authenticated();
                            auth.requestMatchers("/supplier-transports/**").authenticated();
                            auth.requestMatchers("/supplier-glampings/**").authenticated();
                            auth.requestMatchers("/supplier-institutions/**").authenticated();
                            auth.requestMatchers("/supplier-types/**").authenticated();
                            auth.requestMatchers("/tourist-destines/**").authenticated();
                            auth.requestMatchers("/transport-types/**").authenticated();
                            auth.requestMatchers("/passenger-types/**").authenticated();
                            auth.requestMatchers("/access-voucher-egress/**").authenticated();
                            auth.requestMatchers("/cost-sheet-two-percents/**").authenticated();
                            // Operation
                            auth.requestMatchers("/configuration-supplier-operators/**").authenticated();
                            auth.requestMatchers("/operation-nomenclatures/**").authenticated();
                            auth.requestMatchers("/order-services/**").authenticated();
                            auth.requestMatchers("/request-order-services/**").authenticated();
                            auth.requestMatchers("/transfer-control/**").authenticated();
                            // Statistics
                            auth.requestMatchers("/statistics/**").authenticated();
                            // Analytics
                            auth.requestMatchers("/analytics/account/**").authenticated();
                            auth.requestMatchers("/analytics/booking/**").authenticated();
                            auth.requestMatchers("/analytics/maintenance/**").authenticated();
                })
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    /*@Bean
    @Order(1)
    public SecurityFilterChain apiKeyAuthenticationFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/tenants/**")
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/tenants/**")).authenticated();
                })
                .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.disable());
        return http.build();
    }*/

}
