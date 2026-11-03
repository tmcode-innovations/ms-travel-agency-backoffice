package com.saas.tourismagency.admin.backoffice.adapter.events.crm.event;

import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import org.springframework.context.ApplicationEvent;

public class PassengerCrmEditEvent extends ApplicationEvent {

    private PassengerDomain passenger;

    public PassengerCrmEditEvent(Object source, PassengerDomain passenger) {
        super(source);
        this.passenger = passenger;
    }

    public PassengerDomain getPassenger() {
        return passenger;
    }

}
