package com.saas.tourismagency.admin.backoffice.domain.base;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class EntityBaseDomain extends BaseDomain implements Serializable {

    protected String name;
    protected String description;

    protected String identify;

    protected String typeInheritance;
}
