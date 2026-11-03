package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherIngressDetailCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("importSoles")
    private Double importSoles;

    @JsonProperty("importUsd")
    private Double importUsd;

    @JsonProperty("voucherIngress")
    private String voucherIngress;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("changeType")
    private String changeType;

    public VoucherIngressDetailDomain to() {
        VoucherIngressDetailDomain domain = new VoucherIngressDetailDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setObservation(observation);
        domain.setImportSoles(importSoles);
        domain.setImportUsd(importUsd);
        return domain;
    }

    public VoucherIngressDetailDomain to(VoucherIngressDomain voucherIngress, CustomerDomain customer, SupplierDomain supplier, RegisterGroupDomain registerGroup, ChangeTypeDomain changeType) {
        VoucherIngressDetailDomain domain = new VoucherIngressDetailDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setObservation(observation);
        domain.setImportSoles(importSoles);
        domain.setImportUsd(importUsd);
        if (voucherIngress != null)
            domain.setVoucherIngress(voucherIngress);
        if (customer != null)
            domain.setCustomer(customer);
        if (supplier != null)
            domain.setSupplier(supplier);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (changeType != null)
            domain.setChangeType(changeType);
        return domain;
    }
}
