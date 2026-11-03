package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemOperatorUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateService")
    private LocalDate dateService;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("cantPaxAdult")
    private Integer cantPaxAdult;

    @JsonProperty("cantPaxChildren")
    private Integer cantPaxChildren;

    @JsonProperty("tc")
    private Integer tc;

    @JsonProperty("tcLocal")
    private Integer tcLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("otherService")
    private String otherService;

    @JsonProperty("pvt")
    private Boolean pvt;

    @JsonProperty("sic")
    private Boolean sic;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("idItineraryItemIdentify")
    private String idItineraryItemIdentify;

    @JsonProperty("service")
    private String service;

    @JsonProperty("registerBookingOperator")
    private String registerBookingOperator;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("generatedBy")
    private String generatedBy;

    public RegisterBookingItemOperatorDomain to() {
        RegisterBookingItemOperatorDomain domain = new RegisterBookingItemOperatorDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateService(dateService);
        domain.setCantPax(cantPax);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTc(tc);
        domain.setTcLocal(tcLocal);
        domain.setGuide(guide);

        domain.setObservation(observation);
        domain.setOtherService(otherService);

        domain.setPvt(pvt);
        domain.setSic(sic);
        domain.setDate(date);
        domain.setDateTime(dateTime);
        domain.setIdItineraryItemIdentify(idItineraryItemIdentify);
        domain.setGeneratedBy(generatedBy);
        return domain;
    }

    public RegisterBookingItemOperatorDomain to(ServiceDomain service, RegisterBookingOperatorDomain registerBookingOperator, RegisterGroupDomain registerGroup) {
        RegisterBookingItemOperatorDomain domain = new RegisterBookingItemOperatorDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateService(dateService);
        domain.setCantPax(cantPax);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTc(tc);
        domain.setTcLocal(tcLocal);
        domain.setGuide(guide);

        domain.setObservation(observation);
        domain.setOtherService(otherService);

        domain.setPvt(pvt);
        domain.setSic(sic);
        domain.setDate(date);
        domain.setDateTime(dateTime);
        domain.setIdItineraryItemIdentify(idItineraryItemIdentify);
        domain.setGeneratedBy(generatedBy);

        if (service != null)
            domain.setService(service);
        if (registerBookingOperator != null)
            domain.setRegisterBookingOperator(registerBookingOperator);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }

}
