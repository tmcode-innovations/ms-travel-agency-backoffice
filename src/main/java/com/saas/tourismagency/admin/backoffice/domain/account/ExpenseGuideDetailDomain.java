package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ExpenseGuideDetailDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceUnit;
    private Double importUsd;
    private Double importSoles;
    private Integer cant;
    private LocalDateTime dateTimeService;

    private ExpenseGuideDomain expenseGuide;

    private ServiceDomain service;

    private OrderServiceItemDomain orderServiceItem;

    private GuideDomain guideDrive;

    @Getter(AccessLevel.NONE)
    protected String priceUnitFormat;

    public String getPriceUnitFormat() {
        return NumberUtil.getImportFormat(priceUnit);
    }

    @Getter(AccessLevel.NONE)
    protected String importSolesFormat;

    public String getImportSolesFormat() {
        return NumberUtil.getImportFormat(importSoles);
    }

    @Getter(AccessLevel.NONE)
    protected String importUsdFormat;

    public String getImportUsdFormat() {
        return NumberUtil.getImportFormat(importUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String strDateService;

    public String getStrDateService() {
        return LocalDateTimeUtil.toLocalDateTimeFormatShort(dateTimeService);
    }

    @Getter(AccessLevel.NONE)
    protected String strTimeService;

    public String getStrTimeService() {
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(dateTimeService);
    }

    @Getter(AccessLevel.NONE)
    protected String totalExpenseGuideCurrencyFormat;

    public String getTotalExpenseGuideCurrencyFormat() {
        String result = null;
        if (expenseGuide != null && expenseGuide.getCurrency() != null){
            if ( expenseGuide.getCurrency().getName().equals("SOL") ){
                result = getImportSolesFormat();
            }else if ( expenseGuide.getCurrency().getName().equals("DOLLAR") ){
                result = getImportUsdFormat();
            }
        }
        return result;
    }

}
