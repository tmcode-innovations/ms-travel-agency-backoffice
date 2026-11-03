package com.saas.tourismagency.admin.backoffice.adapter.events.crm.event;

import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import org.springframework.context.ApplicationEvent;

public class ItineraryItemCrmEditEvent extends ApplicationEvent {

    private ItineraryItemDomain itinerary;

    public ItineraryItemCrmEditEvent(Object source, ItineraryItemDomain itinerary) {
        super(source);
        this.itinerary = itinerary;
    }

    public ItineraryItemDomain getItinerary() {
        return itinerary;
    }

}
