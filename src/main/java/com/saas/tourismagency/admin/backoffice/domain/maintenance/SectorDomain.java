package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SectorDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

}
