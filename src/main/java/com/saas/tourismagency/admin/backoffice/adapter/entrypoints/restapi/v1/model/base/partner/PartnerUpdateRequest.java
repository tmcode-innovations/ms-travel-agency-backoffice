package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerUpdateRequest {

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

    public PartnerDomain to() {
        PartnerDomain partnerDomain = new PartnerDomain();
        partnerDomain.setId(id);
        partnerDomain.setName(name);
        partnerDomain.setDescription(description);
        partnerDomain.setIdentify(identify);
        partnerDomain.setSocialMediaInformation(socialMediaInformation);
        partnerDomain.setWebSiteInformation(webSiteInformation);
        partnerDomain.setContactInformation(contactInformation);
        partnerDomain.setLocationInformation(locationInformation);
        partnerDomain.setAddress(address);
        return partnerDomain;
    }

}
