package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CostSheetTwoDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate date;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private Integer noPax;

    private Double totalProform;
    private Double totalProfNCred;
    private Double totalDataProform;
    private Double totalVoucherIngress;
    private Double airValue;
    private Double totalAirValue;

    private Double dcInvoice;
    private Double dcBoleta;
    private Double dcCobranza;
    private Double dcVtaPqt;

    private Double duUtility;

    private Double distrUtiliInvoiceGastos;
    private Double distrUtiliInvoiceUtilidad;
    private Double distrUtiliInvoiceTotalReal;
    private Double distrUtiliInvoicePercentReal;
    private Double distrUtiliInvoiceVal;
    private Double distrUtiliInvoiceDiff;

    private Double distrUtiliBoletaGastos;
    private Double distrUtiliBoletaUtilidad;
    private Double distrUtiliBoletaTotalReal;
    private Double distrUtiliBoletaPercentReal;
    private Double distrUtiliBoletaVal;
    private Double distrUtiliBoletaDiff;

    private Double distrUtiliCobranzaGastos;
    private Double distrUtiliCobranzaTotalReal;

    private Double distrUtiliTotalGastos;
    private Double distrUtiliTotalUtilidad;
    private Double distrUtiliTotalTotalReal;
    private Double distrUtiliTotalPercentReal;
    private Double distrUtiliTotalVal;

    private Double totalValueWithoutIgv;
    private Double totalValueWithIgv;
    private Double totalValueInvoiceExporter;
    private Double totalValueBoletaInafect;
    private Double totalValueDocCobranza;

    private String costSheetTwoState;

    private DateProcessDomain dateProcess;

    private CountryDomain country;

    private CurrencyDomain currency;

    private ChangeTypeDomain changeType;

    private CustomerDomain customer;

    private CostSheetTwoPercentDomain costSheetTwoPercent;

    private RegisterGroupDomain registerGroup;

    @Getter(AccessLevel.NONE)
    private String totalProfNCredFormat;

    public String getTotalProfNCredFormat() {
        return NumberUtil.getImportFormat(totalProfNCred);
    }

    @Getter(AccessLevel.NONE)
    private String totalProformFormat;

    public String getTotalProformFormat() {
        return NumberUtil.getImportFormat(totalProform);
    }

    @Getter(AccessLevel.NONE)
    private String totalDataProformFormat;

    public String getTotalDataProformFormat() {
        return NumberUtil.getImportFormat(totalDataProform);
    }

    @Getter(AccessLevel.NONE)
    private String totalVoucherIngressFormat;

    public String getTotalVoucherIngressFormat() {
        return NumberUtil.getImportFormat(totalVoucherIngress);
    }

    @Getter(AccessLevel.NONE)
    private String airValueFormat;

    public String getAirValueFormat() {
        return NumberUtil.getImportFormat(airValue);
    }

    @Getter(AccessLevel.NONE)
    private String totalAirValueFormat;

    public String getTotalAirValueFormat() {
        return NumberUtil.getImportFormat(totalAirValue);
    }

    @Getter(AccessLevel.NONE)
    private String dcInvoiceFormat;

    public String getDcInvoiceFormat() {
        return NumberUtil.getImportFormat(dcInvoice);
    }

    @Getter(AccessLevel.NONE)
    private String dcBoletaFormat;

    public String getDcBoletaFormat() {
        return NumberUtil.getImportFormat(dcBoleta);
    }

    @Getter(AccessLevel.NONE)
    private String dcCobranzaFormat;

    public String getDcCobranzaFormat() {
        return NumberUtil.getImportFormat(dcCobranza);
    }

    @Getter(AccessLevel.NONE)
    private String dcVtaPqtFormat;

    public String getDcVtaPqtFormat() {
        return NumberUtil.getImportFormat(dcVtaPqt);
    }

    @Getter(AccessLevel.NONE)
    private String duUtilityFormat;

    public String getDuUtilityFormat() {
        return NumberUtil.getImportFormat(duUtility);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliInvoiceGastosFormat;

    public String getDistrUtiliInvoiceGastosFormat() {
        return NumberUtil.getImportFormat(distrUtiliInvoiceGastos);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliInvoiceUtilidadFormat;

    public String getDistrUtiliInvoiceUtilidadFormat() {
        return NumberUtil.getImportFormat(distrUtiliInvoiceUtilidad);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliInvoiceTotalRealFormat;

    public String getDistrUtiliInvoiceTotalRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliInvoiceTotalReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliInvoicePercentRealFormat;

    public String getDistrUtiliInvoicePercentRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliInvoicePercentReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliInvoiceValFormat;

    public String getDistrUtiliInvoiceValFormat() {
        return NumberUtil.getImportFormat(distrUtiliInvoiceVal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliInvoiceDiffFormat;

    public String getDistrUtiliInvoiceDiffFormat() {
        return NumberUtil.getImportFormat(distrUtiliInvoiceDiff);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliBoletaGastosFormat;

    public String getDistrUtiliBoletaGastosFormat() {
        return NumberUtil.getImportFormat(distrUtiliBoletaGastos);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliBoletaUtilidadFormat;

    public String getDistrUtiliBoletaUtilidadFormat() {
        return NumberUtil.getImportFormat(distrUtiliBoletaUtilidad);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliBoletaTotalRealFormat;

    public String getDistrUtiliBoletaTotalRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliBoletaTotalReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliBoletaPercentRealFormat;

    public String getDistrUtiliBoletaPercentRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliBoletaPercentReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliBoletaValFormat;

    public String getDistrUtiliBoletaValFormat() {
        return NumberUtil.getImportFormat(distrUtiliBoletaVal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliBoletaDiffFormat;

    public String getDistrUtiliBoletaDiffFormat() {
        return NumberUtil.getImportFormat(distrUtiliBoletaDiff);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliCobranzaGastosFormat;

    public String getDistrUtiliCobranzaGastosFormat() {
        return NumberUtil.getImportFormat(distrUtiliCobranzaGastos);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliCobranzaTotalRealFormat;

    public String getDistrUtiliCobranzaTotalRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliCobranzaTotalReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliTotalGastosFormat;

    public String getDistrUtiliTotalGastosFormat() {
        return NumberUtil.getImportFormat(distrUtiliTotalGastos);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliTotalUtilidadFormat;

    public String getDistrUtiliTotalUtilidadFormat() {
        return NumberUtil.getImportFormat(distrUtiliTotalUtilidad);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliTotalTotalRealFormat;

    public String getDistrUtiliTotalTotalRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliTotalTotalReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliTotalPercentRealFormat;

    public String getDistrUtiliTotalPercentRealFormat() {
        return NumberUtil.getImportFormat(distrUtiliTotalPercentReal);
    }

    @Getter(AccessLevel.NONE)
    private String distrUtiliTotalValFormat;

    public String getDistrUtiliTotalValFormat() {
        return NumberUtil.getImportFormat(distrUtiliTotalVal);
    }

    @Getter(AccessLevel.NONE)
    private String totalValueWithoutIgvFormat;

    public String getTotalValueWithoutIgvFormat() {
        return NumberUtil.getImportFormat(totalValueWithoutIgv);
    }

    @Getter(AccessLevel.NONE)
    private String totalValueWithIgvFormat;

    public String getTotalValueWithIgvFormat() {
        return NumberUtil.getImportFormat(totalValueWithIgv);
    }

    @Getter(AccessLevel.NONE)
    private String totalValueInvoiceExporterFormat;

    public String getTotalValueInvoiceExporterFormat() {
        return NumberUtil.getImportFormat(totalValueInvoiceExporter);
    }

    @Getter(AccessLevel.NONE)
    private String totalValueBoletaInafectFormat;

    public String getTotalValueBoletaInafectFormat() {
        return NumberUtil.getImportFormat(totalValueBoletaInafect);
    }

    @Getter(AccessLevel.NONE)
    private String totalValueDocCobranzaFormat;

    public String getTotalValueDocCobranzaFormat() {
        return NumberUtil.getImportFormat(totalValueDocCobranza);
    }

}
