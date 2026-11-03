package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.PassengerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.PassengerCreateEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PassengerCreateByBookingListener implements ApplicationListener<PassengerCreateEvent> {

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(PassengerCreateEvent event) {

        PassengerDomain passengerDomainDestination = new PassengerDomain();
        passengerDomainDestination.setName(event.getPassengerGroup().getName());
        passengerDomainDestination.setDescription(event.getPassengerGroup().getDescription());
        passengerDomainDestination.setIdentify(event.getPassengerGroup().getIdentify());
        passengerDomainDestination.setSocialMediaInformation(event.getPassengerGroup().getSocialMediaInformation());
        passengerDomainDestination.setWebSiteInformation(event.getPassengerGroup().getWebSiteInformation());
        passengerDomainDestination.setContactInformation(event.getPassengerGroup().getContactInformation());
        passengerDomainDestination.setAddress(event.getPassengerGroup().getAddress());
        passengerDomainDestination.setDateInit(event.getPassengerGroup().getDateInit());
        passengerDomainDestination.setDateEnd(event.getPassengerGroup().getDateEnd());
        passengerDomainDestination.setDocument(event.getPassengerGroup().getDocument());
        passengerDomainDestination.setObservation(event.getPassengerGroup().getObservation());
        passengerDomainDestination.setImageUrl(event.getPassengerGroup().getImageUrl());
        passengerDomainDestination.setTitlePerson(event.getPassengerGroup().getTitlePerson());
        passengerDomainDestination.setTypePassengerTcBooking(event.getPassengerGroup().getTypePassengerTcBooking());
        passengerDomainDestination.setPerson(event.getPassengerGroup().getPerson());
        passengerDomainDestination.setNationality(event.getPassengerGroup().getNationality());
        passengerDomainDestination.setDocumentType(event.getPassengerGroup().getDocumentType());
        passengerDomainDestination.setQuotation(event.getPassengerGroup().getRegisterGroup().getQuotation());

        passengerDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

        PassengerDomain createdDomain = passengerRepo.create(passengerDomainDestination);
        event.getPassengerGroup().setIdPassengerQuotation(createdDomain.getId());
        passengerGroupRepo.edit(event.getPassengerGroup());
    }

}
