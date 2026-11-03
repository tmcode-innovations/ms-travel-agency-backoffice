package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class VoucherIngressDetailDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String observation;

    private Double importSoles;
    private Double importUsd;

    private VoucherIngressDomain voucherIngress;

    private CustomerDomain customer;

    private SupplierDomain supplier;

    private RegisterGroupDomain registerGroup;

    private ChangeTypeDomain changeType;

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
    protected String strOperatorCustomer;

    public String getStrOperatorCustomer() {
        String result = null;
        if (customer != null &&
                customer.getId() != null &&
                !customer.getId().isEmpty()){
            result = customer.getName();
        }else if (supplier != null &&
                supplier.getId() != null &&
                !supplier.getId().isEmpty()){
            result = supplier.getName();
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    protected Integer typeOperatorCustomer;

    public Integer getTypeOperatorCustomer() {
        Integer result = null;
        if (customer != null &&
                customer.getId() != null &&
                !customer.getId().isEmpty()){
            result = 2;
        }else if (supplier != null &&
                supplier.getId() != null &&
                !supplier.getId().isEmpty()){
            result = 1;
        }
        return result;
    }
}
