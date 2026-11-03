package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AccessVoucherEgressDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private UserDomain user;

}
