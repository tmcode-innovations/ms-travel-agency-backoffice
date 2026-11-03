package com.saas.tourismagency.admin.backoffice.domain.core;

import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CompanyDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private String identify;

    private PartnerDomain partner;

    private OrganizationDomain organization;

    private SettingEmbeddedDomain setting;
}
