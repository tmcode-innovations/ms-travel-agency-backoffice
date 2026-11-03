package com.saas.tourismagency.admin.backoffice.adapter.events.booking.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryDateCrmDeleteEvent;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDateDeleteByCrmListener implements ApplicationListener<ItineraryDateCrmDeleteEvent> {

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryDateCrmDeleteEvent event) {
        ItineraryDateDomain itineraryDateDomainDestination = itineraryDateRepo.findByIdItineraryDateQuotation(event.getItineraryDate().getId());
        if (itineraryDateDomainDestination != null){
            itineraryDateRepo.deleteById(itineraryDateDomainDestination.getId());
        }
    }

}
