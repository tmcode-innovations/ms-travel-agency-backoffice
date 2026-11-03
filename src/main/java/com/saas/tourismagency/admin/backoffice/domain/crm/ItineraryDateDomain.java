package com.saas.tourismagency.admin.backoffice.domain.crm;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDateDomain extends BaseDomain implements Serializable, Comparable<ItineraryDateDomain> {

    private String name;
    private String description;
    private String identify;

    private LocalDate itineraryDate;
    private Integer index;
    private String itemItinerary;

    private QuotationDomain quotation;

    private Set<TourDomain> tours;

    @Override
    public int compareTo(ItineraryDateDomain other) {
        int value = itineraryDate.compareTo(other.itineraryDate);
        if (value == 0){
            int value1 = index.compareTo(other.getIndex());
            return value1;
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ItineraryDateDomain)) return false;
        ItineraryDateDomain that = (ItineraryDateDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Getter(AccessLevel.NONE)
    private String itineraryDateString;

    public String getItineraryDateString(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getItineraryDate());
    }

}
