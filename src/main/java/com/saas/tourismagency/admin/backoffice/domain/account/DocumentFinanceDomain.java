package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
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
public class DocumentFinanceDomain extends BaseDomain implements Serializable {

    protected String name;
    protected String description;
    protected String identify;

    protected String identifyPrefix;
    protected String identifyConsecutive;

    protected String typeInheritanceDocumentFinance;

    protected LocalDate date;
    protected LocalDate dateCancel;
    protected LocalDate dateAnnulled;
    protected String address;
    protected String referenceDocument;
    protected LocalDate dateInit;
    protected LocalDate dateEnd;
    protected Integer noPax;

    protected Double subTotalPrice;
    protected Double importSubTotalUsd;
    protected Double importSubTotalSoles;
    protected Double importIgvUsd;
    protected Double importIgvSoles;
    protected Double importTotalUsd;
    protected Double importTotalSoles;

    protected String documentAccountType;
    protected String documentAccountState;

    protected DateProcessDomain dateProcess;

    protected CountryDomain country;

    protected CurrencyDomain currency;

    protected ChangeTypeDomain changeType;

    protected CustomerDomain customer;

    protected RegisterGroupDomain registerGroup;

    protected EmployeeDomain responsible;

    @Getter(AccessLevel.NONE)
    protected String subTotalPriceFormat;

    public String getSubTotalPriceFormat() {
        return NumberUtil.getImportFormat(subTotalPrice);
    }

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
    protected String importIgvUsdFormat;

    public String getImportIgvUsdFormat() {
        return NumberUtil.getImportFormat(importIgvUsd);
    }

    @Getter(AccessLevel.NONE)
    protected String importIgvSolesFormat;

    public String getImportIgvSolesFormat() {
        return NumberUtil.getImportFormat(importIgvSoles);
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
    protected String totalDocumentFinancyCurrencyFormat;

    public String getTotalDocumentFinancyCurrencyFormat() {
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
    protected Double totalDocumentFinancyCurrency;

    public Double getTotalDocumentFinancyCurrency() {
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
    private Double saleInaffectIgvSoles;

    public Double getSaleInaffectIgvSoles() {
        if (subTotalPrice != null && importIgvSoles != null && importIgvSoles > 0) {
            return subTotalPrice - importIgvSoles;
        }
        return subTotalPrice;
    }

    @Getter(AccessLevel.NONE)
    private Double saleAffectIgvSoles;

    public Double getSaleAffectIgvSoles() {
        if (importIgvSoles != null && importIgvSoles > 0) {
            return subTotalPrice;
        }
        return 0.0;
    }

}
