package com.saas.tourismagency.admin.backoffice.adapter.events.booking.event;

import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import org.springframework.context.ApplicationEvent;

public class ItineraryDateDeleteEvent extends ApplicationEvent {

    private ItineraryDateDomain itineraryDate;

    public ItineraryDateDeleteEvent(Object source, ItineraryDateDomain itineraryDate) {
        super(source);
        this.itineraryDate = itineraryDate;
    }

    public ItineraryDateDomain getItineraryDate() {
        return itineraryDate;
    }

}
