package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
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

    private RegisterGroupDomain registerGroup;

    private String idItineraryDateQuotation;

    private Set<TourDomain> tours;

    @Getter(AccessLevel.NONE)
    private String strItineraryDate;

    public String getStrItineraryDate(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getItineraryDate());
    }

    @Override
    public int compareTo(ItineraryDateDomain other) {
        ItineraryDateDomain to = other;
        if ( itineraryDate != null && to.getItineraryDate() != null ){
            int value = itineraryDate.compareTo(to.getItineraryDate());
            return value;
        }else{
            if (index != null && to.getIndex() != null){
                int value = index.compareTo(to.getIndex());
                return value;
            }else{
                return 0;
            }
        }
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
}
