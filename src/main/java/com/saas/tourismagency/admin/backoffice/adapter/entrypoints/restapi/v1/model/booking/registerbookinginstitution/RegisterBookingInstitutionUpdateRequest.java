package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinginstitution;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RegisterBookingInstitutionUpdateRequest extends RegisterBookingBaseUpdateRequest {

    @JsonProperty("numberService")
    private Integer numberService;

    @JsonProperty("dateInitActivity")
    private LocalDate dateInitActivity;

    @JsonProperty("dateEndActivity")
    private LocalDate dateEndActivity;

    @JsonProperty("noCupon")
    private String noCupon;

    @JsonProperty("noCuponPreImpress")
    private String noCuponPreImpress;

    @JsonProperty("cuponTcLocal")
    private Integer cuponTcLocal;

    @JsonProperty("cuponTc")
    private Integer cuponTc;

    @JsonProperty("cuponCantPaxChildren")
    private Integer cuponCantPaxChildren;

    @JsonProperty("cuponCantPaxAdult")
    private Integer cuponCantPaxAdult;

    @JsonProperty("operatorArrive")
    private String operatorArrive;

    @JsonProperty("operatorDeparture")
    private String operatorDeparture;

    @JsonProperty("supplierLodging")
    private String supplierLodging;

    public RegisterBookingInstitutionDomain to() {
        RegisterBookingInstitutionDomain domain = new RegisterBookingInstitutionDomain();
        domain.setId(id);
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
        // Operator
        domain.setNumberService(numberService);
        domain.setDateInitActivity(dateInitActivity);
        domain.setDateEndActivity(dateEndActivity);
        domain.setNoCupon(noCupon);
        domain.setNoCuponPreImpress(noCuponPreImpress);
        domain.setCuponTcLocal(cuponTcLocal);
        domain.setCuponTc(cuponTc);
        domain.setCuponCantPaxChildren(cuponCantPaxChildren);
        domain.setCuponCantPaxAdult(cuponCantPaxAdult);
        return domain;
    }

    public RegisterBookingInstitutionDomain to(
            SupplierTypeDomain supplierType,
            SupplierDomain supplier,
            RegisterGroupDomain registerGroup,
            CustomerDomain customer,
            NationalityDomain nationality,
            EmployeeDomain employee,
            SupplierOperatorDomain operatorArrive,
            SupplierOperatorDomain operatorDeparture,
            SupplierLodgingDomain supplierLodging
    ) {
        RegisterBookingInstitutionDomain domain = new RegisterBookingInstitutionDomain();
        domain.setId(id);
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

        domain.setNumberService(numberService);
        domain.setDateInitActivity(dateInitActivity);
        domain.setDateEndActivity(dateEndActivity);
        domain.setNoCupon(noCupon);
        domain.setNoCuponPreImpress(noCuponPreImpress);
        domain.setCuponTcLocal(cuponTcLocal);
        domain.setCuponTc(cuponTc);
        domain.setCuponCantPaxChildren(cuponCantPaxChildren);
        domain.setCuponCantPaxAdult(cuponCantPaxAdult);
        if (operatorArrive != null)
            domain.setOperatorArrive(operatorArrive);
        if (operatorDeparture != null)
            domain.setOperatorDeparture(operatorDeparture);
        if (supplierLodging != null)
            domain.setSupplierLodging(supplierLodging);
        return domain;
    }

}
