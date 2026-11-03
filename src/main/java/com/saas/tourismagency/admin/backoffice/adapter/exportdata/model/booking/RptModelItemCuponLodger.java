package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerDomain;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptModelItemCuponLodger {

    @EqualsAndHashCode.Include
    private Integer id;

    private List<RegisterBookingItemLodgerDomain> list;

}
