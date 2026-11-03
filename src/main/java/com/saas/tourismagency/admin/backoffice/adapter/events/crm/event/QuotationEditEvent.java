package com.saas.tourismagency.admin.backoffice.adapter.events.crm.event;

import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import org.springframework.context.ApplicationEvent;

public class QuotationEditEvent extends ApplicationEvent {

    private QuotationDomain quotation;

    public QuotationEditEvent(Object source, QuotationDomain quotation) {
        super(source);
        this.quotation = quotation;
    }

    public QuotationDomain getQuotation() {
        return quotation;
    }

}
