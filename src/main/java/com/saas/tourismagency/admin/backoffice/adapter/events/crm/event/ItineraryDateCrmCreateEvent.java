package com.saas.tourismagency.admin.backoffice.adapter.events.crm.event;

import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import org.springframework.context.ApplicationEvent;

public class ItineraryDateCrmCreateEvent extends ApplicationEvent {

    private ItineraryDateDomain itineraryDate;

    public ItineraryDateCrmCreateEvent(Object source, ItineraryDateDomain itineraryDate) {
        super(source);
        this.itineraryDate = itineraryDate;
    }

    public ItineraryDateDomain getItineraryDate() {
        return itineraryDate;
    }

}
