package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptModelPassenger {

    @EqualsAndHashCode.Include
    private Integer id;

    private PassengerBookingDomain passenger;

}
