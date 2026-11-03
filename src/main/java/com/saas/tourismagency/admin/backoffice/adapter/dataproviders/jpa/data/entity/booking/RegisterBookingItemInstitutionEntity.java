package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_register_booking_item_institutions")
public class RegisterBookingItemInstitutionEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateService;
    private Integer cantPax;
    private Integer sequence;

    private String observation;
    private String otherService;

    private Boolean pvt;
    private Boolean sic;

    private LocalDate date;
    private LocalDateTime dateTime;
    private String idItineraryItemIdentify;

    private @DocumentReference(lazy = true)
    ServiceEntity service;

    private @DocumentReference(lazy = true)
    RegisterBookingInstitutionEntity registerBookingInstitution;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;
}
