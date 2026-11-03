package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressDetailItemUpdateRequest {

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

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("netImportUsd")
    private Double netImportUsd;

    @JsonProperty("netImportSoles")
    private Double netImportSoles;

    @JsonProperty("totalImportUsd")
    private Double totalImportUsd;

    @JsonProperty("totalImportSoles")
    private Double totalImportSoles;

    @JsonProperty("netGeneralUsd")
    private Double netGeneralUsd;

    @JsonProperty("igvSfGeneral")
    private Double igvSfGeneral;

    @JsonProperty("igvCostGeneral")
    private Double igvCostGeneral;

    @JsonProperty("igvTotalGeneral")
    private Double igvTotalGeneral;

    @JsonProperty("invoiceSaleGeneral")
    private Double invoiceSaleGeneral;

    @JsonProperty("ticketSaleGeneral")
    private Double ticketSaleGeneral;

    @JsonProperty("documentCollectionGeneral")
    private Double documentCollectionGeneral;

    @JsonProperty("voucherEgress")
    private String voucherEgress;

    @JsonProperty("voucherEgressDetail")
    private String voucherEgressDetail;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("servicePqtTourist")
    private String servicePqtTourist;

    @JsonProperty("igv")
    private String igv;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("detraction")
    private String detraction;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("netImportUsdTotal")
    private Double netImportUsdTotal;

    @JsonProperty("netImportSolesTotal")
    private Double netImportSolesTotal;

    @JsonProperty("totalImportUsdTotal")
    private Double totalImportUsdTotal;

    @JsonProperty("totalImportSolesTotal")
    private Double totalImportSolesTotal;

    public VoucherEgressDetailItemDomain to() {
        VoucherEgressDetailItemDomain domain = new VoucherEgressDetailItemDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setObservation(observation);
        domain.setNetImportUsd(netImportUsd);
        domain.setNetImportSoles(netImportSoles);
        domain.setTotalImportUsd(totalImportUsd);
        domain.setTotalImportSoles(totalImportSoles);
        domain.setNetGeneralUsd(netGeneralUsd);
        domain.setIgvSfGeneral(igvSfGeneral);
        domain.setIgvCostGeneral(igvCostGeneral);
        domain.setIgvTotalGeneral(igvTotalGeneral);
        domain.setInvoiceSaleGeneral(invoiceSaleGeneral);
        domain.setTicketSaleGeneral(ticketSaleGeneral);
        domain.setDocumentCollectionGeneral(documentCollectionGeneral);
        domain.setPaxTotal(paxTotal);
        domain.setPaxTotal(paxTotal);
        domain.setNetImportUsdTotal(netImportUsdTotal);
        domain.setNetImportSolesTotal(netImportSolesTotal);
        domain.setTotalImportUsdTotal(totalImportUsdTotal);
        domain.setTotalImportSolesTotal(totalImportSolesTotal);
        return domain;
    }

    public VoucherEgressDetailItemDomain to(VoucherEgressDomain voucherEgress, VoucherEgressDetailDomain voucherEgressDetail, CurrencyDomain currency, ServicePqtTouristDomain servicePqtTourist, IgvDomain igv, RegisterGroupDomain registerGroup, DetractionDomain detraction) {
        VoucherEgressDetailItemDomain domain = new VoucherEgressDetailItemDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setObservation(observation);
        domain.setNetImportUsd(netImportUsd);
        domain.setNetImportSoles(netImportSoles);
        domain.setTotalImportUsd(totalImportUsd);
        domain.setTotalImportSoles(totalImportSoles);
        domain.setNetGeneralUsd(netGeneralUsd);
        domain.setIgvSfGeneral(igvSfGeneral);
        domain.setIgvCostGeneral(igvCostGeneral);
        domain.setIgvTotalGeneral(igvTotalGeneral);
        domain.setInvoiceSaleGeneral(invoiceSaleGeneral);
        domain.setTicketSaleGeneral(ticketSaleGeneral);
        domain.setDocumentCollectionGeneral(documentCollectionGeneral);
        domain.setPaxTotal(paxTotal);
        domain.setPaxTotal(paxTotal);
        domain.setNetImportUsdTotal(netImportUsdTotal);
        domain.setNetImportSolesTotal(netImportSolesTotal);
        domain.setTotalImportUsdTotal(totalImportUsdTotal);
        domain.setTotalImportSolesTotal(totalImportSolesTotal);

        if (voucherEgress != null)
            domain.setVoucherEgress(voucherEgress);
        if (voucherEgressDetail != null)
            domain.setVoucherEgressDetail(voucherEgressDetail);
        if (currency != null)
            domain.setCurrency(currency);
        if (servicePqtTourist != null)
            domain.setServicePqtTourist(servicePqtTourist);
        if (igv != null)
            domain.setIgv(igv);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (detraction != null)
            domain.setDetraction(detraction);

        return domain;
    }

}
