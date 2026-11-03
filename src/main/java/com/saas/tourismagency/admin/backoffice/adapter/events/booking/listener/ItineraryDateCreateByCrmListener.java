package com.saas.tourismagency.admin.backoffice.adapter.events.booking.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryDateCrmCreateEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDateCreateByCrmListener implements ApplicationListener<ItineraryDateCrmCreateEvent> {

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryDateCrmCreateEvent event) {

        ItineraryDateDomain itineraryDateDomainDestination = new ItineraryDateDomain();
        itineraryDateDomainDestination.setName(event.getItineraryDate().getName());
        itineraryDateDomainDestination.setDescription(event.getItineraryDate().getDescription());
        itineraryDateDomainDestination.setIdentify(event.getItineraryDate().getIdentify());
        itineraryDateDomainDestination.setItineraryDate(event.getItineraryDate().getItineraryDate());
        itineraryDateDomainDestination.setIndex(event.getItineraryDate().getIndex());
        itineraryDateDomainDestination.setItemItinerary(event.getItineraryDate().getItemItinerary());
        itineraryDateDomainDestination.setTours(event.getItineraryDate().getTours());

        RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(event.getItineraryDate().getQuotation().getIdentifyFile());
        if (registerGroupDomain != null){
            itineraryDateDomainDestination.setRegisterGroup(registerGroupDomain);
        }

        itineraryDateDomainDestination.setIdItineraryDateQuotation(event.getItineraryDate().getId());
        itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

        itineraryDateRepo.create(itineraryDateDomainDestination);
    }

}
