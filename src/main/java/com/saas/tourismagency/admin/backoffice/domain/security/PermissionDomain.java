package com.saas.tourismagency.admin.backoffice.domain.security;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PermissionDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Boolean permCreate;
    private Boolean permRead;
    private Boolean permUpdate;
    private Boolean permDelete;

    private ObjectDomain entity;

}
