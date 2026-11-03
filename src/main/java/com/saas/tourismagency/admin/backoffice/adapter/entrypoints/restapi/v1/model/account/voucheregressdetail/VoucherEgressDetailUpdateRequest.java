package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressDetailUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("valueTypeShowSupplier")
    private String valueTypeShowSupplier;

    @JsonProperty("numberDoc")
    private String numberDoc;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("montSoles")
    private Double montSoles;

    @JsonProperty("montUsd")
    private Double montUsd;

    @JsonProperty("detractionSoles")
    private Double detractionSoles;

    @JsonProperty("detractionUsd")
    private Double detractionUsd;

    @JsonProperty("balanceSoles")
    private Double balanceSoles;

    @JsonProperty("balanceUsd")
    private Double balanceUsd;

    @JsonProperty("voucherEgress")
    private String voucherEgress;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("guideDrive")
    private String guideDrive;

    @JsonProperty("guideTransfer")
    private String guideTransfer;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("touristDestine")
    private String touristDestine;

    @JsonProperty("departmentRegion")
    private String departmentRegion;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("documentAccountType")
    private String documentAccountType;

    @JsonProperty("detraction")
    private String detraction;

    @JsonProperty("itineraryItem")
    private String itineraryItem;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    public VoucherEgressDetailDomain to() {
        VoucherEgressDetailDomain domain = new VoucherEgressDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setYear(year);
        domain.setValueTypeShowSupplier(valueTypeShowSupplier);
        domain.setNumberDoc(numberDoc);
        domain.setObservation(observation);
        domain.setDate(date);
        domain.setMontSoles(montSoles);
        domain.setMontUsd(montUsd);
        domain.setDetractionUsd(detractionUsd);
        domain.setDetractionSoles(detractionSoles);
        domain.setBalanceUsd(balanceUsd);
        domain.setBalanceSoles(balanceSoles);
        domain.setPaxTotal(paxTotal);
        return domain;
    }

    public VoucherEgressDetailDomain to(VoucherEgressDomain voucherEgress, CurrencyDomain currency, RegisterGroupDomain registerGroup, SupplierDomain supplier, GuideDomain guideDrive, GuideDomain guideTransfer, SupplierTypeDomain supplierType, TouristDestineDomain touristDestine, DepartmentRegionDomain departmentRegion, ChangeTypeDomain changeType, DocumentAccountTypeDomain documentAccountType, DetractionDomain detraction) {
        VoucherEgressDetailDomain domain = new VoucherEgressDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setYear(year);
        domain.setValueTypeShowSupplier(valueTypeShowSupplier);
        domain.setNumberDoc(numberDoc);
        domain.setObservation(observation);
        domain.setDate(date);
        domain.setMontSoles(montSoles);
        domain.setMontUsd(montUsd);
        domain.setDetractionUsd(detractionUsd);
        domain.setDetractionSoles(detractionSoles);
        domain.setBalanceUsd(balanceUsd);
        domain.setBalanceSoles(balanceSoles);
        domain.setPaxTotal(paxTotal);

        if (voucherEgress != null)
            domain.setVoucherEgress(voucherEgress);
        if (currency != null)
            domain.setCurrency(currency);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (supplier != null)
            domain.setSupplier(supplier);
        if (guideDrive != null)
            domain.setGuideDrive(guideDrive);
        if (guideTransfer != null)
            domain.setGuideTransfer(guideTransfer);
        if (supplierType != null)
            domain.setSupplierType(supplierType);
        if (touristDestine != null)
            domain.setTouristDestine(touristDestine);
        if (departmentRegion != null)
            domain.setDepartmentRegion(departmentRegion);
        if (changeType != null)
            domain.setChangeType(changeType);
        if (documentAccountType != null)
            domain.setDocumentAccountType(documentAccountType);
        if (detraction != null)
            domain.setDetraction(detraction);
        return domain;
    }

    public VoucherEgressDetailDomain to(VoucherEgressDomain voucherEgress, CurrencyDomain currency, RegisterGroupDomain registerGroup, SupplierDomain supplier, GuideDomain guideDrive, GuideDomain guideTransfer, SupplierTypeDomain supplierType, TouristDestineDomain touristDestine, DepartmentRegionDomain departmentRegion, ChangeTypeDomain changeType, DocumentAccountTypeDomain documentAccountType, DetractionDomain detraction, ItineraryItemDomain itineraryItem) {
        VoucherEgressDetailDomain domain = new VoucherEgressDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setYear(year);
        domain.setValueTypeShowSupplier(valueTypeShowSupplier);
        domain.setNumberDoc(numberDoc);
        domain.setObservation(observation);
        domain.setDate(date);
        domain.setMontSoles(montSoles);
        domain.setMontUsd(montUsd);
        domain.setDetractionUsd(detractionUsd);
        domain.setDetractionSoles(detractionSoles);
        domain.setBalanceUsd(balanceUsd);
        domain.setBalanceSoles(balanceSoles);
        domain.setPaxTotal(paxTotal);

        if (voucherEgress != null)
            domain.setVoucherEgress(voucherEgress);
        if (currency != null)
            domain.setCurrency(currency);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (supplier != null)
            domain.setSupplier(supplier);
        if (guideDrive != null)
            domain.setGuideDrive(guideDrive);
        if (guideTransfer != null)
            domain.setGuideTransfer(guideTransfer);
        if (supplierType != null)
            domain.setSupplierType(supplierType);
        if (touristDestine != null)
            domain.setTouristDestine(touristDestine);
        if (departmentRegion != null)
            domain.setDepartmentRegion(departmentRegion);
        if (changeType != null)
            domain.setChangeType(changeType);
        if (documentAccountType != null)
            domain.setDocumentAccountType(documentAccountType);
        if (detraction != null)
            domain.setDetraction(detraction);
        if (itineraryItem != null)
            domain.setItineraryItem(itineraryItem);
        return domain;
    }
}
