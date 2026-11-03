package com.saas.tourismagency.admin.backoffice.adapter.events.crm.event;

import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import org.springframework.context.ApplicationEvent;

public class PassengerCrmCreateEvent extends ApplicationEvent {

    private PassengerDomain passenger;

    public PassengerCrmCreateEvent(Object source, PassengerDomain passengerGroup) {
        super(source);
        this.passenger = passenger;
    }

    public PassengerDomain getPassenger() {
        return passenger;
    }

}
