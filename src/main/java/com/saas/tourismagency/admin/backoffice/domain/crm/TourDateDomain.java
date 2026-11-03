package com.saas.tourismagency.admin.backoffice.domain.crm;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TourDateDomain extends BaseDomain implements Serializable, Comparable<TourDateDomain> {

    private String name;
    private String description;
    private String identify;

    private Integer day;

    private TourDomain tour;

    @Override
    public int compareTo(TourDateDomain other) {
        int value = day.compareTo(other.getDay());
        return value;
    }
}
