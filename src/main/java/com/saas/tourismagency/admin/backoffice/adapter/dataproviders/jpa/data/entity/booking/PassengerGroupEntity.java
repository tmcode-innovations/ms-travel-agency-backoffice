package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NationalityEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.AddressEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.ContactInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.SocialMediaInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.WebSiteInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PassengerTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_passenger_groups")
public class PassengerGroupEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private SocialMediaInformationEmbeddedEntity socialMediaInformation;
    private WebSiteInformationEmbeddedEntity webSiteInformation;
    private ContactInformationEmbeddedEntity contactInformation;
    private AddressEmbeddedEntity address;

    private LocalDate dateInit;
    private LocalDate dateEnd;
    private String document;
    private String observation;
    private String imageUrl;

    private Boolean principal;

    private String titlePerson;
    private String typePassengerTcBooking;

    private @DocumentReference(lazy = true)
    PersonEntity person;

    private @DocumentReference(lazy = true)
    NationalityEntity nationality;

    private @DocumentReference(lazy = true)
    DocumentTypeEntity documentType;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private String idPassengerQuotation;

    private @DocumentReference(lazy = true)
    PassengerTypeEntity passengerType;

    private Integer index;
}
