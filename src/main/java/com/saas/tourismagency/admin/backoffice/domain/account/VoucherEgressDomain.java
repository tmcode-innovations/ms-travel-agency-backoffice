package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VoucherEgressDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer year;
    private Integer cantRegister;
    private String noCheck;

    private String voucherState;
    private String voucherType;

    private Double importTotalSoles;
    private Double importTotalUsd;

    private Double importSoles;
    private Double importUsd;

    private Double detractionSoles;
    private Double detractionUsd;

    private LocalDate date;
    private LocalDate dateCancel;

    private Boolean voucherDetraction;

    private DateProcessDomain dateProcess;

    private ChangeTypeDomain changeType;

    private CurrencyDomain currency;

    private EmployeeDomain responsible;

    private BankDomain bank;

    private VoucherEgressDomain parent;

    private DetractionDomain detraction;

    private RegisterGroupDomain registerGroup;

    private SupplierDomain supplier;

    private SupplierTypeDomain supplierType;

    private DepartmentRegionDomain departmentRegion;

    private String valueTypeShowSupplier;

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
    private String detractionSolesFormat;

    public String getDetractionSolesFormat() {
        return NumberUtil.getImportFormat(detractionSoles);
    }

    @Getter(AccessLevel.NONE)
    private String detractionUsdFormat;

    public String getDetractionUsdFormat() {
        return NumberUtil.getImportFormat(detractionUsd);
    }

    @Getter(AccessLevel.NONE)
    private String strDate;

    public String getStrDate(){
        return LocalDateTimeUtil.toLocalDateFormatShort(date);
    }

    @Getter(AccessLevel.NONE)
    private String strDateCancel;

    public String getStrDateCancel(){
        return LocalDateTimeUtil.toLocalDateFormatShort(dateCancel);
    }

    @Getter(AccessLevel.NONE)
    private String strDetraction;

    public String getStrDetraction(){
        if (voucherDetraction)
            return "D";
        else
            return "";
    }

    @Getter(AccessLevel.NONE)
    private String voucherTypeDescription;

    public String getVoucherTypeDescription(){
        if (voucherType != null && !voucherType.isEmpty()){
            VoucherTypeEnum.VoucherTypeModel voucherTypeModelData = EnumMappeableService
                    .map(VoucherTypeEnum.class)
                    .stream().filter(
                            voucherTypeModel -> voucherTypeModel.name().equals(voucherType))
                    .findFirst().get();
            if (voucherTypeModelData != null){
                return voucherTypeModelData.description();
            }
        }
        return "";
    }
}
