package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptModelItemCuponOperator {

    @EqualsAndHashCode.Include
    private Integer id;

    private List<RegisterBookingItemOperatorDomain> list;

}
