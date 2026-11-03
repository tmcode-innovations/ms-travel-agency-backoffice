package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterBookingBaseDomain extends BaseDomain implements Serializable {

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

    protected SupplierTypeDomain supplierType;
    protected SupplierDomain supplier;
    protected RegisterGroupDomain registerGroup;
    protected CustomerDomain customer;
    protected NationalityDomain nationality;
    protected EmployeeDomain employee;

    protected Boolean activeRegisterBooking;
}
