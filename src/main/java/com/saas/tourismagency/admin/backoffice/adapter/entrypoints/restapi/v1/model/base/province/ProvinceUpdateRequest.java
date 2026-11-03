package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("country")
    private String country;

    @JsonProperty("departmentRegion")
    private String departmentRegion;

    public ProvinceDomain to() {
        ProvinceDomain provinceDomain = new ProvinceDomain();
        provinceDomain.setId(id);
        provinceDomain.setName(name);
        provinceDomain.setDescription(description);
        provinceDomain.setIdentify(identify);
        return provinceDomain;
    }

    public ProvinceDomain to(DepartmentRegionDomain departmentRegionDomain, CountryDomain countryDomain) {
        ProvinceDomain provinceDomain = new ProvinceDomain();
        provinceDomain.setId(id);
        provinceDomain.setName(name);
        provinceDomain.setDescription(description);
        provinceDomain.setIdentify(identify);
        if (departmentRegionDomain != null)
            provinceDomain.setDepartmentRegion(departmentRegionDomain);
        if (countryDomain != null)
            provinceDomain.setCountry(countryDomain);
        return provinceDomain;
    }

}
