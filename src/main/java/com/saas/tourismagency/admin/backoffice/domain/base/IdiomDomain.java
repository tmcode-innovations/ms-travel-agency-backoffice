package com.saas.tourismagency.admin.backoffice.domain.base;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class IdiomDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

}
