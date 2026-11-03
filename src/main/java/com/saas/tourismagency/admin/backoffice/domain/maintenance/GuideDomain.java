package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class GuideDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private String identify;
    private String ruc;

    private PersonDomain person;
    private SupplierDomain supplier;

    private Set<IdiomDomain> idioms;
    private Set<String> guideTypes;

    private AddressEmbeddedDomain address;
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;
    private WebSiteInformationEmbeddedDomain webSiteInformation;
    private ContactInformationEmbeddedDomain contactInformation;
}
