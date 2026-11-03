package com.saas.tourismagency.admin.backoffice.adapter.events.booking.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.PassengerCrmEditEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PassengerEditByCrmListener implements ApplicationListener<PassengerCrmEditEvent> {

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(PassengerCrmEditEvent event) {

        PassengerGroupDomain passengerDomainDestination = passengerGroupRepo.findByIdPassengerQuotation(event.getPassenger().getId());
        if (passengerDomainDestination != null){
            passengerDomainDestination.setName(event.getPassenger().getName());
            passengerDomainDestination.setDescription(event.getPassenger().getDescription());
            passengerDomainDestination.setIdentify(event.getPassenger().getIdentify());
            passengerDomainDestination.setSocialMediaInformation(event.getPassenger().getSocialMediaInformation());
            passengerDomainDestination.setWebSiteInformation(event.getPassenger().getWebSiteInformation());
            passengerDomainDestination.setContactInformation(event.getPassenger().getContactInformation());
            passengerDomainDestination.setAddress(event.getPassenger().getAddress());
            passengerDomainDestination.setDateInit(event.getPassenger().getDateInit());
            passengerDomainDestination.setDateEnd(event.getPassenger().getDateEnd());
            passengerDomainDestination.setDocument(event.getPassenger().getDocument());
            passengerDomainDestination.setObservation(event.getPassenger().getObservation());
            passengerDomainDestination.setImageUrl(event.getPassenger().getImageUrl());
            passengerDomainDestination.setTitlePerson(event.getPassenger().getTitlePerson());
            passengerDomainDestination.setTypePassengerTcBooking(event.getPassenger().getTypePassengerTcBooking());
            passengerDomainDestination.setPerson(event.getPassenger().getPerson());
            passengerDomainDestination.setNationality(event.getPassenger().getNationality());
            passengerDomainDestination.setDocumentType(event.getPassenger().getDocumentType());

            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(event.getPassenger().getQuotation().getIdentifyFile());
            if (registerGroupDomain != null){
                passengerDomainDestination.setRegisterGroup(registerGroupDomain);
            }

            passengerDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

            passengerGroupRepo.edit(passengerDomainDestination);
        }
    }

}
