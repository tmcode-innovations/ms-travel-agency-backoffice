package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CostSheetTwoPercentDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private Boolean available;
    private Double percentMin;
    private Double percentMax;

}
