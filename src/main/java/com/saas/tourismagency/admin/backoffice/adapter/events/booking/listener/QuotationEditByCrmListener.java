package com.saas.tourismagency.admin.backoffice.adapter.events.booking.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.QuotationEditEvent;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class QuotationEditByCrmListener implements ApplicationListener<QuotationEditEvent> {

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(QuotationEditEvent event) {

        RegisterGroupDomain registerGroupDomain = registerGroupRepo.getByQuotation(event.getQuotation().getId());
        if (registerGroupDomain != null){
            registerGroupDomain.setInitTour(event.getQuotation().getDateInitTour());
            registerGroupDomain.setEndTour(event.getQuotation().getDateEndTour());
            registerGroupDomain.setDays(event.getQuotation().getCantDayTour());
            registerGroupRepo.edit(registerGroupDomain);
        }
    }

}
