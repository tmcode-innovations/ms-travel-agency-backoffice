package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.domain.base.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TouristDestineDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private CountryDomain country;
    private DepartmentRegionDomain departmentRegion;
    private ProvinceDomain province;
    private DistrictDomain district;
    private StateDomain state;


}
