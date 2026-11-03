package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemInstitutionCreateRequest {

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

    @JsonProperty("registerBookingInstitution")
    private String registerBookingInstitution;

    @JsonProperty("registerGroup")
    private String registerGroup;

    public RegisterBookingItemInstitutionDomain to() {
        RegisterBookingItemInstitutionDomain domain = new RegisterBookingItemInstitutionDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateService(dateService);
        domain.setCantPax(cantPax);

        domain.setObservation(observation);
        domain.setOtherService(otherService);

        domain.setPvt(pvt);
        domain.setSic(sic);
        domain.setDate(date);
        domain.setDateTime(dateTime);
        domain.setIdItineraryItemIdentify(idItineraryItemIdentify);
        return domain;
    }

    public RegisterBookingItemInstitutionDomain to(ServiceDomain service, RegisterBookingInstitutionDomain registerBookingInstitution, RegisterGroupDomain registerGroup) {
        RegisterBookingItemInstitutionDomain domain = new RegisterBookingItemInstitutionDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateService(dateService);
        domain.setCantPax(cantPax);

        domain.setObservation(observation);
        domain.setOtherService(otherService);

        domain.setPvt(pvt);
        domain.setSic(sic);
        domain.setDate(date);
        domain.setDateTime(dateTime);
        domain.setIdItineraryItemIdentify(idItineraryItemIdentify);

        if (service != null)
            domain.setService(service);
        if (registerBookingInstitution != null)
            domain.setRegisterBookingInstitution(registerBookingInstitution);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }
}
