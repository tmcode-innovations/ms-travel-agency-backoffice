package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ContactDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private String identify;

    private String appointment;

    private String contactType;

    private PersonDomain person;
    private SupplierDomain supplier;
    private SupplierTypeDomain supplierType;
    private CustomerDomain customer;


    private AddressEmbeddedDomain address;
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;
    private WebSiteInformationEmbeddedDomain webSiteInformation;
    private ContactInformationEmbeddedDomain contactInformation;
}
