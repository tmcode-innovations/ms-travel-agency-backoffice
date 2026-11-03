package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateInit")
    private LocalDate dateInit;

    @JsonProperty("dateEnd")
    private LocalDate dateEnd;

    @JsonProperty("noPax")
    private Integer noPax;

    @JsonProperty("totalProform")
    private Double totalProform;

    @JsonProperty("totalProfNCred")
    private Double totalProfNCred;

    @JsonProperty("totalDataProform")
    private Double totalDataProform;

    @JsonProperty("totalVoucherIngress")
    private Double totalVoucherIngress;

    @JsonProperty("airValue")
    private Double airValue;

    @JsonProperty("totalAirValue")
    private Double totalAirValue;

    @JsonProperty("dcInvoice")
    private Double dcInvoice;

    @JsonProperty("dcBoleta")
    private Double dcBoleta;

    @JsonProperty("dcCobranza")
    private Double dcCobranza;

    @JsonProperty("dcVtaPqt")
    private Double dcVtaPqt;

    @JsonProperty("duUtility")
    private Double duUtility;

    @JsonProperty("distrUtiliInvoiceGastos")
    private Double distrUtiliInvoiceGastos;

    @JsonProperty("distrUtiliInvoiceUtilidad")
    private Double distrUtiliInvoiceUtilidad;

    @JsonProperty("distrUtiliInvoiceTotalReal")
    private Double distrUtiliInvoiceTotalReal;

    @JsonProperty("distrUtiliInvoicePercentReal")
    private Double distrUtiliInvoicePercentReal;

    @JsonProperty("distrUtiliInvoiceVal")
    private Double distrUtiliInvoiceVal;

    @JsonProperty("distrUtiliInvoiceDiff")
    private Double distrUtiliInvoiceDiff;

    @JsonProperty("distrUtiliBoletaGastos")
    private Double distrUtiliBoletaGastos;

    @JsonProperty("distrUtiliBoletaUtilidad")
    private Double distrUtiliBoletaUtilidad;

    @JsonProperty("distrUtiliBoletaTotalReal")
    private Double distrUtiliBoletaTotalReal;

    @JsonProperty("distrUtiliBoletaPercentReal")
    private Double distrUtiliBoletaPercentReal;

    @JsonProperty("distrUtiliBoletaVal")
    private Double distrUtiliBoletaVal;

    @JsonProperty("distrUtiliBoletaDiff")
    private Double distrUtiliBoletaDiff;

    @JsonProperty("distrUtiliCobranzaGastos")
    private Double distrUtiliCobranzaGastos;

    @JsonProperty("distrUtiliCobranzaTotalReal")
    private Double distrUtiliCobranzaTotalReal;

    @JsonProperty("distrUtiliTotalGastos")
    private Double distrUtiliTotalGastos;

    @JsonProperty("distrUtiliTotalUtilidad")
    private Double distrUtiliTotalUtilidad;

    @JsonProperty("distrUtiliTotalTotalReal")
    private Double distrUtiliTotalTotalReal;

    @JsonProperty("distrUtiliTotalPercentReal")
    private Double distrUtiliTotalPercentReal;

    @JsonProperty("distrUtiliTotalVal")
    private Double distrUtiliTotalVal;

    @JsonProperty("totalValueWithoutIgv")
    private Double totalValueWithoutIgv;

    @JsonProperty("totalValueWithIgv")
    private Double totalValueWithIgv;

    @JsonProperty("totalValueInvoiceExporter")
    private Double totalValueInvoiceExporter;

    @JsonProperty("totalValueBoletaInafect")
    private Double totalValueBoletaInafect;

    @JsonProperty("totalValueDocCobranza")
    private Double totalValueDocCobranza;

    @JsonProperty("dateProcess")
    private String dateProcess;

    @JsonProperty("country")
    private String country;

    @JsonProperty("costSheetTwoState")
    private String costSheetTwoState;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("costSheetTwoPercent")
    private String costSheetTwoPercent;

    @JsonProperty("registerGroup")
    private String registerGroup;

    public CostSheetTwoDomain to() {
        CostSheetTwoDomain domain = new CostSheetTwoDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDate(date);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setNoPax(noPax);

        domain.setTotalProform(totalProform);
        domain.setTotalProfNCred(totalProfNCred);
        domain.setTotalDataProform(totalDataProform);
        domain.setTotalVoucherIngress(totalVoucherIngress);
        domain.setAirValue(airValue);
        domain.setTotalAirValue(totalAirValue);

        domain.setDcInvoice(dcInvoice);
        domain.setDcBoleta(dcBoleta);
        domain.setDcCobranza(dcCobranza);
        domain.setDcVtaPqt(dcVtaPqt);

        domain.setDuUtility(duUtility);

        domain.setDistrUtiliInvoiceGastos(distrUtiliInvoiceGastos);
        domain.setDistrUtiliInvoiceUtilidad(distrUtiliInvoiceUtilidad);
        domain.setDistrUtiliInvoiceTotalReal(distrUtiliInvoiceTotalReal);
        domain.setDistrUtiliInvoicePercentReal(distrUtiliInvoicePercentReal);
        domain.setDistrUtiliInvoiceVal(distrUtiliInvoiceVal);
        domain.setDistrUtiliInvoiceDiff(distrUtiliInvoiceDiff);

        domain.setDistrUtiliBoletaGastos(distrUtiliBoletaGastos);
        domain.setDistrUtiliBoletaUtilidad(distrUtiliBoletaUtilidad);
        domain.setDistrUtiliBoletaTotalReal(distrUtiliBoletaTotalReal);
        domain.setDistrUtiliBoletaPercentReal(distrUtiliBoletaPercentReal);
        domain.setDistrUtiliBoletaVal(distrUtiliBoletaVal);
        domain.setDistrUtiliBoletaDiff(distrUtiliBoletaDiff);

        domain.setDistrUtiliCobranzaGastos(distrUtiliCobranzaGastos);
        domain.setDistrUtiliCobranzaTotalReal(distrUtiliCobranzaTotalReal);

        domain.setDistrUtiliTotalGastos(distrUtiliTotalGastos);
        domain.setDistrUtiliTotalUtilidad(distrUtiliTotalUtilidad);
        domain.setDistrUtiliTotalTotalReal(distrUtiliTotalTotalReal);
        domain.setDistrUtiliTotalPercentReal(distrUtiliTotalPercentReal);
        domain.setDistrUtiliTotalVal(distrUtiliTotalVal);

        domain.setTotalValueWithoutIgv(totalValueWithoutIgv);
        domain.setTotalValueWithIgv(totalValueWithIgv);
        domain.setTotalValueInvoiceExporter(totalValueInvoiceExporter);
        domain.setTotalValueBoletaInafect(totalValueBoletaInafect);
        domain.setTotalValueDocCobranza(totalValueDocCobranza);

        domain.setCostSheetTwoState(costSheetTwoState);

        return domain;
    }

    public CostSheetTwoDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            CostSheetTwoPercentDomain costSheetTwoPercentDomain,
            RegisterGroupDomain registerGroupDomain
    ) {
        CostSheetTwoDomain domain = new CostSheetTwoDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDate(date);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setNoPax(noPax);

        domain.setTotalProform(totalProform);
        domain.setTotalProfNCred(totalProfNCred);
        domain.setTotalDataProform(totalDataProform);
        domain.setTotalVoucherIngress(totalVoucherIngress);
        domain.setAirValue(airValue);
        domain.setTotalAirValue(totalAirValue);

        domain.setDcInvoice(dcInvoice);
        domain.setDcBoleta(dcBoleta);
        domain.setDcCobranza(dcCobranza);
        domain.setDcVtaPqt(dcVtaPqt);

        domain.setDuUtility(duUtility);

        domain.setDistrUtiliInvoiceGastos(distrUtiliInvoiceGastos);
        domain.setDistrUtiliInvoiceUtilidad(distrUtiliInvoiceUtilidad);
        domain.setDistrUtiliInvoiceTotalReal(distrUtiliInvoiceTotalReal);
        domain.setDistrUtiliInvoicePercentReal(distrUtiliInvoicePercentReal);
        domain.setDistrUtiliInvoiceVal(distrUtiliInvoiceVal);
        domain.setDistrUtiliInvoiceDiff(distrUtiliInvoiceDiff);

        domain.setDistrUtiliBoletaGastos(distrUtiliBoletaGastos);
        domain.setDistrUtiliBoletaUtilidad(distrUtiliBoletaUtilidad);
        domain.setDistrUtiliBoletaTotalReal(distrUtiliBoletaTotalReal);
        domain.setDistrUtiliBoletaPercentReal(distrUtiliBoletaPercentReal);
        domain.setDistrUtiliBoletaVal(distrUtiliBoletaVal);
        domain.setDistrUtiliBoletaDiff(distrUtiliBoletaDiff);

        domain.setDistrUtiliCobranzaGastos(distrUtiliCobranzaGastos);
        domain.setDistrUtiliCobranzaTotalReal(distrUtiliCobranzaTotalReal);

        domain.setDistrUtiliTotalGastos(distrUtiliTotalGastos);
        domain.setDistrUtiliTotalUtilidad(distrUtiliTotalUtilidad);
        domain.setDistrUtiliTotalTotalReal(distrUtiliTotalTotalReal);
        domain.setDistrUtiliTotalPercentReal(distrUtiliTotalPercentReal);
        domain.setDistrUtiliTotalVal(distrUtiliTotalVal);

        domain.setTotalValueWithoutIgv(totalValueWithoutIgv);
        domain.setTotalValueWithIgv(totalValueWithIgv);
        domain.setTotalValueInvoiceExporter(totalValueInvoiceExporter);
        domain.setTotalValueBoletaInafect(totalValueBoletaInafect);
        domain.setTotalValueDocCobranza(totalValueDocCobranza);

        domain.setCostSheetTwoState(costSheetTwoState);

        if (dateProcessDomain != null)
            domain.setDateProcess(dateProcessDomain);

        if (countryDomain != null)
            domain.setCountry(countryDomain);

        if (currencyDomain != null)
            domain.setCurrency(currencyDomain);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        if (customerDomain != null)
            domain.setCustomer(customerDomain);

        if (costSheetTwoPercentDomain != null)
            domain.setCostSheetTwoPercent(costSheetTwoPercentDomain);

        if (registerGroupDomain != null)
            domain.setRegisterGroup(registerGroupDomain);

        return domain;
    }
}
