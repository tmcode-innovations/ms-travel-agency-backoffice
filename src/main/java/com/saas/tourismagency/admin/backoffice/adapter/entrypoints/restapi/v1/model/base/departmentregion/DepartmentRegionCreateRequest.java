package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRegionCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("country")
    private String country;

    public DepartmentRegionDomain to() {
        DepartmentRegionDomain departmentRegionDomain = new DepartmentRegionDomain();
        departmentRegionDomain.setName(name);
        departmentRegionDomain.setDescription(description);
        departmentRegionDomain.setIdentify(identify);
        departmentRegionDomain.setCapital(capital);
        return departmentRegionDomain;
    }

    public DepartmentRegionDomain to(CountryDomain countryDomain) {
        DepartmentRegionDomain departmentRegionDomain = new DepartmentRegionDomain();
        departmentRegionDomain.setName(name);
        departmentRegionDomain.setDescription(description);
        departmentRegionDomain.setIdentify(identify);
        departmentRegionDomain.setCapital(capital);
        if (countryDomain != null)
            departmentRegionDomain.setCountry(countryDomain);
        return departmentRegionDomain;
    }
}
