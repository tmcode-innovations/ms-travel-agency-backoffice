package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonCreateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuideCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ruc")
    private String ruc;

    @JsonProperty("person")
    private PersonCreateRequest person;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("idioms")
    private Set<String> idioms;

    @JsonProperty("guideTypes")
    private Set<String> guideTypes;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;


    public GuideDomain to() {
        GuideDomain guideDomain = new GuideDomain();
        guideDomain.setName(name);
        guideDomain.setDescription(description);
        guideDomain.setRuc(ruc);
        guideDomain.setAddress(address);
        guideDomain.setSocialMediaInformation(socialMediaInformation);
        guideDomain.setWebSiteInformation(webSiteInformation);
        guideDomain.setContactInformation(contactInformation);
        return guideDomain;
    }

    public GuideDomain to(PersonDomain person, SupplierDomain supplier, Set<IdiomDomain> idioms, Set<String> guideTypes) {
        GuideDomain guideDomain = new GuideDomain();
        guideDomain.setName(name);
        guideDomain.setDescription(description);
        guideDomain.setRuc(ruc);
        guideDomain.setAddress(address);
        guideDomain.setSocialMediaInformation(socialMediaInformation);
        guideDomain.setWebSiteInformation(webSiteInformation);
        guideDomain.setContactInformation(contactInformation);
        if (person != null)
            guideDomain.setPerson(person);
        if (supplier != null)
            guideDomain.setSupplier(supplier);
        if (idioms != null && !idioms.isEmpty())
            guideDomain.setIdioms(idioms);
        if (guideTypes != null && !guideTypes.isEmpty())
            guideDomain.setGuideTypes(guideTypes);
        return guideDomain;
    }
}
