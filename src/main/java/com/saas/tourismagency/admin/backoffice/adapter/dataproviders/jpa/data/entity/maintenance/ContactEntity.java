package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.AddressEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.ContactInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.SocialMediaInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.WebSiteInformationEmbeddedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "maintenance_contacts")
public class ContactEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private String identify;

    private String appointment;

    private String contactType;

    private @DocumentReference(lazy = true)
    PersonEntity person;
    private @DocumentReference(lazy = true)
    SupplierEntity supplier;
    protected @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;
    private @DocumentReference(lazy = true)
    CustomerEntity customer;

    private AddressEmbeddedEntity address;
    private SocialMediaInformationEmbeddedEntity socialMediaInformation;
    private WebSiteInformationEmbeddedEntity webSiteInformation;
    private ContactInformationEmbeddedEntity contactInformation;

}
