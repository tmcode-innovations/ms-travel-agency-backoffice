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
public class UserDomain extends BaseDomain implements Serializable {

    private String userName;
    private String email;
    private String fullName;
    private String description;

    private Set<ProfileDomain> profiles;

    private Set<AppDomain> apps;
}
