package com.saas.tourismagency.admin.backoffice.domain.base;

import com.saas.tourismagency.admin.backoffice.domain.base.embedded.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PartnerDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private String identify;

    private SocialMediaInformationEmbeddedDomain socialMediaInformation;
    private WebSiteInformationEmbeddedDomain webSiteInformation;
    private ContactInformationEmbeddedDomain contactInformation;
    private LocationInformationEmbeddedDomain locationInformation;
    private AddressEmbeddedDomain address;

}
