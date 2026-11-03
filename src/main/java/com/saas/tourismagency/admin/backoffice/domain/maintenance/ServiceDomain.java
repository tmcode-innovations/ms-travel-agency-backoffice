package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ServiceDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String abbrev;
    private String observation;
    private Boolean noApplyRate;
    private Integer day;
    private Boolean withoutCost;
    private Boolean typeTour;
    private Double cost;

    private String serviceType;

    private TouristDestineDomain touristDestine;
    private ServiceCategoryDomain serviceCategory;


}
