package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class IgvDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private Boolean available;
    private Double percent;
    private LocalDate date;
    private String observation;

}
