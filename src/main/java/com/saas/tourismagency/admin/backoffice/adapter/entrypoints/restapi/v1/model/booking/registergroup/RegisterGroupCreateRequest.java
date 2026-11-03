package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterGroupCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("cantFree")
    private Integer cantFree;

    @JsonProperty("cantPaxAdult")
    private Integer cantPaxAdult;

    @JsonProperty("cantPaxChildren")
    private Integer cantPaxChildren;

    @JsonProperty("tC")
    private Integer tC;

    @JsonProperty("tCLocal")
    private Integer tCLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("totalPax")
    private Integer totalPax;

    @JsonProperty("dateAperture")
    private LocalDate dateAperture;

    @JsonProperty("initTour")
    private LocalDate initTour;

    @JsonProperty("endTour")
    private LocalDate endTour;

    @JsonProperty("arriveAir")
    private String arriveAir;

    @JsonProperty("departureAir")
    private String departureAir;

    @JsonProperty("cantSWB")
    private Integer cantSWB;

    @JsonProperty("cantTRP")
    private Integer cantTRP;

    @JsonProperty("cantCDP")
    private Integer cantCDP;

    @JsonProperty("cantQTP")
    private Integer cantQTP;

    @JsonProperty("cantSXTP")
    private Integer cantSXTP;

    @JsonProperty("cantTWIN")
    private Integer cantTWIN;

    @JsonProperty("cantMAT")
    private Integer cantMAT;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("program")
    private String program;

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("idiom")
    private String idiom;

    @JsonProperty("country")
    private String country;

    @JsonProperty("stateRegisterGroup")
    private String stateRegisterGroup;

    @JsonProperty("responsible")
    private String responsible;

    @JsonProperty("versionQuotation")
    private Integer versionQuotation;

    @JsonProperty("apertureWrite")
    private Boolean apertureWrite;

    @JsonProperty("link")
    private String link;

    @JsonProperty("linkBitrix")
    private String linkBitrix;

    @JsonProperty("linkWetravel")
    private String linkWetravel;

    public RegisterGroupDomain to() {
        RegisterGroupDomain domain = new RegisterGroupDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setDays(days);
        domain.setCantPax(cantPax);
        domain.setCantFree(cantFree);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTC(tC);
        domain.setTCLocal(tCLocal);
        domain.setGuide(guide);
        domain.setTotalPax(totalPax);
        domain.setDateAperture(dateAperture);
        domain.setInitTour(initTour);
        domain.setEndTour(endTour);
        domain.setArriveAir(arriveAir);
        domain.setDepartureAir(departureAir);
        domain.setCantSWB(cantSWB);
        domain.setCantTRP(cantTRP);
        domain.setCantCDP(cantCDP);
        domain.setCantQTP(cantQTP);
        domain.setCantSXTP(cantSXTP);
        domain.setCantTWIN(cantTWIN);
        domain.setCantMAT(cantMAT);
        domain.setObservation(observation);
        domain.setStateRegisterGroup(stateRegisterGroup);
        domain.setVersionQuotation(versionQuotation);
        domain.setApertureWrite(apertureWrite);
        domain.setLink(link);
        domain.setLinkBitrix(linkBitrix);
        domain.setLinkWetravel(linkWetravel);
        return domain;
    }

    public RegisterGroupDomain to(ProgramDomain program, QuotationDomain quotation, CustomerDomain customer, IdiomDomain idiom, EmployeeDomain responsible, CountryDomain country) {
        RegisterGroupDomain domain = new RegisterGroupDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setDays(days);
        domain.setCantPax(cantPax);
        domain.setCantFree(cantFree);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTC(tC);
        domain.setTCLocal(tCLocal);
        domain.setTotalPax(totalPax);
        domain.setDateAperture(dateAperture);
        domain.setInitTour(initTour);
        domain.setEndTour(endTour);
        domain.setArriveAir(arriveAir);
        domain.setDepartureAir(departureAir);
        domain.setCantSWB(cantSWB);
        domain.setCantTRP(cantTRP);
        domain.setCantCDP(cantCDP);
        domain.setCantQTP(cantQTP);
        domain.setCantSXTP(cantSXTP);
        domain.setCantTWIN(cantTWIN);
        domain.setCantMAT(cantMAT);
        domain.setObservation(observation);
        domain.setLink(link);
        domain.setLinkBitrix(linkBitrix);
        domain.setLinkWetravel(linkWetravel);
        if (program != null)
            domain.setProgram(program);
        if (quotation != null)
            domain.setQuotation(quotation);
        if (customer != null)
            domain.setCustomer(customer);
        if (idiom != null)
            domain.setIdiom(idiom);
        domain.setStateRegisterGroup(stateRegisterGroup);
        if (responsible != null)
            domain.setResponsible(responsible);
        if (country != null)
            domain.setCountry(country);
        domain.setVersionQuotation(versionQuotation);
        domain.setApertureWrite(apertureWrite);
        return domain;
    }
}
