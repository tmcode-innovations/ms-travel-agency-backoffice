package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
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
public class ProformaDomain extends BaseDomain implements Serializable {

    protected String name;
    protected String description;
    protected String identify;

    protected String typeInheritanceProforma;

    protected String title;
    protected LocalDate date;
    protected String address;
    protected String reference;
    protected LocalDate dateInit;
    protected LocalDate dateEnd;
    protected Integer noPax;

    protected Double importTotalUsd;
    protected Double importTotalSoles;
    protected Double importAmortizationUsd;
    protected Double importAmortizationSoles;
    protected Double importSaldxAmortizarUsd;
    protected Double importSaldxAmortizarSoles;
    protected Double priceUnit;

    protected Integer cantRegister;

    protected LocalDate dateCancel;

    protected String proformaType;
    protected String proformaState;

    protected DateProcessDomain dateProcess;

    protected CountryDomain country;

    protected CurrencyDomain currency;

    protected ChangeTypeDomain changeType;

    protected CustomerDomain customer;

    protected RegisterGroupDomain registerGroup;

    protected EmployeeDomain employee;

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
    protected String priceUnitFormat;

    public String getPriceUnitFormat() {
        return NumberUtil.getImportFormat(importTotalSoles);
    }

    @Getter(AccessLevel.NONE)
    protected String importTotalUsdAccountFormat;

    public String getImportTotalUsdAccountFormat() {
        return NumberUtil.getImportFormat(getImportTotalUsdAccount());
    }

    @Getter(AccessLevel.NONE)
    protected Double importTotalUsdAccount;

    public Double getImportTotalUsdAccount() {
        Double result = 0.00;
        if (proformaType != null){
            if ( proformaType.equals( (ProformaTypeEnum.INVOICE.getName()) ) ){
                if (importTotalUsd != null){
                    result = 1 * importTotalUsd;
                }
            } else if ( proformaType.equals( (ProformaTypeEnum.CREDIT_NOTE.getName()) ) ){
                if (importTotalUsd != null){
                    result =  -1 * importTotalUsd;
                }
            }
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    protected String importAmortizationUsdFormat;

    public String getImportAmortizationUsdFormat() {
        return NumberUtil.getImportFormat(importAmortizationUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String importAmortizationSolesFormat;

    public String getImportAmortizationSolesFormat() {
        return NumberUtil.getImportFormat(importAmortizationSoles);
    }

    @Getter(AccessLevel.NONE)
    protected String importSaldxAmortizarUsdFormat;

    public String getImportSaldxAmortizarUsdFormat() {
        return NumberUtil.getImportFormat(importSaldxAmortizarUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String importSaldxAmortizarSolesFormat;

    public String getImportSaldxAmortizarSolesFormat() {
        return NumberUtil.getImportFormat(importSaldxAmortizarSoles);
    }

    @Getter(AccessLevel.NONE)
    private String strDateInit;

    public String getStrDateInit(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateInit());
    }

    @Getter(AccessLevel.NONE)
    private String strDateEnd;

    public String getStrDateEnd(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateEnd());
    }

    @Getter(AccessLevel.NONE)
    private String strDate;

    public String getStrDate(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDate());
    }

    @Getter(AccessLevel.NONE)
    private String strDateCancel;

    public String getStrDateCancel(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateCancel());
    }

}
