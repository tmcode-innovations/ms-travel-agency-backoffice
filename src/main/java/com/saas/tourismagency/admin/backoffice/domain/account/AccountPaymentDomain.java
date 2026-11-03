package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AccountPaymentDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private LocalDate paymentDate;
    private String nroDocument;
    private String observation;
    private Double importMontSoles;
    private Double importMontUsd;

    private String paymentType;

    private ProformaDomain proforma;

    private BankDomain bank;

    private ChangeTypeDomain changeType;

    @Getter(AccessLevel.NONE)
    private String importMontSolesFormat;

    @Getter(AccessLevel.NONE)
    private String importMontUsdFormat;

    public String getImportMontSolesFormat(){
        return NumberUtil.getImportFormat(importMontSoles);
    }

    public String getImportMontUsdFormat(){
        return NumberUtil.getImportFormat(importMontUsd);
    }
}
