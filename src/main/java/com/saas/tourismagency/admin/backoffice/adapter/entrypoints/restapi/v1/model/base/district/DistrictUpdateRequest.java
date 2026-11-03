package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictUpdateRequest {

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

    @JsonProperty("province")
    private String province;

    public DistrictDomain to() {
        DistrictDomain districtDomain = new DistrictDomain();
        districtDomain.setId(id);
        districtDomain.setName(name);
        districtDomain.setDescription(description);
        districtDomain.setIdentify(identify);
        return districtDomain;
    }

    public DistrictDomain to(ProvinceDomain provinceDomain, DepartmentRegionDomain departmentRegionDomain, CountryDomain countryDomain) {
        DistrictDomain districtDomain = new DistrictDomain();
        districtDomain.setId(id);
        districtDomain.setName(name);
        districtDomain.setDescription(description);
        districtDomain.setIdentify(identify);
        if (provinceDomain != null)
            districtDomain.setProvince(provinceDomain);
        if (departmentRegionDomain != null)
            districtDomain.setDepartmentRegion(departmentRegionDomain);
        if (countryDomain != null)
            districtDomain.setCountry(countryDomain);
        return districtDomain;
    }

}
