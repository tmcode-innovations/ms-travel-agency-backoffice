package com.saas.tourismagency.admin.backoffice.domain.crm;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCostTourDomain extends BaseDomain implements Serializable, Comparable<ProgramCostTourDomain> {

    private String name;
    private String description;
    private String identify;

    private Integer day;

    private ProgramDomain program;

    private TourDomain tour;

    private ProgramDateDomain programDate;

    @Override
    public int compareTo(ProgramCostTourDomain other) {
        int value = programDate.compareTo(other.programDate);
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ProgramCostTourDomain)) return false;
        ProgramCostTourDomain that = (ProgramCostTourDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
