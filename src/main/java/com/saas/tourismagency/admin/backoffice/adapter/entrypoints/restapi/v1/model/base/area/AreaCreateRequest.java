package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("company")
    private String company;

    public AreaDomain to() {
        AreaDomain areaDomain = new AreaDomain();
        areaDomain.setName(name);
        areaDomain.setDescription(description);
        areaDomain.setIdentify(identify);
        return areaDomain;
    }

    public AreaDomain to(CompanyDomain companyDomain) {
        AreaDomain areaDomain = new AreaDomain();
        areaDomain.setName(name);
        areaDomain.setDescription(description);
        areaDomain.setIdentify(identify);
        if (companyDomain != null)
            areaDomain.setCompany(companyDomain);
        return areaDomain;
    }
}
