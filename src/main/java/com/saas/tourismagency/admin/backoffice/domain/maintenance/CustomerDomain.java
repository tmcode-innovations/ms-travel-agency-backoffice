package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.domain.base.EntityBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CustomerDomain extends EntityBaseDomain implements Serializable {

    private String ruc;

    private String customerType;

    private PartnerDomain partner;

}
