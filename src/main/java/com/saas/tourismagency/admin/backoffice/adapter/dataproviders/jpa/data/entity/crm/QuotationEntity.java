package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CurrencyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "crm_quotations")
public class QuotationEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer cantDayTour;

    private Integer paxTotal;

    private Integer paxAdult;
    private Integer paxChild;
    private Integer paxTc;
    private Integer paxTcLocal;
    private Integer guide;

    private LocalDate dateInitTour;
    private LocalDate dateEndTour;

    private String observation;

    private String stateQuotation;

    private Double amount;
    private Double profitPercent;
    private Double otherExpenses;

    private @DocumentReference(lazy = true)
    CustomerEntity customer;
    private @DocumentReference(lazy = true)
    ProgramEntity program;
    private @DocumentReference(lazy = true)
    EmployeeEntity employee;
    private @DocumentReference(lazy = true)
    IdiomEntity idiom;
    private @DocumentReference(lazy = true)
    CountryEntity country;

    private @DocumentReference(lazy = true)
    EmployeeEntity responsibleFile;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private String identifyFile;

    private String quotationType;

    private String urlFileQuotation;
    private String urlFileItinerary;
    private String fileNameQuotation;
    private String fileNameItinerary;

    private String linkGDrive;
    private String linkBitrix;
    private String linkWetravel;

    private String emailPassengerPrincipal;
    private String mobilePassengerPrincipal;
    private String whatsappPassengerPrincipal;
}
