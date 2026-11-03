package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.AccessVoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessVoucherEgressCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("user")
    private String user;

    public AccessVoucherEgressDomain to(
            UserDomain user
    ) {
        AccessVoucherEgressDomain domain = new AccessVoucherEgressDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        if (user != null)
            domain.setUser(user);
        return domain;
    }
}
