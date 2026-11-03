package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.organization.OrganizationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("partner")
    PartnerResponse partner;

    @JsonProperty("organization")
    OrganizationResponse organization;

    @JsonProperty("setting")
    SettingEmbeddedDomain setting;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static CompanyResponse from(CompanyDomain domain) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(domain.getId());
        companyResponse.setName(domain.getName());
        companyResponse.setDescription(domain.getDescription());
        companyResponse.setIdentify(domain.getIdentify());
        companyResponse.setSetting(domain.getSetting());
        if (domain.getSetting() != null){
            if (domain.getSetting().getAdministrative() != null && domain.getSetting().getAdministrative().getId() == null){
                domain.getSetting().setAdministrative(null);
            }
            if (domain.getSetting().getLodging() != null && domain.getSetting().getLodging().getId() == null){
                domain.getSetting().setLodging(null);
            }
            if (domain.getSetting().getLodger() != null && domain.getSetting().getLodger().getId() == null){
                domain.getSetting().setLodger(null);
            }
            if (domain.getSetting().getOperator() != null && domain.getSetting().getOperator().getId() == null){
                domain.getSetting().setOperator(null);
            }
            if (domain.getSetting().getRestaurant() != null && domain.getSetting().getRestaurant().getId() == null){
                domain.getSetting().setRestaurant(null);
            }
            if (domain.getSetting().getTransport() != null && domain.getSetting().getTransport().getId() == null){
                domain.getSetting().setTransport(null);
            }
            if (domain.getSetting().getGuide() != null && domain.getSetting().getGuide().getId() == null){
                domain.getSetting().setGuide(null);
            }
            if (domain.getSetting().getAirLine() != null && domain.getSetting().getAirLine().getId() == null){
                domain.getSetting().setAirLine(null);
            }
            if (domain.getSetting().getGlamping() != null && domain.getSetting().getGlamping().getId() == null){
                domain.getSetting().setGlamping(null);
            }
            if (domain.getSetting().getInstitution() != null && domain.getSetting().getInstitution().getId() == null){
                domain.getSetting().setInstitution(null);
            }
            if (domain.getSetting().getCurrencyUsdDefault() != null && domain.getSetting().getCurrencyUsdDefault().getId() == null){
                domain.getSetting().setCurrencyUsdDefault(null);
            }
            if (domain.getSetting().getCurrencySolesDefault() != null && domain.getSetting().getCurrencySolesDefault().getId() == null){
                domain.getSetting().setCurrencySolesDefault(null);
            }
        }
        if (domain.getOrganization() != null &&
                domain.getOrganization().getId() != null &&
                !domain.getOrganization().getId().isEmpty()){
            companyResponse.setOrganization(OrganizationResponse.from(domain.getOrganization()));
        }
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            companyResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }


        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            companyResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            companyResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            companyResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        companyResponse.setCreatedAt(domain.getCreatedAt());
        companyResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        companyResponse.setUpdatedAt(domain.getUpdatedAt());
        companyResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        companyResponse.setDeletedAt(domain.getDeletedAt());
        companyResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return companyResponse;
    }
}
