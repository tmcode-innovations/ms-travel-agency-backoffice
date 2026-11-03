package com.saas.tourismagency.admin.backoffice.adapter.events.booking.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryItemCrmDeleteEvent;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryItemDeleteByCrmListener implements ApplicationListener<ItineraryItemCrmDeleteEvent> {

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryItemCrmDeleteEvent event) {
        ItineraryItemDomain itineraryItemDomainDestination = itineraryItemRepo.findByIdItineraryItemQuotation(event.getItinerary().getId());
        if (itineraryItemDomainDestination != null){
            itineraryItemRepo.deleteById(itineraryItemDomainDestination.getId());
        }
    }

}
