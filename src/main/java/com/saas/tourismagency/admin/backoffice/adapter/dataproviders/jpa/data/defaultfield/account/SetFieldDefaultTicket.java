package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.TicketEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.account.TicketDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultTicket implements SetFieldDefaultEntity<TicketEntity, TicketDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public TicketEntity setDefaultFieldBaseCreate(TicketEntity entityParam) throws RuntimeException {
        TicketEntity entity = (TicketEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public TicketEntity setDefaultFieldBaseUpdate(TicketEntity entityParam) throws RuntimeException {
        TicketEntity entity = (TicketEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public TicketEntity setDefaultFieldBaseDelete(TicketEntity entityParam) throws RuntimeException {
        TicketEntity entity = (TicketEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public TicketDomain setDefaultFieldBaseUpdated(TicketDomain domainTarget, TicketDomain domainSource) throws RuntimeException {
        TicketDomain domain = (TicketDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
