package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ServicePqtTouristDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Boolean affectServiceIgv;
    private Boolean costServiceIgv;

    private DocumentAccountTypeDomain documentAccountType;
    private GroupPqtTouristDomain groupPqtTourist;
    private String affectIgv;
}
