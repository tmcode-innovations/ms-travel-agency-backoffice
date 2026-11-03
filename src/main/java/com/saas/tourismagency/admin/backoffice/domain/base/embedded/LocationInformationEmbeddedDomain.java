package com.saas.tourismagency.admin.backoffice.domain.base.embedded;

import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationInformationEmbeddedDomain implements Serializable {

    private String id;
    private String name;
    private String description;

    private String latitude;
    private String longitude;

}
