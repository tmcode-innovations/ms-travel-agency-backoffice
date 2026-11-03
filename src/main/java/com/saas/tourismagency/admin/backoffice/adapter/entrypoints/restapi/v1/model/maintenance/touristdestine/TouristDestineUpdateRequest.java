package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristDestineUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("country")
    private String country;

    @JsonProperty("departmentRegion")
    private String departmentRegion;

    @JsonProperty("province")
    private String province;

    @JsonProperty("district")
    private String district;

    @JsonProperty("state")
    private String state;

    public TouristDestineDomain to() {
        TouristDestineDomain touristDestineDomain = new TouristDestineDomain();
        touristDestineDomain.setId(id);
        touristDestineDomain.setName(name);
        touristDestineDomain.setDescription(description);
        return touristDestineDomain;
    }

    public TouristDestineDomain to(CountryDomain country, DepartmentRegionDomain departmentRegion, ProvinceDomain province, DistrictDomain district, StateDomain state) {
        TouristDestineDomain touristDestineDomain = new TouristDestineDomain();
        touristDestineDomain.setId(id);
        touristDestineDomain.setName(name);
        touristDestineDomain.setDescription(description);
        if (country != null)
            touristDestineDomain.setCountry(country);
        if (departmentRegion != null)
            touristDestineDomain.setDepartmentRegion(departmentRegion);
        if (province != null)
            touristDestineDomain.setProvince(province);
        if (district != null)
            touristDestineDomain.setDistrict(district);
        if (state != null)
            touristDestineDomain.setState(state);
        return touristDestineDomain;
    }

}
