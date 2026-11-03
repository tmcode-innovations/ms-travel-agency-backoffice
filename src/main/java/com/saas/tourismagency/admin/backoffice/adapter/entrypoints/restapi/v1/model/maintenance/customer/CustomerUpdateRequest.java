package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ruc")
    private String ruc;

    @JsonProperty("customerType")
    private String customerType;

    @JsonProperty("partner")
    PartnerUpdateRequest partner;

    public CustomerDomain to() {
        CustomerDomain customerDomain = new CustomerDomain();
        customerDomain.setId(id);
        customerDomain.setName(name);
        customerDomain.setDescription(description);
        customerDomain.setRuc(ruc);
        customerDomain.setCustomerType(customerType);
        return customerDomain;
    }

    public CustomerDomain to(PartnerDomain partner) {
        CustomerDomain customerDomain = new CustomerDomain();
        customerDomain.setId(id);
        customerDomain.setName(name);
        customerDomain.setDescription(description);
        customerDomain.setRuc(ruc);
        customerDomain.setCustomerType(customerType);
        if (partner != null)
            customerDomain.setPartner(partner);
        return customerDomain;
    }

}
