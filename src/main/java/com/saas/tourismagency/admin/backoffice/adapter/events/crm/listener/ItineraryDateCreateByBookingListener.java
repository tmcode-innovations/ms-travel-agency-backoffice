package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.ItineraryDateCreateEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDateCreateByBookingListener implements ApplicationListener<ItineraryDateCreateEvent> {

    @Autowired
    private ItineraryDateCrmRepo itineraryDateCrmRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryDateCreateEvent event) {

        ItineraryDateDomain itineraryDateDomainDestination = new ItineraryDateDomain();
        itineraryDateDomainDestination.setName(event.getItineraryDate().getName());
        itineraryDateDomainDestination.setDescription(event.getItineraryDate().getDescription());
        itineraryDateDomainDestination.setIdentify(event.getItineraryDate().getIdentify());
        itineraryDateDomainDestination.setItineraryDate(event.getItineraryDate().getItineraryDate());
        itineraryDateDomainDestination.setIndex(event.getItineraryDate().getIndex());
        itineraryDateDomainDestination.setItemItinerary(event.getItineraryDate().getItemItinerary());
        itineraryDateDomainDestination.setQuotation(event.getItineraryDate().getRegisterGroup().getQuotation());
        itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());
        itineraryDateDomainDestination.setTours(event.getItineraryDate().getTours());

        ItineraryDateDomain createdDomain = itineraryDateCrmRepo.create(itineraryDateDomainDestination);
        event.getItineraryDate().setIdItineraryDateQuotation(createdDomain.getId());
        itineraryDateRepo.edit(event.getItineraryDate());
    }

}
