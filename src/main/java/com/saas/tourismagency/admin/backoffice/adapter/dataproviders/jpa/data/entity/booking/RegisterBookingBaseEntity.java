package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NationalityEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_register_booking_bases")
@TypeAlias("RegisterBookingEntityData")
public class RegisterBookingBaseEntity extends BaseEntity implements Serializable {

    protected String name;
    protected String description;
    protected String identify;

    protected String typeInheritanceBooking;

    protected String identifyConfirmation;
    protected String identifyReConfirmation;

    protected String arriveAir;
    protected String departureAir;

    protected LocalDate arriveAirDate;
    protected LocalDate dateAperture;
    protected LocalDate departureAirDate;
    protected LocalDate dateConfirmation;
    protected Boolean confirmation;
    protected LocalDate dateReConfirmation;
    protected Boolean reConfirmation;
    protected Boolean prePago;
    protected Boolean gastosExtrasNoIncluidos;
    protected Boolean withConfirmation;
    protected Boolean withReConfirmation;

    protected String observation;
    protected String observationCupon;
    protected Integer cantPax;
    protected Integer cantFree;
    protected Integer cantPaxAdult;
    protected Integer cantPaxChildren;
    protected Integer tc;
    protected Integer tcLocal;
    protected Integer guide;
    protected Integer totalPax;
    protected Integer valueConfirmation;
    protected Integer valueReConfirmation;

    protected String stateBooking;

    protected @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    protected @DocumentReference(lazy = true)
    SupplierEntity supplier;

    protected @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    protected @DocumentReference(lazy = true)
    CustomerEntity customer;

    protected @DocumentReference(lazy = true)
    NationalityEntity nationality;

    protected @DocumentReference(lazy = true)
    EmployeeEntity employee;

    protected Boolean activeRegisterBooking;
}
