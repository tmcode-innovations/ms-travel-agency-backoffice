package com.saas.tourismagency.admin.backoffice.domain.core.embedded;

import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettingEmbeddedDomain implements Serializable {

    private String id;
    private String name;
    private String description;

    private String quotationType;
    private String registerGroupType;
    private String generalConfigurationType;

    private String urlSaveDocumentQuotation;
    private String urlSaveDocumentBooking;
    private String urlSaveDocumentOperation;
    private String urlSaveDocumentAccount;

    private String companyNameReportIntern;
    private String companyNameReportExtern;

    private LocalDateTime lodgingCantBreakfastDateTime;
    private LocalDateTime lodgingCantLunchDateTime;
    private LocalDateTime lodgingCantDinnerDateTime;
    private LocalDateTime lodgingCantBoxLunchDateTime;

    private LocalDateTime lodgeCantBreakfastDateTime;
    private LocalDateTime lodgeCantLunchDateTime;
    private LocalDateTime lodgeCantDinnerDateTime;
    private LocalDateTime lodgeCantBoxLunchDateTime;

    private Double distributionInvoiceExpenses;
    private Double distributionTicketExpenses;

    private SupplierLodgingDomain lodging;
    private SupplierLodgerDomain lodger;
    private SupplierOperatorDomain operator;
    private SupplierRestaurantDomain restaurant;
    private SupplierGuideDomain guide;
    private SupplierTransportDomain transport;
    private SupplierAirLineDomain airLine;
    private SupplierAdministrativeDomain administrative;
    private SupplierGlampingDomain glamping;
    private SupplierInstitutionDomain institution;

    private CurrencyDomain currencyUsdDefault;
    private CurrencyDomain currencySolesDefault;

    private Set<AreaDomain> areaListQuotationFilter;

    // Voucher Egress

    private CurrencyDomain currencyVoucherEgressDefault;

    private BankDomain bankVoucherEgressDefault;

    private String voucherTypeVoucherEgressDefault;

    private String valueTypeShowSupplierVoucherEgressDefault;

    @Getter(AccessLevel.NONE)
    private VoucherTypeEnum.VoucherTypeModel voucherTypeVoucherEgressDefaultModel;

    public VoucherTypeEnum.VoucherTypeModel getVoucherTypeVoucherEgressDefaultModel() {
        VoucherTypeEnum.VoucherTypeModel result = null;
        if (StringUtils.isNotEmpty(voucherTypeVoucherEgressDefault)) {
            result = EnumMappeableService
                    .map(VoucherTypeEnum.class)
                    .stream().filter(
                    voucherTypeModel -> voucherTypeModel.name().equals(voucherTypeVoucherEgressDefault))
                    .findFirst().get();
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    private ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel valueTypeShowSupplierVoucherEgressDefaultModel;

    public ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel getValueTypeShowSupplierVoucherEgressDefaultModel() {
        ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel result = null;
        if (StringUtils.isNotEmpty(valueTypeShowSupplierVoucherEgressDefault)) {
            result = EnumMappeableService
                    .map(ValueTypeShowSupplierEnum.class)
                    .stream().filter(
                            valueTypeShowSupplierModel -> valueTypeShowSupplierModel.name().equals(valueTypeShowSupplierVoucherEgressDefault))
                    .findFirst().get();
        }
        return result;
    }
}
