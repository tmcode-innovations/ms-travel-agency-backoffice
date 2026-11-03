package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonCreateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerGroupCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

    @JsonProperty("dateInit")
    private LocalDate dateInit;

    @JsonProperty("dateEnd")
    private LocalDate dateEnd;

    @JsonProperty("document")
    private String document;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("principal")
    private Boolean principal;

    @JsonProperty("titlePerson")
    private String titlePerson;

    @JsonProperty("typePassengerTcBooking")
    private String typePassengerTcBooking;

    @JsonProperty("person")
    private PersonCreateRequest person;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("passengerType")
    private String passengerType;

    public PassengerGroupDomain to(RegisterGroupDomain registerGroup) {
        PassengerGroupDomain domain = new PassengerGroupDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setSocialMediaInformation(socialMediaInformation);
        domain.setWebSiteInformation(webSiteInformation);
        domain.setContactInformation(contactInformation);
        domain.setAddress(address);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setDocument(document);
        domain.setObservation(observation);
        domain.setImageUrl(imageUrl);
        domain.setPrincipal(principal);
        domain.setTitlePerson(titlePerson);
        domain.setTypePassengerTcBooking(typePassengerTcBooking);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }

    public PassengerGroupDomain to(PersonDomain person, NationalityDomain nationality, DocumentTypeDomain documentType, RegisterGroupDomain registerGroup, PassengerTypeDomain passengerType) {
        PassengerGroupDomain domain = new PassengerGroupDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setSocialMediaInformation(socialMediaInformation);
        domain.setWebSiteInformation(webSiteInformation);
        domain.setContactInformation(contactInformation);
        domain.setAddress(address);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setDocument(document);
        domain.setObservation(observation);
        domain.setImageUrl(imageUrl);
        domain.setTitlePerson(titlePerson);
        domain.setPrincipal(principal);
        domain.setTypePassengerTcBooking(typePassengerTcBooking);
        if (person != null)
            domain.setPerson(person);
        if (nationality != null)
            domain.setNationality(nationality);
        if (documentType != null)
            domain.setDocumentType(documentType);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (passengerType != null)
            domain.setPassengerType(passengerType);
        return domain;
    }
}
