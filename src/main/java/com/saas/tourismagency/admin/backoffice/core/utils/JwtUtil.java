package com.saas.tourismagency.admin.backoffice.core.utils;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.CompanyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.JWTClaims;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginResponse;
import com.saas.tourismagency.admin.backoffice.core.services.core.usecase.OAuthAccessTokenUseCase;
import com.saas.tourismagency.admin.backoffice.core.services.data.entity.OAuthAccessTokenEntity;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JwtUtil {

    public static String JWT_CLAIM_ID = "user_id";
    public static String JWT_CLAIM_USERNAME = "user_username";
    public static String JWT_CLAIM_EMAIL = "user_email";
    public static String JWT_CLAIM_FULLNAME = "user_fullname";
    public static String JWT_CLAIM_TENANT = "user_tenant";
    public static String JWT_CLAIM_APPS = "user_apps";
    public static String JWT_CLAIM_PERMISSIONS = "user_permissions";

    public static long JWT_TOKEN_VALIDITY = 43200;
    public static String JWT_TOKEN_SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    // @Value("${application.jwt.token.validity}")
    // private long jwtTokenValidity;

    // @Value("${application.jwt.token.secret}")
    // private String jwtTokenSecret;

    @Autowired
    private OAuthAccessTokenUseCase oAuthAccessTokenUseCase;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private StringUtil stringUtil;


    @Transactional
    public LoginResponse generateToken(UserDomain user) {

        List<String> apps = user.getApps().stream().map(appDomain -> appDomain.getCode()).collect(Collectors.toList());
        List<String> permissions = new ArrayList<>();
        if (user.getProfiles() != null && !user.getProfiles().isEmpty()){
            for(ProfileDomain profileDomain: user.getProfiles()){
                if (profileDomain.getPermissions() != null && !profileDomain.getPermissions().isEmpty()){
                    for(PermissionDomain permissionDomain: profileDomain.getPermissions()){
                        if (!permissions.contains(permissionDomain.getEntity().getIdentify())){
                            permissions.add(permissionDomain.getEntity().getIdentify());
                        }
                    }
                }
            }
        }
        LocalDateTime nowLdt = LocalDateTime.now();
        ZonedDateTime zdt = nowLdt.atZone(ZoneId.systemDefault());
        Date issuedAt = Date.from(zdt.toInstant());

        Date expirationAt = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
        LocalDateTime ldtExpirationAt = LocalDateTime.ofInstant(expirationAt.toInstant(), ZoneId.systemDefault());

        SecretKey key = Keys.hmacShaKeyFor(JWT_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));

        CompanyResponse companyResponse = companyUseCase.findByTenant(user.getTenant());

        String token = Jwts.builder()
                .setSubject(user.getFullName())
                .claim(JWT_CLAIM_ID, user.getId())
                .claim(JWT_CLAIM_USERNAME, user.getUserName())
                .claim(JWT_CLAIM_EMAIL, user.getEmail())
                .claim(JWT_CLAIM_FULLNAME, user.getFullName())
                .claim(JWT_CLAIM_TENANT, user.getTenant())
                .claim(JWT_CLAIM_APPS, apps)
                .claim(JWT_CLAIM_PERMISSIONS, permissions)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationAt)
                .signWith(key)
                .compact();

        oAuthAccessTokenUseCase.save(token,user,nowLdt,ldtExpirationAt);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwt(token);
        loginResponse.setExpireAt(ldtExpirationAt);
        loginResponse.setSettings(new SettingEmbeddedDomain());
        loginResponse.getSettings().setId( companyResponse.getSetting().getId() );
        loginResponse.getSettings().setName( companyResponse.getSetting().getName() );
        loginResponse.getSettings().setDescription( companyResponse.getSetting().getDescription() );
        loginResponse.getSettings().setQuotationType( companyResponse.getSetting().getQuotationType() );
        loginResponse.getSettings().setRegisterGroupType( companyResponse.getSetting().getRegisterGroupType() );
        loginResponse.getSettings().setGeneralConfigurationType( companyResponse.getSetting().getGeneralConfigurationType() );
        loginResponse.getSettings().setUrlSaveDocumentQuotation( companyResponse.getSetting().getUrlSaveDocumentQuotation() );
        loginResponse.getSettings().setUrlSaveDocumentBooking( companyResponse.getSetting().getUrlSaveDocumentBooking() );
        loginResponse.getSettings().setUrlSaveDocumentOperation( companyResponse.getSetting().getUrlSaveDocumentOperation() );
        loginResponse.getSettings().setUrlSaveDocumentAccount( companyResponse.getSetting().getUrlSaveDocumentAccount() );
        loginResponse.getSettings().setCompanyNameReportIntern( companyResponse.getSetting().getCompanyNameReportIntern() );
        loginResponse.getSettings().setCompanyNameReportExtern( companyResponse.getSetting().getCompanyNameReportExtern() );
        loginResponse.getSettings().setLodgingCantBreakfastDateTime( companyResponse.getSetting().getLodgingCantBreakfastDateTime() );
        loginResponse.getSettings().setLodgingCantDinnerDateTime( companyResponse.getSetting().getLodgingCantDinnerDateTime() );
        loginResponse.getSettings().setLodgingCantLunchDateTime( companyResponse.getSetting().getLodgingCantLunchDateTime() );
        loginResponse.getSettings().setLodgingCantBoxLunchDateTime( companyResponse.getSetting().getLodgingCantBoxLunchDateTime() );
        loginResponse.getSettings().setLodgeCantBreakfastDateTime( companyResponse.getSetting().getLodgeCantBreakfastDateTime() );
        loginResponse.getSettings().setLodgeCantDinnerDateTime( companyResponse.getSetting().getLodgeCantDinnerDateTime() );
        loginResponse.getSettings().setLodgeCantLunchDateTime( companyResponse.getSetting().getLodgeCantLunchDateTime() );
        loginResponse.getSettings().setLodgeCantBoxLunchDateTime( companyResponse.getSetting().getLodgeCantBoxLunchDateTime() );

        if (companyResponse.getSetting().getLodging() != null &&
                companyResponse.getSetting().getLodging().getId() != null &&
                !companyResponse.getSetting().getLodging().getId().isEmpty()){
            loginResponse.getSettings().setLodging( companyResponse.getSetting().getLodging() );
        }else{
            loginResponse.getSettings().setLodging( null );
        }

        if (companyResponse.getSetting().getLodger() != null &&
                companyResponse.getSetting().getLodger().getId() != null &&
                !companyResponse.getSetting().getLodger().getId().isEmpty()){
            loginResponse.getSettings().setLodger( companyResponse.getSetting().getLodger() );
        }else{
            loginResponse.getSettings().setLodger( null );
        }

        if (companyResponse.getSetting().getOperator() != null &&
                companyResponse.getSetting().getOperator().getId() != null &&
                !companyResponse.getSetting().getOperator().getId().isEmpty()){
            loginResponse.getSettings().setOperator( companyResponse.getSetting().getOperator() );
        }else{
            loginResponse.getSettings().setOperator( null );
        }

        if (companyResponse.getSetting().getRestaurant() != null &&
                companyResponse.getSetting().getRestaurant().getId() != null &&
                !companyResponse.getSetting().getRestaurant().getId().isEmpty()){
            loginResponse.getSettings().setRestaurant( companyResponse.getSetting().getRestaurant() );
        }else{
            loginResponse.getSettings().setRestaurant( null );
        }

        if (companyResponse.getSetting().getGuide() != null &&
                companyResponse.getSetting().getGuide().getId() != null &&
                !companyResponse.getSetting().getGuide().getId().isEmpty()){
            loginResponse.getSettings().setGuide( companyResponse.getSetting().getGuide() );
        }else{
            loginResponse.getSettings().setGuide( null );
        }

        if (companyResponse.getSetting().getTransport() != null &&
                companyResponse.getSetting().getTransport().getId() != null &&
                !companyResponse.getSetting().getTransport().getId().isEmpty()){
            loginResponse.getSettings().setTransport( companyResponse.getSetting().getTransport() );
        }else{
            loginResponse.getSettings().setTransport( null );
        }

        if (companyResponse.getSetting().getAirLine() != null &&
                companyResponse.getSetting().getAirLine().getId() != null &&
                !companyResponse.getSetting().getAirLine().getId().isEmpty()){
            loginResponse.getSettings().setAirLine( companyResponse.getSetting().getAirLine() );
        }else{
            loginResponse.getSettings().setAirLine( null );
        }

        if (companyResponse.getSetting().getAdministrative() != null &&
                companyResponse.getSetting().getAdministrative().getId() != null &&
                !companyResponse.getSetting().getAdministrative().getId().isEmpty()){
            loginResponse.getSettings().setAdministrative( companyResponse.getSetting().getAdministrative() );
        }else{
            loginResponse.getSettings().setAdministrative( null );
        }

        if (companyResponse.getSetting().getGlamping() != null &&
                companyResponse.getSetting().getGlamping().getId() != null &&
                !companyResponse.getSetting().getGlamping().getId().isEmpty()){
            loginResponse.getSettings().setGlamping( companyResponse.getSetting().getGlamping() );
        }else{
            loginResponse.getSettings().setGlamping( null );
        }

        if (companyResponse.getSetting().getInstitution() != null &&
                companyResponse.getSetting().getInstitution().getId() != null &&
                !companyResponse.getSetting().getInstitution().getId().isEmpty()){
            loginResponse.getSettings().setInstitution( companyResponse.getSetting().getInstitution() );
        }else{
            loginResponse.getSettings().setInstitution( null );
        }

        if (companyResponse.getSetting().getCurrencyUsdDefault() != null &&
                companyResponse.getSetting().getCurrencyUsdDefault().getId() != null &&
                !companyResponse.getSetting().getCurrencyUsdDefault().getId().isEmpty()){
            loginResponse.getSettings().setCurrencyUsdDefault( companyResponse.getSetting().getCurrencyUsdDefault() );
        }else{
            loginResponse.getSettings().setCurrencyUsdDefault( null );
        }

        if (companyResponse.getSetting().getCurrencySolesDefault() != null &&
                companyResponse.getSetting().getCurrencySolesDefault().getId() != null &&
                !companyResponse.getSetting().getCurrencySolesDefault().getId().isEmpty()){
            loginResponse.getSettings().setCurrencySolesDefault( companyResponse.getSetting().getCurrencySolesDefault() );
        }else{
            loginResponse.getSettings().setCurrencySolesDefault( null );
        }

        if (companyResponse.getSetting().getCurrencyVoucherEgressDefault() != null &&
                companyResponse.getSetting().getCurrencyVoucherEgressDefault().getId() != null &&
                !companyResponse.getSetting().getCurrencyVoucherEgressDefault().getId().isEmpty()){
            loginResponse.getSettings().setCurrencyVoucherEgressDefault( companyResponse.getSetting().getCurrencyVoucherEgressDefault() );
        }else{
            loginResponse.getSettings().setCurrencyVoucherEgressDefault( null );
        }

        if (companyResponse.getSetting().getBankVoucherEgressDefault() != null &&
                companyResponse.getSetting().getBankVoucherEgressDefault().getId() != null &&
                !companyResponse.getSetting().getBankVoucherEgressDefault().getId().isEmpty()){
            loginResponse.getSettings().setBankVoucherEgressDefault( companyResponse.getSetting().getBankVoucherEgressDefault() );
        }else{
            loginResponse.getSettings().setCurrencyVoucherEgressDefault( null );
        }

        if (StringUtils.isNotEmpty(companyResponse.getSetting().getVoucherTypeVoucherEgressDefault())) {
            loginResponse.getSettings().setVoucherTypeVoucherEgressDefault( companyResponse.getSetting().getVoucherTypeVoucherEgressDefault() );
        }else{
            loginResponse.getSettings().setVoucherTypeVoucherEgressDefault( null );
        }

        if (StringUtils.isNotEmpty(companyResponse.getSetting().getValueTypeShowSupplierVoucherEgressDefault())) {
            loginResponse.getSettings().setValueTypeShowSupplierVoucherEgressDefault( companyResponse.getSetting().getValueTypeShowSupplierVoucherEgressDefault() );
        }else{
            loginResponse.getSettings().setValueTypeShowSupplierVoucherEgressDefault( null );
        }


        loginResponse.setId(user.getId());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setFullName(user.getFullName());
        loginResponse.setApps(apps);
        loginResponse.setPermissions(permissions);
        return loginResponse;
    }

    public JWTClaims validateToken(String bearerToken) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        SecretKey key = Keys.hmacShaKeyFor(JWT_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(bearerToken)
                .getBody();

        String userId = claims.get(JWT_CLAIM_ID, String.class);
        String userUsername = claims.get(JWT_CLAIM_USERNAME, String.class);
        String userEmail = claims.get(JWT_CLAIM_EMAIL, String.class);
        String userFullname = claims.get(JWT_CLAIM_FULLNAME, String.class);
        String userTenant = claims.get(JWT_CLAIM_TENANT, String.class);
        List<String> userApps = claims.get(JWT_CLAIM_APPS, List.class);
        List<String> userPermissions = claims.get(JWT_CLAIM_PERMISSIONS, List.class);

        return new JWTClaims(
                userId,
                userUsername,
                userEmail,
                userFullname,
                userTenant,
                userApps,
                userPermissions
        );
    }

    public boolean tokensAvailable(String jwt){
        if(stringUtil.isEmpty(jwt)){
            return false;
        }
        OAuthAccessTokenEntity authToken = oAuthAccessTokenUseCase.findToken(jwt);
        if(authToken == null){
            return false;
        }else{
            if(authToken.getExpiresAt().isBefore(LocalDateTime.now())){
                try {
                    oAuthAccessTokenUseCase.deleteById(authToken.getId());
                } catch (DataAccessException e) {
                    log.warn("No se pudo eliminar el token OAuth expirado {}: {}", authToken.getId(), e.getMessage());
                }
                return false;
            }
        }
        return true;
    }

    public Claims getAllClaims(String bearerToken) {
        SecretKey key = Keys.hmacShaKeyFor(JWT_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(bearerToken)
                .getBody();
        return claims;
    }

    public String getUserEmail(String bearerToken) {
        return getAllClaims(bearerToken).get(JWT_CLAIM_EMAIL, String.class);
    }
}
