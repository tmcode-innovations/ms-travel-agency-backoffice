package com.saas.tourismagency.admin.backoffice.domain.crm;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TourItemDomain extends BaseDomain implements Serializable, Comparable<TourItemDomain> {

    private String name;
    private String description;
    private String identify;

    private String observation;

    private Integer index;

    private TourDateDomain tourDate;

    private TourDomain tour;

    private SupplierTypeDomain supplierType;

    private SupplierDomain supplier;

    private ServiceDomain service;

    private String serviceGroup;

    @Override
    public int compareTo(TourItemDomain other) {
        int value = index.compareTo(other.getIndex());
        return value;
    }
}
