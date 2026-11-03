package com.saas.tourismagency.admin.backoffice.adapter.events.booking.event;

import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import org.springframework.context.ApplicationEvent;

public class PassengerCreateEvent extends ApplicationEvent {

    private PassengerGroupDomain passengerGroup;

    public PassengerCreateEvent(Object source, PassengerGroupDomain passengerGroup) {
        super(source);
        this.passengerGroup = passengerGroup;
    }

    public PassengerGroupDomain getPassengerGroup() {
        return passengerGroup;
    }

}
