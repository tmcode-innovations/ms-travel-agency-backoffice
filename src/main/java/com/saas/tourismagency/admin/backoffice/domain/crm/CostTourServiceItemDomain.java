package com.saas.tourismagency.admin.backoffice.domain.crm;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostTourServiceItemDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String observation;

    private CostTourDomain costTour;

    private ItineraryDateDomain itineraryDate;

    private QuotationDomain quotation;

    private TourDomain tour;

    private SupplierTypeDomain supplierType;

    private SupplierDomain supplier;

    private ServiceDomain service;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CostTourServiceItemDomain)) return false;
        CostTourServiceItemDomain that = (CostTourServiceItemDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
