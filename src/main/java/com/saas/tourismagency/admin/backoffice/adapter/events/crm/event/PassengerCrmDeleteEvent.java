package com.saas.tourismagency.admin.backoffice.adapter.events.crm.event;

import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import org.springframework.context.ApplicationEvent;

public class PassengerCrmDeleteEvent extends ApplicationEvent {

    private PassengerDomain passenger;

    public PassengerCrmDeleteEvent(Object source, PassengerDomain passenger) {
        super(source);
        this.passenger = passenger;
    }

    public PassengerDomain getPassenger() {
        return passenger;
    }

}
