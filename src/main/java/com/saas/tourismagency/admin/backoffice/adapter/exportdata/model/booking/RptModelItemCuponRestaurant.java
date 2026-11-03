package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptModelItemCuponRestaurant {

    @EqualsAndHashCode.Include
    private Integer id;

    private List<RegisterBookingItemRestaurantDomain> list;

}
