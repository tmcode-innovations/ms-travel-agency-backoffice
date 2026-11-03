package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("appointment")
    private String appointment;

    @JsonProperty("contactType")
    private String contactType;

    @JsonProperty("person")
    private PersonUpdateRequest person;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("supplierType")
    protected String supplierType;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;

    public ContactDomain to() {
        ContactDomain contactDomain = new ContactDomain();
        contactDomain.setId(id);
        contactDomain.setName(name);
        contactDomain.setDescription(description);
        contactDomain.setIdentify(identify);
        contactDomain.setAppointment(appointment);
        contactDomain.setContactType(contactType);
        contactDomain.setAddress(address);
        contactDomain.setSocialMediaInformation(socialMediaInformation);
        contactDomain.setWebSiteInformation(webSiteInformation);
        contactDomain.setContactInformation(contactInformation);
        return contactDomain;
    }

    public ContactDomain to(PersonDomain person, SupplierDomain supplier, SupplierTypeDomain supplierType, CustomerDomain customer) {
        ContactDomain contactDomain = new ContactDomain();
        contactDomain.setId(id);
        contactDomain.setName(name);
        contactDomain.setDescription(description);
        contactDomain.setIdentify(identify);
        contactDomain.setAppointment(appointment);
        contactDomain.setContactType(contactType);
        contactDomain.setAddress(address);
        contactDomain.setSocialMediaInformation(socialMediaInformation);
        contactDomain.setWebSiteInformation(webSiteInformation);
        contactDomain.setContactInformation(contactInformation);
        if (person != null)
            contactDomain.setPerson(person);
        if (supplier != null)
            contactDomain.setSupplier(supplier);
        if (supplierType != null)
            contactDomain.setSupplierType(supplierType);
        if (customer != null)
            contactDomain.setCustomer(customer);
        return contactDomain;
    }

    public ContactDomain to(PersonDomain person, SupplierDomain supplier, SupplierTypeDomain supplierType) {
        ContactDomain contactDomain = new ContactDomain();
        contactDomain.setId(id);
        contactDomain.setName(name);
        contactDomain.setDescription(description);
        contactDomain.setIdentify(identify);
        contactDomain.setAppointment(appointment);
        contactDomain.setContactType(contactType);
        contactDomain.setAddress(address);
        contactDomain.setSocialMediaInformation(socialMediaInformation);
        contactDomain.setWebSiteInformation(webSiteInformation);
        contactDomain.setContactInformation(contactInformation);
        if (person != null)
            contactDomain.setPerson(person);
        if (supplier != null)
            contactDomain.setSupplier(supplier);
        if (supplierType != null)
            contactDomain.setSupplierType(supplierType);
        return contactDomain;
    }

    public ContactDomain to(PersonDomain person, CustomerDomain customer) {
        ContactDomain contactDomain = new ContactDomain();
        contactDomain.setId(id);
        contactDomain.setName(name);
        contactDomain.setDescription(description);
        contactDomain.setIdentify(identify);
        contactDomain.setAppointment(appointment);
        contactDomain.setContactType(contactType);
        contactDomain.setAddress(address);
        contactDomain.setSocialMediaInformation(socialMediaInformation);
        contactDomain.setWebSiteInformation(webSiteInformation);
        contactDomain.setContactInformation(contactInformation);
        if (person != null)
            contactDomain.setPerson(person);
        if (customer != null)
            contactDomain.setCustomer(customer);
        return contactDomain;
    }

}
