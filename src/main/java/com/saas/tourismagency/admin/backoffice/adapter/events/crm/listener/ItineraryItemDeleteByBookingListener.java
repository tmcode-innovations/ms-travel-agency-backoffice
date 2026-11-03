package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.ItineraryItemDeleteEvent;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryItemDeleteByBookingListener implements ApplicationListener<ItineraryItemDeleteEvent> {

    @Autowired
    private ItineraryItemCrmRepo itineraryItemCrmRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryItemDeleteEvent event) {
        ItineraryItemDomain itineraryItemDomainDestination = itineraryItemCrmRepo.findById(event.getItinerary().getIdItineraryItemQuotation());
        if (itineraryItemDomainDestination != null){
            itineraryItemCrmRepo.deleteById(itineraryItemDomainDestination.getId());
        }
    }

}
