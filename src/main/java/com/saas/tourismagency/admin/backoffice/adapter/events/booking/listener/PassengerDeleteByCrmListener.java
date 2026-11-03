package com.saas.tourismagency.admin.backoffice.adapter.events.booking.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.PassengerCrmDeleteEvent;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PassengerDeleteByCrmListener implements ApplicationListener<PassengerCrmDeleteEvent> {

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(PassengerCrmDeleteEvent event) {
        PassengerGroupDomain passengerDomainDestination = passengerGroupRepo.findByIdPassengerQuotation(event.getPassenger().getId());
        if (passengerDomainDestination != null){
            passengerGroupRepo.deleteById(passengerDomainDestination.getId());
        }
    }

}
