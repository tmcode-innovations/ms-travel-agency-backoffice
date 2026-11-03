package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

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
    private PersonResponse person;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("supplierType")
    private SupplierTypeResponse supplierType;

    @JsonProperty("customer")
    private CustomerResponse customer;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;


    public static ContactResponse from(ContactDomain domain) {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(domain.getId());
        contactResponse.setName(domain.getName());
        contactResponse.setDescription(domain.getDescription());
        contactResponse.setIdentify(domain.getIdentify());
        contactResponse.setAppointment(domain.getAppointment());
        contactResponse.setContactType(domain.getContactType());
        contactResponse.setAddress(domain.getAddress());
        contactResponse.setSocialMediaInformation(domain.getSocialMediaInformation());
        contactResponse.setWebSiteInformation(domain.getWebSiteInformation());
        contactResponse.setContactInformation(domain.getContactInformation());
        if (domain.getPerson() != null &&
                domain.getPerson().getId() != null &&
                !domain.getPerson().getId().isEmpty()
        ){
            contactResponse.setPerson(PersonResponse.from(domain.getPerson()));
        }
        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()
        ){
            contactResponse.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            contactResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            contactResponse.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            contactResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            contactResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            contactResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        contactResponse.setCreatedAt(domain.getCreatedAt());
        contactResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        contactResponse.setUpdatedAt(domain.getUpdatedAt());
        contactResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        contactResponse.setDeletedAt(domain.getDeletedAt());
        contactResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return contactResponse;
    }
}
