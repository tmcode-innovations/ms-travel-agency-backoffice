package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class DocumentFinanceDetailDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceUnit;

    private Double importUsd;

    private Double importSoles;

    private Double importIgvUsd;

    private Double importIgvSoles;

    private Double importTotalUsd;

    private Double importTotalSoles;

    private Integer cant;

    private String serviceFinance;

    private Integer noItem;

    private DocumentFinanceDomain documentFinance;

    private ChangeTypeDomain changeType;

    // Rubro
    private EntryDomain entry;

    private String affectIgv;

    @Getter(AccessLevel.NONE)
    private String priceUnitFormat;

    public String getPriceUnitFormat() {
        return NumberUtil.getImportFormat(priceUnit);
    }

    @Getter(AccessLevel.NONE)
    private String importSolesFormat;

    public String getImportSolesFormat() {
        return NumberUtil.getImportFormat(importSoles);
    }

    @Getter(AccessLevel.NONE)
    private String importUsdFormat;

    public String getImportUsdFormat() {
        return NumberUtil.getImportFormat(importUsd);
    }

    @Getter(AccessLevel.NONE)
    private String importIgvUsdFormat;

    public String getImportIgvUsdFormat() {
        return NumberUtil.getImportFormat(importIgvUsd);
    }

    @Getter(AccessLevel.NONE)
    private String importIgvSolesFormat;

    public String getImportIgvSolesFormat() {
        return NumberUtil.getImportFormat(importIgvSoles);
    }

    @Getter(AccessLevel.NONE)
    private String importTotalUsdFormat;

    public String getImportTotalUsdFormat() {
        return NumberUtil.getImportFormat(importTotalUsd);
    }

    @Getter(AccessLevel.NONE)
    private String importTotalSolesFormat;

    public String getImportTotalSolesFormat() {
        return NumberUtil.getImportFormat(importTotalSoles);
    }

    @Getter(AccessLevel.NONE)
    private String strNoItemFormat;

    public String getStrNoItemFormat() {
        if (noItem != null){
            String number_item = noItem.toString();
            if (number_item.length() == 1){
                number_item = "00"+number_item;
            }else if (number_item.length() == 2){
                number_item = "0"+number_item;
            }
            return number_item;
        }
        return "000";
    }

}
