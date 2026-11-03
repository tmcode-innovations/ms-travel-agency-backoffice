package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VoucherEgressDetailItemDomain extends BaseDomain implements Serializable, Comparable<VoucherEgressDetailItemDomain> {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private String observation;

    private Double netImportUsd;
    private Double netImportSoles;
    private Double totalImportUsd;
    private Double totalImportSoles;

    private Double netGeneralUsd;
    private Double igvSfGeneral;
    private Double igvCostGeneral;
    private Double igvTotalGeneral;
    private Double invoiceSaleGeneral;
    private Double ticketSaleGeneral;
    private Double documentCollectionGeneral;

    private VoucherEgressDomain voucherEgress;

    private VoucherEgressDetailDomain voucherEgressDetail;

    private CurrencyDomain currency;

    private ServicePqtTouristDomain servicePqtTourist;

    private IgvDomain igv;

    private RegisterGroupDomain registerGroup;

    private DetractionDomain detraction;

    // Using by GURU
    private Integer paxTotal;

    private Double netImportUsdTotal;
    private Double netImportSolesTotal;
    private Double totalImportUsdTotal;
    private Double totalImportSolesTotal;

    @Getter(AccessLevel.NONE)
    private String netImportUsdFormat;

    public String getNetImportUsdFormat() {
        return NumberUtil.getImportFormat(netImportUsd);
    }

    @Getter(AccessLevel.NONE)
    private String netImportSolesFormat;

    public String getNetImportSolesFormat() {
        return NumberUtil.getImportFormat(netImportSoles);
    }

    @Getter(AccessLevel.NONE)
    private String totalImportUsdFormat;

    public String getTotalImportUsdFormat() {
        return NumberUtil.getImportFormat(totalImportUsd);
    }

    @Getter(AccessLevel.NONE)
    private String totalImportSolesFormat;

    public String getTotalImportSolesFormat() {
        return NumberUtil.getImportFormat(totalImportSoles);
    }

    @Getter(AccessLevel.NONE)
    private String netGeneralUsdFormat;

    public String getNetGeneralUsdFormat() {
        return NumberUtil.getImportFormat(netGeneralUsd);
    }

    @Getter(AccessLevel.NONE)
    private String igvSfGeneralFormat;

    public String getIgvSfGeneralFormat() {
        return NumberUtil.getImportFormat(igvSfGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String igvCostGeneralFormat;

    public String getIgvCostGeneralFormat() {
        return NumberUtil.getImportFormat(igvCostGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String igvTotalGeneralFormat;

    public String getIgvTotalGeneralFormat() {
        return NumberUtil.getImportFormat(igvTotalGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String invoiceSaleGeneralFormat;

    public String getInvoiceSaleGeneralFormat() {
        return NumberUtil.getImportFormat(invoiceSaleGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String ticketSaleGeneralFormat;

    public String getTicketSaleGeneralFormat() {
        return NumberUtil.getImportFormat(ticketSaleGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String documentCollectionGeneralFormat;

    public String getDocumentCollectionGeneralFormat() {
        return NumberUtil.getImportFormat(documentCollectionGeneral);
    }

    @Override
    public int compareTo(VoucherEgressDetailItemDomain other) {
        return Integer.compare(this.noItem, other.noItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof VoucherEgressDetailItemDomain)) return false;
        VoucherEgressDetailItemDomain that = (VoucherEgressDetailItemDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Getter(AccessLevel.NONE)
    private String strNoItemFormat;

    public String getStrNoItemFormat() {
        String result = "";
        if (noItem != null){
            String numberItem = noItem.toString();
            if (numberItem.length() == 1){
                numberItem = "00" + numberItem;
            }else if (numberItem.length() == 2){
                numberItem = "0" + numberItem;
            }
            result = numberItem;
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    private String netImportUsdTotalFormat;

    public String getNetImportUsdTotalFormat() {
        return NumberUtil.getImportFormat(netImportUsdTotal);
    }

    @Getter(AccessLevel.NONE)
    private String netImportSolesTotalFormat;

    public String getNetImportSolesTotalFormat() {
        return NumberUtil.getImportFormat(netImportSolesTotal);
    }

    @Getter(AccessLevel.NONE)
    private String totalImportUsdTotalFormat;

    public String getTotalImportUsdTotalFormat() {
        return NumberUtil.getImportFormat(totalImportUsdTotal);
    }

    @Getter(AccessLevel.NONE)
    private String totalImportSolesTotalFormat;

    public String getTotalImportSolesTotalFormat() {
        return NumberUtil.getImportFormat(totalImportSolesTotal);
    }
}
