package com.saas.tourismagency.admin.backoffice.adapter.events.booking.event;

import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import org.springframework.context.ApplicationEvent;

public class ItineraryItemDeleteEvent extends ApplicationEvent {

    private ItineraryItemDomain itinerary;

    public ItineraryItemDeleteEvent(Object source, ItineraryItemDomain itinerary) {
        super(source);
        this.itinerary = itinerary;
    }

    public ItineraryItemDomain getItinerary() {
        return itinerary;
    }

}
