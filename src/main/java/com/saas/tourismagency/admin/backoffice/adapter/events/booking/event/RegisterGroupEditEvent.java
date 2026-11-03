package com.saas.tourismagency.admin.backoffice.adapter.events.booking.event;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.context.ApplicationEvent;

public class RegisterGroupEditEvent extends ApplicationEvent {

    private RegisterGroupDomain registerGroup;

    public RegisterGroupEditEvent(Object source, RegisterGroupDomain registerGroup) {
        super(source);
        this.registerGroup = registerGroup;
    }

    public RegisterGroupDomain getRegisterGroup() {
        return registerGroup;
    }

}
