package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.embedded;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CurrencyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettingEmbeddedEntity implements Serializable {
    @Id
    protected String id;

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

    private @DocumentReference(lazy = true)
    SupplierLodgingEntity lodging;

    private @DocumentReference(lazy = true)
    SupplierLodgerEntity lodger;

    private @DocumentReference(lazy = true)
    SupplierOperatorEntity operator;

    private @DocumentReference(lazy = true)
    SupplierRestaurantEntity restaurant;

    private @DocumentReference(lazy = true)
    SupplierGuideEntity guide;

    private @DocumentReference(lazy = true)
    SupplierTransportEntity transport;

    private @DocumentReference(lazy = true)
    SupplierAirLineEntity airLine;

    private @DocumentReference(lazy = true)
    SupplierAdministrativeEntity administrative;

    private @DocumentReference(lazy = true)
    SupplierGlampingEntity glamping;

    private @DocumentReference(lazy = true)
    SupplierInstitutionEntity institution;

    private @DocumentReference(lazy = true)
    CurrencyEntity currencyUsdDefault;

    private @DocumentReference(lazy = true)
    CurrencyEntity currencySolesDefault;

    private @DocumentReference(lazy = true)
    Set<AreaEntity> areaListQuotationFilter;

    // Voucher Egress

    private @DocumentReference(lazy = true)
    CurrencyEntity currencyVoucherEgressDefault;

    private @DocumentReference(lazy = true)
    BankEntity bankVoucherEgressDefault;

    private String voucherTypeVoucherEgressDefault;

    private String valueTypeShowSupplierVoucherEgressDefault;
}
