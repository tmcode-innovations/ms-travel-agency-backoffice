package com.saas.tourismagency.admin.backoffice.domain.crm;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TourDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer cantDay;
    private Boolean withoutCost;
    private Boolean uniqueDay;
}
