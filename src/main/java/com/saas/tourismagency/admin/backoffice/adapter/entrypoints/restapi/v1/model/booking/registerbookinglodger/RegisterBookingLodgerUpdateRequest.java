package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RegisterBookingLodgerUpdateRequest extends RegisterBookingBaseUpdateRequest {

    // Operator

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

    // Lodging

    @JsonProperty("initTour")
    private LocalDate initTour;

    @JsonProperty("endTour")
    private LocalDate endTour;

    @JsonProperty("initHourTour")
    private LocalDateTime initHourTour;

    @JsonProperty("endHourTour")
    private LocalDateTime endHourTour;

    @JsonProperty("cantSwb")
    private Integer cantSwb;

    @JsonProperty("cantSwbTcGuide")
    private Integer cantSwbTcGuide;

    @JsonProperty("cantSwbTcLocalGuide")
    private Integer cantSwbTcLocalGuide;

    @JsonProperty("cantSwbGuide")
    private Integer cantSwbGuide;

    @JsonProperty("cantTrp")
    private Integer cantTrp;

    @JsonProperty("cantCdp")
    private Integer cantCdp;

    @JsonProperty("cantQtp")
    private Integer cantQtp;

    @JsonProperty("cantSxtp")
    private Integer cantSxtp;

    @JsonProperty("cantTwin")
    private Integer cantTwin;

    @JsonProperty("cantTwinTcGuide")
    private Integer cantTwinTcGuide;

    @JsonProperty("cantTwinTcLocalGuide")
    private Integer cantTwinTcLocalGuide;

    @JsonProperty("cantTwinGuide")
    private Integer cantTwinGuide;

    @JsonProperty("cantMat")
    private Integer cantMat;

    @JsonProperty("numberIngress")
    private Integer numberIngress;

    public RegisterBookingLodgerDomain to() {
        RegisterBookingLodgerDomain domain = new RegisterBookingLodgerDomain();
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

        // Lodging
        domain.setInitTour(initTour);
        domain.setEndTour(endTour);
        domain.setInitHourTour(initHourTour);
        domain.setEndHourTour(endHourTour);
        domain.setCantSwb(cantSwb);
        domain.setCantSwbTcGuide(cantSwbTcGuide);
        domain.setCantSwbTcLocalGuide(cantSwbTcLocalGuide);
        domain.setCantSwbGuide(cantSwbGuide);
        domain.setCantTrp(cantTrp);
        domain.setCantCdp(cantCdp);
        domain.setCantQtp(cantQtp);
        domain.setCantSxtp(cantSxtp);
        domain.setCantTwin(cantTwin);
        domain.setCantTwinTcGuide(cantTwinTcGuide);
        domain.setCantTwinTcLocalGuide(cantTwinTcLocalGuide);
        domain.setCantTwinGuide(cantTwinGuide);
        domain.setCantMat(cantMat);
        domain.setNumberIngress(numberIngress);

        return domain;
    }

    public RegisterBookingLodgerDomain to(
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
        RegisterBookingLodgerDomain domain = new RegisterBookingLodgerDomain();
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
        if (operatorArrive != null)
            domain.setOperatorArrive(operatorArrive);
        if (operatorDeparture != null)
            domain.setOperatorDeparture(operatorDeparture);
        if (supplierLodging != null)
            domain.setSupplierLodging(supplierLodging);

        // Lodging
        domain.setInitTour(initTour);
        domain.setEndTour(endTour);
        domain.setInitHourTour(initHourTour);
        domain.setEndHourTour(endHourTour);
        domain.setCantSwb(cantSwb);
        domain.setCantSwbTcGuide(cantSwbTcGuide);
        domain.setCantSwbTcLocalGuide(cantSwbTcLocalGuide);
        domain.setCantSwbGuide(cantSwbGuide);
        domain.setCantTrp(cantTrp);
        domain.setCantCdp(cantCdp);
        domain.setCantQtp(cantQtp);
        domain.setCantSxtp(cantSxtp);
        domain.setCantTwin(cantTwin);
        domain.setCantTwinTcGuide(cantTwinTcGuide);
        domain.setCantTwinTcLocalGuide(cantTwinTcLocalGuide);
        domain.setCantTwinGuide(cantTwinGuide);
        domain.setCantMat(cantMat);
        domain.setNumberIngress(numberIngress);

        return domain;
    }

}
