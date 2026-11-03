package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.PassengerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.PassengerDeleteEvent;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PassengerDeleteByBookingListener implements ApplicationListener<PassengerDeleteEvent> {

    @Autowired
    private PassengerRepo passengerRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(PassengerDeleteEvent event) {
        PassengerDomain passengerDomainDestination = passengerRepo.findById(event.getPassengerGroup().getIdPassengerQuotation());
        if (passengerDomainDestination != null){
            passengerRepo.deleteById(passengerDomainDestination.getId());
        }
    }

}
