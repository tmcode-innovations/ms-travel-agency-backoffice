package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.ItineraryDateEditEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDateEditByBookingListener implements ApplicationListener<ItineraryDateEditEvent> {

    @Autowired
    private ItineraryDateCrmRepo itineraryDateCrmRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryDateEditEvent event) {

        ItineraryDateDomain itineraryDateDomainDestination = itineraryDateCrmRepo.findById(event.getItineraryDate().getIdItineraryDateQuotation());
        if (itineraryDateDomainDestination != null){
            itineraryDateDomainDestination.setName(event.getItineraryDate().getName());
            itineraryDateDomainDestination.setDescription(event.getItineraryDate().getDescription());
            itineraryDateDomainDestination.setIdentify(event.getItineraryDate().getIdentify());
            itineraryDateDomainDestination.setItineraryDate(event.getItineraryDate().getItineraryDate());
            itineraryDateDomainDestination.setIndex(event.getItineraryDate().getIndex());
            itineraryDateDomainDestination.setItemItinerary(event.getItineraryDate().getItemItinerary());
            itineraryDateDomainDestination.setQuotation(event.getItineraryDate().getRegisterGroup().getQuotation());
            itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());
            itineraryDateDomainDestination.setTours(event.getItineraryDate().getTours());

            itineraryDateCrmRepo.edit(itineraryDateDomainDestination);
        }
    }

}
