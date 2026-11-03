package com.saas.tourismagency.admin.backoffice.domain.booking;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingRoomDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String typeRoomBooking;

    private RegisterBookingLodgerDomain registerBookingLodger;
    private RegisterBookingLodgingDomain registerBookingLodging;
    private RegisterBookingGlampingDomain registerBookingGlamping;

    private RegisterGroupDomain registerGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof RegisterBookingRoomDomain)) return false;
        RegisterBookingRoomDomain that = (RegisterBookingRoomDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
