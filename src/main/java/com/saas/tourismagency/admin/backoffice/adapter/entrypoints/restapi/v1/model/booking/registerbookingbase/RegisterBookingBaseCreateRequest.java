package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingBaseCreateRequest {

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("identifyConfirmation")
    protected String identifyConfirmation;

    @JsonProperty("identifyReConfirmation")
    protected String identifyReConfirmation;

    @JsonProperty("arriveAir")
    protected String arriveAir;

    @JsonProperty("departureAir")
    protected String departureAir;

    @JsonProperty("arriveAirDate")
    protected LocalDate arriveAirDate;

    @JsonProperty("dateAperture")
    protected LocalDate dateAperture;

    @JsonProperty("departureAirDate")
    protected LocalDate departureAirDate;

    @JsonProperty("dateConfirmation")
    protected LocalDate dateConfirmation;

    @JsonProperty("confirmation")
    protected Boolean confirmation;

    @JsonProperty("dateReConfirmation")
    protected LocalDate dateReConfirmation;

    @JsonProperty("reConfirmation")
    protected Boolean reConfirmation;

    @JsonProperty("prePago")
    protected Boolean prePago;

    @JsonProperty("gastosExtrasNoIncluidos")
    protected Boolean gastosExtrasNoIncluidos;

    @JsonProperty("withConfirmation")
    protected Boolean withConfirmation;

    @JsonProperty("withReConfirmation")
    protected Boolean withReConfirmation;

    @JsonProperty("observation")
    protected String observation;

    @JsonProperty("observationCupon")
    protected String observationCupon;

    @JsonProperty("cantPax")
    protected Integer cantPax;

    @JsonProperty("cantFree")
    protected Integer cantFree;

    @JsonProperty("cantPaxAdult")
    protected Integer cantPaxAdult;

    @JsonProperty("cantPaxChildren")
    protected Integer cantPaxChildren;

    @JsonProperty("tc")
    protected Integer tc;

    @JsonProperty("tcLocal")
    protected Integer tcLocal;

    @JsonProperty("guide")
    protected Integer guide;

    @JsonProperty("totalPax")
    protected Integer totalPax;

    @JsonProperty("valueConfirmation")
    protected Integer valueConfirmation;

    @JsonProperty("valueReConfirmation")
    protected Integer valueReConfirmation;

    @JsonProperty("stateBooking")
    protected String stateBooking;

    @JsonProperty("supplierType")
    protected String supplierType;

    @JsonProperty("supplier")
    protected String supplier;

    @JsonProperty("registerGroup")
    protected String registerGroup;

    @JsonProperty("customer")
    protected String customer;

    @JsonProperty("nationality")
    protected String nationality;

    @JsonProperty("employee")
    protected String employee;

    public RegisterBookingBaseDomain to() {
        RegisterBookingBaseDomain domain = new RegisterBookingBaseDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setIdentifyConfirmation(identifyConfirmation);
        domain.setIdentifyReConfirmation(identifyReConfirmation);
        domain.setArriveAir(arriveAir);
        domain.setDepartureAir(departureAir);
        domain.setArriveAirDate(arriveAirDate);
        domain.setDateAperture(dateAperture);
        domain.setDepartureAirDate(departureAirDate);
        domain.setDateConfirmation(dateConfirmation);
        domain.setConfirmation(confirmation);
        domain.setDateReConfirmation(dateReConfirmation);
        domain.setReConfirmation(reConfirmation);
        domain.setPrePago(prePago);
        domain.setGastosExtrasNoIncluidos(gastosExtrasNoIncluidos);
        domain.setWithConfirmation(withConfirmation);
        domain.setWithReConfirmation(withReConfirmation);
        domain.setObservation(observation);
        domain.setObservationCupon(observationCupon);
        domain.setCantPax(cantPax);
        domain.setCantFree(cantFree);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTc(tc);
        domain.setTcLocal(tcLocal);
        domain.setGuide(guide);
        domain.setTotalPax(totalPax);
        domain.setValueConfirmation(valueConfirmation);
        domain.setValueReConfirmation(valueReConfirmation);
        domain.setStateBooking(stateBooking);
        return domain;
    }

    public RegisterBookingBaseDomain to(SupplierTypeDomain supplierType,
                                        SupplierDomain supplier,
                                        RegisterGroupDomain registerGroup,
                                        CustomerDomain customer,
                                        NationalityDomain nationality,
                                        EmployeeDomain employee) {
        RegisterBookingBaseDomain domain = new RegisterBookingBaseDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setIdentifyConfirmation(identifyConfirmation);
        domain.setIdentifyReConfirmation(identifyReConfirmation);
        domain.setArriveAir(arriveAir);
        domain.setDepartureAir(departureAir);
        domain.setArriveAirDate(arriveAirDate);
        domain.setDateAperture(dateAperture);
        domain.setDepartureAirDate(departureAirDate);
        domain.setDateConfirmation(dateConfirmation);
        domain.setConfirmation(confirmation);
        domain.setDateReConfirmation(dateReConfirmation);
        domain.setReConfirmation(reConfirmation);
        domain.setPrePago(prePago);
        domain.setGastosExtrasNoIncluidos(gastosExtrasNoIncluidos);
        domain.setWithConfirmation(withConfirmation);
        domain.setWithReConfirmation(withReConfirmation);
        domain.setObservation(observation);
        domain.setObservationCupon(observationCupon);
        domain.setCantPax(cantPax);
        domain.setCantFree(cantFree);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTc(tc);
        domain.setTcLocal(tcLocal);
        domain.setGuide(guide);
        domain.setTotalPax(totalPax);
        domain.setValueConfirmation(valueConfirmation);
        domain.setValueReConfirmation(valueReConfirmation);
        domain.setStateBooking(stateBooking);

        if (supplierType != null)
            domain.setSupplierType(supplierType);
        if (supplier != null)
            domain.setSupplier(supplier);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (customer != null)
            domain.setCustomer(customer);
        if (nationality != null)
            domain.setNationality(nationality);
        if (employee != null)
            domain.setEmployee(employee);
        return domain;
    }
}
