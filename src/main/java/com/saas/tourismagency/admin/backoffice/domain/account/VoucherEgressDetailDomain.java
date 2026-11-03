package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.domain.BaseDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VoucherEgressDetailDomain extends BaseDomain implements Serializable, Comparable<VoucherEgressDetailDomain> {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private Integer year;

    private String valueTypeShowSupplier;
    private String numberDoc;
    private String observation;

    private LocalDate date;

    private Double montSoles;
    private Double montUsd;

    private Double detractionSoles;
    private Double detractionUsd;

    private Double balanceSoles;
    private Double balanceUsd;

    private VoucherEgressDomain voucherEgress;

    private CurrencyDomain currency;

    private RegisterGroupDomain registerGroup;

    private SupplierDomain supplier;

    private GuideDomain guideDrive;

    private GuideDomain guideTransfer;

    private SupplierTypeDomain supplierType;

    private TouristDestineDomain touristDestine;

    private DepartmentRegionDomain departmentRegion;

    private ChangeTypeDomain changeType;

    private DocumentAccountTypeDomain documentAccountType;

    private DetractionDomain detraction;

    // Using by GURU
    private ItineraryItemDomain itineraryItem;

    // Using by GURU
    private Integer paxTotal;

    @Getter(AccessLevel.NONE)
    private String montSolesFormat;

    public String getMontSolesFormat() {
        return NumberUtil.getImportFormat(montSoles);
    }

    @Getter(AccessLevel.NONE)
    private String montUsdFormat;

    public String getMontUsdFormat() {
        return NumberUtil.getImportFormat(montUsd);
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
    private String balanceSolesFormat;

    public String getBalanceSolesFormat() {
        return NumberUtil.getImportFormat(balanceSoles);
    }

    @Getter(AccessLevel.NONE)
    private String balanceUsdFormat;

    public String getBalanceUsdFormat() {
        return NumberUtil.getImportFormat(balanceUsd);
    }

    @Override
    public int compareTo(VoucherEgressDetailDomain other) {
        return Integer.compare(this.noItem, other.noItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof VoucherEgressDetailDomain)) return false;
        VoucherEgressDetailDomain that = (VoucherEgressDetailDomain) o;
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
    private String strDate;

    public String getStrDate(){
        return LocalDateTimeUtil.toLocalDateFormatShort(date);
    }

    @Getter(AccessLevel.NONE)
    private String strOperator;

    public String getStrOperator() {
        StringBuffer result = new StringBuffer();
        if (supplier != null &&
                supplier.getId() != null &&
                !supplier.getId().isEmpty()){
            result.append(supplier.getName());
        }else if ( guideDrive != null &&
                guideDrive.getId() != null &&
                !guideDrive.getId().isEmpty() ){
            result.append(guideDrive.getPerson().getFullName());
        }else if ( guideTransfer != null &&
                guideTransfer.getId() != null &&
                !guideTransfer.getId().isEmpty() ){
            result.append(guideTransfer.getPerson().getFullName());
        }
        return result.toString();
    }

    public String getValueTypeShowSupplierDescription(){
        if (valueTypeShowSupplier != null && !valueTypeShowSupplier.isEmpty()){
            ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel valueTypeShowSupplierModelData = EnumMappeableService
                    .map(ValueTypeShowSupplierEnum.class)
                    .stream().filter(
                            valueTypeShowSupplierModel -> valueTypeShowSupplierModel.name().equals(valueTypeShowSupplier))
                    .findFirst().get();
            if (valueTypeShowSupplierModelData != null){
                return valueTypeShowSupplierModelData.description();
            }
        }
        return "";
    }
}
