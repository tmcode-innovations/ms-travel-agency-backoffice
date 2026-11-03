package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("cantDayTour")
    private Integer cantDayTour;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("paxAdult")
    private Integer paxAdult;

    @JsonProperty("paxChild")
    private Integer paxChild;

    @JsonProperty("paxTc")
    private Integer paxTc;

    @JsonProperty("paxTcLocal")
    private Integer paxTcLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("dateInitTour")
    private LocalDate dateInitTour;

    @JsonProperty("dateEndTour")
    private LocalDate dateEndTour;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("profitPercent")
    private Double profitPercent;

    @JsonProperty("otherExpenses")
    private Double otherExpenses;

    @JsonProperty("stateQuotation")
    private String stateQuotation;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("program")
    private String program;

    @JsonProperty("employee")
    private String employee;

    @JsonProperty("idiom")
    private String idiom;

    @JsonProperty("country")
    private String country;

    @JsonProperty("quotationType")
    private String quotationType;

    @JsonProperty("urlFileQuotation")
    private String urlFileQuotation;

    @JsonProperty("urlFileItinerary")
    private String urlFileItinerary;

    @JsonProperty("fileNameQuotation")
    private String fileNameQuotation;

    @JsonProperty("fileNameItinerary")
    private String fileNameItinerary;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("linkGDrive")
    private String linkGDrive;

    @JsonProperty("linkBitrix")
    private String linkBitrix;

    @JsonProperty("linkWetravel")
    private String linkWetravel;

    public QuotationDomain to() {
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setName(name);
        quotationDomain.setDescription(description);
        quotationDomain.setIdentify(identify);
        quotationDomain.setCantDayTour(cantDayTour);
        quotationDomain.setPaxTotal(paxTotal);
        quotationDomain.setPaxAdult(paxAdult);
        quotationDomain.setPaxChild(paxChild);
        quotationDomain.setPaxTc(paxTc);
        quotationDomain.setPaxTcLocal(paxTcLocal);
        quotationDomain.setGuide(guide);
        quotationDomain.setDateInitTour(dateInitTour);
        quotationDomain.setDateEndTour(dateEndTour);
        quotationDomain.setStateQuotation(stateQuotation);
        quotationDomain.setObservation(observation);
        quotationDomain.setAmount(amount);
        quotationDomain.setProfitPercent(profitPercent);
        quotationDomain.setOtherExpenses(otherExpenses);
        quotationDomain.setQuotationType(quotationType);
        quotationDomain.setUrlFileQuotation(urlFileQuotation);
        quotationDomain.setUrlFileItinerary(urlFileItinerary);
        quotationDomain.setFileNameQuotation(fileNameQuotation);
        quotationDomain.setFileNameItinerary(fileNameItinerary);
        quotationDomain.setLinkGDrive(linkGDrive);
        quotationDomain.setLinkBitrix(linkBitrix);
        quotationDomain.setLinkWetravel(linkWetravel);
        return quotationDomain;
    }

    public QuotationDomain to(CustomerDomain customer, ProgramDomain program, EmployeeDomain employee, IdiomDomain idiom, CurrencyDomain currency, CountryDomain country) {
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setName(name);
        quotationDomain.setDescription(description);
        quotationDomain.setIdentify(identify);
        quotationDomain.setCantDayTour(cantDayTour);
        quotationDomain.setPaxTotal(paxTotal);
        quotationDomain.setPaxAdult(paxAdult);
        quotationDomain.setPaxChild(paxChild);
        quotationDomain.setPaxTc(paxTc);
        quotationDomain.setPaxTcLocal(paxTcLocal);
        quotationDomain.setGuide(guide);
        quotationDomain.setDateInitTour(dateInitTour);
        quotationDomain.setDateEndTour(dateEndTour);
        quotationDomain.setStateQuotation(stateQuotation);
        quotationDomain.setObservation(observation);
        quotationDomain.setAmount(amount);
        quotationDomain.setProfitPercent(profitPercent);
        quotationDomain.setOtherExpenses(otherExpenses);
        quotationDomain.setQuotationType(quotationType);
        quotationDomain.setUrlFileQuotation(urlFileQuotation);
        quotationDomain.setUrlFileItinerary(urlFileItinerary);
        quotationDomain.setFileNameQuotation(fileNameQuotation);
        quotationDomain.setFileNameItinerary(fileNameItinerary);
        quotationDomain.setLinkGDrive(linkGDrive);
        quotationDomain.setLinkBitrix(linkBitrix);
        quotationDomain.setLinkWetravel(linkWetravel);
        if (customer != null)
            quotationDomain.setCustomer(customer);
        if (program != null)
            quotationDomain.setProgram(program);
        if (employee != null)
            quotationDomain.setEmployee(employee);
        if (idiom != null)
            quotationDomain.setIdiom(idiom);
        if (currency != null)
            quotationDomain.setCurrency(currency);
        if (country != null)
            quotationDomain.setCountry(country);
        return quotationDomain;
    }
}
