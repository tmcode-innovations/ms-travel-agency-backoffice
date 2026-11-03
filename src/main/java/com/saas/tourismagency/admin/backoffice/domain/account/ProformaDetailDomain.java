package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ProformaDetailDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceUnit;
    private Double importUsd;
    private Double importSoles;
    private Integer cant;
    private String service;
    private Integer noItem;
    private LocalDateTime dateTime;

    private ChangeTypeDomain changeType;

    private ProformaDomain proforma;

    @Getter(AccessLevel.NONE)
    protected String priceUnitFormat;

    public String getPriceUnitFormat() {
        return NumberUtil.getImportFormat(priceUnit);
    }

    @Getter(AccessLevel.NONE)
    protected String importUsdFormat;

    public String getImportUsdFormat() {
        return NumberUtil.getImportFormat(importUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String importSolesFormat;

    public String getImportSolesFormat() {
        return NumberUtil.getImportFormat(importSoles);
    }

    @Getter(AccessLevel.NONE)
    protected String strNoItemFormat;

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
