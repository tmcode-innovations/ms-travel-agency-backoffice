package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VoucherIngressDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer year;
    private String identifyOther;

    private Integer cantRegister;

    private Double importTotalSoles;
    private Double importTotalUsd;

    private LocalDate date;

    private String voucherState;

    private DateProcessDomain dateProcess;

    private ChangeTypeDomain changeType;

    private CurrencyDomain currency;

    private EmployeeDomain responsible;

    @Getter(AccessLevel.NONE)
    private String importTotalSolesFormat;

    public String getImportTotalSolesFormat() {
        return NumberUtil.getImportFormat(importTotalSoles);
    }

    @Getter(AccessLevel.NONE)
    private String importTotalUsdFormat;

    public String getImportTotalUsdFormat() {
        return NumberUtil.getImportFormat(importTotalUsd);
    }

    @Getter(AccessLevel.NONE)
    private String strDate;

    public String getStrDate(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDate());
    }
}
