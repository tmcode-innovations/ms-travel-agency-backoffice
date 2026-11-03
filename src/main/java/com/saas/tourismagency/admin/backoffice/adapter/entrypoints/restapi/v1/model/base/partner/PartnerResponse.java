package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;

    @JsonProperty("locationInformation")
    private LocationInformationEmbeddedDomain locationInformation;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

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

    public static PartnerResponse from(PartnerDomain domain) {
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setId(domain.getId());
        partnerResponse.setName(domain.getName());
        partnerResponse.setDescription(domain.getDescription());
        partnerResponse.setIdentify(domain.getIdentify());
        partnerResponse.setSocialMediaInformation(domain.getSocialMediaInformation());
        partnerResponse.setWebSiteInformation(domain.getWebSiteInformation());
        partnerResponse.setContactInformation(domain.getContactInformation());
        partnerResponse.setLocationInformation(domain.getLocationInformation());
        partnerResponse.setAddress(domain.getAddress());
        if (domain.getAddress() != null){
            if (partnerResponse.getAddress().getCountry() != null &&
                    partnerResponse.getAddress().getCountry().getId() == null){
                partnerResponse.getAddress().setCountry(null);
            }
            if (partnerResponse.getAddress().getDepartmentRegion() != null &&
                    partnerResponse.getAddress().getDepartmentRegion().getId() == null){
                partnerResponse.getAddress().setDepartmentRegion(null);
            }
            if (partnerResponse.getAddress().getProvince() != null &&
                    partnerResponse.getAddress().getProvince().getId() == null){
                partnerResponse.getAddress().setProvince(null);
            }
            if (partnerResponse.getAddress().getDistrict() != null &&
                    partnerResponse.getAddress().getDistrict().getId() == null){
                partnerResponse.getAddress().setDistrict(null);
            }
            if (partnerResponse.getAddress().getState() != null &&
                    partnerResponse.getAddress().getState().getId() == null){
                partnerResponse.getAddress().setState(null);
            }
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            partnerResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            partnerResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            partnerResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        partnerResponse.setCreatedAt(domain.getCreatedAt());
        partnerResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        partnerResponse.setUpdatedAt(domain.getUpdatedAt());
        partnerResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        partnerResponse.setDeletedAt(domain.getDeletedAt());
        partnerResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return partnerResponse;
    }
}
