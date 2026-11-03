package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_register_groups")
public class RegisterGroupEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer days;

    private Integer cantPax;
    private Integer cantFree;
    private Integer cantPaxAdult;
    private Integer cantPaxChildren;
    private Integer tC;
    private Integer tCLocal;
    private Integer guide;
    private Integer totalPax;

    private LocalDate dateAperture;
    private LocalDate initTour;
    private LocalDate endTour;

    private String arriveAir;
    private String departureAir;

    private Integer cantSWB;
    private Integer cantTRP;
    private Integer cantCDP;
    private Integer cantQTP;
    private Integer cantSXTP;
    private Integer cantTWIN;
    private Integer cantMAT;
    private String observation;

    private String link;
    private String linkBitrix;
    private String linkWetravel;

    private @DocumentReference(lazy = true)
    ProgramEntity program;

    private @DocumentReference(lazy = true)
    QuotationEntity quotation;

    private @DocumentReference(lazy = true)
    CustomerEntity customer;

    private @DocumentReference(lazy = true)
    IdiomEntity idiom;

    private String stateRegisterGroup;

    private @DocumentReference(lazy = true)
    EmployeeEntity responsible;

    private @DocumentReference(lazy = true)
    CountryEntity country;

    private Integer versionQuotation;

    private Boolean apertureWrite;

    private String emailPassengerPrincipal;
    private String mobilePassengerPrincipal;
    private String whatsappPassengerPrincipal;

}
