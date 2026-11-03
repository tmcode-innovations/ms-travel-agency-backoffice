package com.saas.tourismagency.admin.backoffice.adapter.events.booking.event;

import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import org.springframework.context.ApplicationEvent;

public class ItineraryItemCreateEvent extends ApplicationEvent {

    private ItineraryItemDomain itinerary;

    public ItineraryItemCreateEvent(Object source, ItineraryItemDomain itinerary) {
        super(source);
        this.itinerary = itinerary;
    }

    public ItineraryItemDomain getItinerary() {
        return itinerary;
    }

}
