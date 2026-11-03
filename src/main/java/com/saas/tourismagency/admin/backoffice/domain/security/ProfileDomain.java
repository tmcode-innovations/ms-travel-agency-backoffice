package com.saas.tourismagency.admin.backoffice.domain.security;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ProfileDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Set<PermissionDomain> permissions;

}
