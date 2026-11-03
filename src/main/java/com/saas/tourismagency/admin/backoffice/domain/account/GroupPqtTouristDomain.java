package com.saas.tourismagency.admin.backoffice.domain.account;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class GroupPqtTouristDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

}
