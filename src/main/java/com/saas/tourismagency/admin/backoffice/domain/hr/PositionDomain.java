package com.saas.tourismagency.admin.backoffice.domain.hr;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PositionDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

}
