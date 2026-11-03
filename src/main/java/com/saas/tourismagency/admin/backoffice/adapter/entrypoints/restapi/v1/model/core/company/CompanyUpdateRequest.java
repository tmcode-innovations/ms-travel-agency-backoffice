package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("partner")
    private PartnerUpdateRequest partner;

    @JsonProperty("setting")
    private SettingEmbeddedDomain setting;

    @JsonProperty("organization")
    private String organization;

    public CompanyDomain to() {
        CompanyDomain companyDomain = new CompanyDomain();
        companyDomain.setId(id);
        companyDomain.setName(name);
        companyDomain.setDescription(description);
        companyDomain.setSetting(setting);
        return companyDomain;
    }

    public CompanyDomain to(PartnerDomain partner, OrganizationDomain organization) {
        CompanyDomain companyDomain = new CompanyDomain();
        companyDomain.setId(id);
        companyDomain.setName(name);
        companyDomain.setDescription(description);
        companyDomain.setSetting(setting);
        if (partner != null)
            companyDomain.setPartner(partner);
        if (organization != null)
            companyDomain.setOrganization(organization);
        return companyDomain;
    }

}
