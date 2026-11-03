package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostTourItemDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private CostTourDomain costTour;

    private ItineraryDateDomain itineraryDate;

    private RegisterGroupDomain registerGroup;

    private TourDomain tour;

    private PassengerTypeDomain passengerType;

    private CostTourServiceItemDomain costTourServiceItem;

    private Integer cantPax;

    private Double pricePax;

    @Getter(AccessLevel.NONE)
    private Double amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CostTourItemDomain)) return false;
        CostTourItemDomain that = (CostTourItemDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Getter(AccessLevel.NONE)
    private String pricePaxFormat;

    public String getPricePaxFormat() {
        return NumberUtil.getImportFormat(pricePax);
    }

    @Getter(AccessLevel.NONE)
    private String amountFormat;

    public String getAmountFormat() {
        return NumberUtil.getImportFormat(amount);
    }

    public Double getAmount() {
        return cantPax * pricePax;
    }
}
