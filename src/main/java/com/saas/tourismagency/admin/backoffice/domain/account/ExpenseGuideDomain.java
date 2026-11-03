package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ExpenseGuideDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateExpenseGuide;
    private Integer nroPax;
    private String lodging;
    private Double importSubTotalUsd;
    private Double importSubTotalSoles;
    private Double importTotalUsd;
    private Double importTotalSoles;
    private Double subTotalPrice;

    private GuideDomain guideTransfer;

    private RegisterGroupDomain registerGroup;

    private SupplierLodgingDomain supplierLodging;

    private CurrencyDomain currency;

    private String expenseGuideState;

    private ChangeTypeDomain changeType;

    private DateProcessDomain dateProcess;

    @Getter(AccessLevel.NONE)
    protected String importSubTotalUsdFormat;

    public String getImportSubTotalUsdFormat() {
        return NumberUtil.getImportFormat(importSubTotalUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String importSubTotalSolesFormat;

    public String getImportSubTotalSolesFormat() {
        return NumberUtil.getImportFormat(importSubTotalSoles);
    }

    @Getter(AccessLevel.NONE)
    protected String importTotalUsdFormat;

    public String getImportTotalUsdFormat() {
        return NumberUtil.getImportFormat(importTotalUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String importTotalSolesFormat;

    public String getImportTotalSolesFormat() {
        return NumberUtil.getImportFormat(importTotalSoles);
    }

    @Getter(AccessLevel.NONE)
    protected String subTotalPriceFormat;

    public String getSubTotalPriceFormat() {
        return NumberUtil.getImportFormat(subTotalPrice);
    }

    @Getter(AccessLevel.NONE)
    protected String totalExpenseGuideCurrencyFormat;

    public String getTotalExpenseGuideCurrencyFormat() {
        String result = null;
        if (currency != null){
            if ( currency.getName().equals("SOL") ){
                result = getImportTotalSolesFormat();
            }else if ( currency.getName().equals("DOLLAR") ){
                result = getImportTotalUsdFormat();
            }
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    protected Double totalExpenseGuideCurrency;

    public Double getTotalExpenseGuideCurrency() {
        Double result = null;
        if (currency != null){
            if ( currency.getName().equals("SOL") ){
                result = getImportTotalSoles();
            }else if ( currency.getName().equals("DOLLAR") ){
                result = getImportTotalUsd();
            }
        }
        return result;
    }

}
