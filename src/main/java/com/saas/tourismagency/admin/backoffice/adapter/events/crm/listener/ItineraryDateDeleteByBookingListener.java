package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.ItineraryDateDeleteEvent;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDateDeleteByBookingListener implements ApplicationListener<ItineraryDateDeleteEvent> {

    @Autowired
    private ItineraryDateCrmRepo itineraryDateCrmRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryDateDeleteEvent event) {
        ItineraryDateDomain itineraryDateDomainDestination = itineraryDateCrmRepo.findById(event.getItineraryDate().getIdItineraryDateQuotation());
        if (itineraryDateDomainDestination != null){
            itineraryDateCrmRepo.deleteById(itineraryDateDomainDestination.getId());
        }
    }

}
