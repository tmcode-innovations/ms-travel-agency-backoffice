package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostTourDomain extends BaseDomain implements Serializable, Comparable<CostTourDomain> {

    private String name;
    private String description;
    private String identify;

    private Integer numMaxPax;

    private Double subTotalAmount;

    private RegisterGroupDomain registerGroup;

    private TourDomain tour;

    private ItineraryDateDomain itineraryDate;

    @Override
    public int compareTo(CostTourDomain other) {
        int value = itineraryDate.compareTo(other.itineraryDate);
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CostTourDomain)) return false;
        CostTourDomain that = (CostTourDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Getter(AccessLevel.NONE)
    private String subTotalAmountFormat;

    public String getSubTotalAmountFormat() {
        return NumberUtil.getImportFormat(subTotalAmount);
    }
}
