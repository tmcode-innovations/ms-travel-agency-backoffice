package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonCreateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerCreateRequest {

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

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("passengerType")
    private String passengerType;

    public PassengerDomain to(QuotationDomain quotation) {
        PassengerDomain passengerDomain = new PassengerDomain();
        passengerDomain.setName(name);
        passengerDomain.setDescription(description);
        passengerDomain.setIdentify(identify);
        passengerDomain.setSocialMediaInformation(socialMediaInformation);
        passengerDomain.setWebSiteInformation(webSiteInformation);
        passengerDomain.setContactInformation(contactInformation);
        passengerDomain.setAddress(address);
        passengerDomain.setDateInit(dateInit);
        passengerDomain.setDateEnd(dateEnd);
        passengerDomain.setDocument(document);
        passengerDomain.setObservation(observation);
        passengerDomain.setImageUrl(imageUrl);
        passengerDomain.setPrincipal(principal);
        passengerDomain.setTitlePerson(titlePerson);
        passengerDomain.setTypePassengerTcBooking(typePassengerTcBooking);
        if (quotation != null)
            passengerDomain.setQuotation(quotation);
        return passengerDomain;
    }

    public PassengerDomain to(PersonDomain person, NationalityDomain nationality, DocumentTypeDomain documentType, QuotationDomain quotation, PassengerTypeDomain passengerType) {
        PassengerDomain passengerDomain = new PassengerDomain();
        passengerDomain.setName(name);
        passengerDomain.setDescription(description);
        passengerDomain.setIdentify(identify);
        passengerDomain.setSocialMediaInformation(socialMediaInformation);
        passengerDomain.setWebSiteInformation(webSiteInformation);
        passengerDomain.setContactInformation(contactInformation);
        passengerDomain.setAddress(address);
        passengerDomain.setDateInit(dateInit);
        passengerDomain.setDateEnd(dateEnd);
        passengerDomain.setDocument(document);
        passengerDomain.setObservation(observation);
        passengerDomain.setImageUrl(imageUrl);
        passengerDomain.setPrincipal(principal);
        passengerDomain.setTitlePerson(titlePerson);
        passengerDomain.setTypePassengerTcBooking(typePassengerTcBooking);
        if (person != null)
            passengerDomain.setPerson(person);
        if (nationality != null)
            passengerDomain.setNationality(nationality);
        if (documentType != null)
            passengerDomain.setDocumentType(documentType);
        if (quotation != null)
            passengerDomain.setQuotation(quotation);
        if (passengerType != null)
            passengerDomain.setPassengerType(passengerType);
        return passengerDomain;
    }
}
