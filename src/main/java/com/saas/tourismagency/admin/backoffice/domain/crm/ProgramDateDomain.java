package com.saas.tourismagency.admin.backoffice.domain.crm;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ProgramDateDomain extends BaseDomain implements Serializable, Comparable<ProgramDateDomain>  {

    private String name;
    private String description;
    private String identify;

    private Integer day;

    private ProgramDomain program;

    private Set<TourDomain> tours;

    @Override
    public int compareTo(ProgramDateDomain other) {
        int value = day.compareTo(other.getDay());
        return value;
    }
}
