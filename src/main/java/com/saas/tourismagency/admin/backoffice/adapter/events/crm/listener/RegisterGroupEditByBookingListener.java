package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.RegisterGroupEditEvent;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RegisterGroupEditByBookingListener implements ApplicationListener<RegisterGroupEditEvent> {

    @Autowired
    private QuotationRepo quotationRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(RegisterGroupEditEvent event) {

        QuotationDomain quotationDomain = quotationRepo.findById(event.getRegisterGroup().getQuotation().getId());
        if (quotationDomain != null){
            quotationDomain.setDateInitTour(event.getRegisterGroup().getInitTour());
            quotationDomain.setDateEndTour(event.getRegisterGroup().getEndTour());
            quotationDomain.setCantDayTour(event.getRegisterGroup().getDays());
            quotationRepo.edit(quotationDomain);
        }
    }

}
