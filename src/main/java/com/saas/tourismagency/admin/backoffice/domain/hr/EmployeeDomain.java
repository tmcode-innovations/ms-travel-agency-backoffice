package com.saas.tourismagency.admin.backoffice.domain.hr;

import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class EmployeeDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private PersonDomain person;
    private AreaDomain area;
    private PositionDomain position;
    private PartnerDomain partner;

    private String urlFileImageSign;
    private String fileNameImageSign;
}
