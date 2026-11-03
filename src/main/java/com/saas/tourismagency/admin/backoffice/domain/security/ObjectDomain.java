package com.saas.tourismagency.admin.backoffice.domain.security;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ObjectDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String entity;
    private String domain;
    private Integer index;

    private AppDomain app;
}
