package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ContactEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultContact implements SetFieldDefaultEntity<ContactEntity, ContactDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ContactEntity setDefaultFieldBaseCreate(ContactEntity contactEntity) throws RuntimeException {
        ContactEntity entity = (ContactEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(contactEntity);
        return entity;
    }

    @Override
    public ContactEntity setDefaultFieldBaseUpdate(ContactEntity contactEntity) throws RuntimeException {
        ContactEntity entity = (ContactEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(contactEntity);
        return entity;
    }

    @Override
    public ContactEntity setDefaultFieldBaseDelete(ContactEntity contactEntity) throws RuntimeException {
        ContactEntity entity = (ContactEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(contactEntity);
        return entity;
    }

    @Override
    public ContactDomain setDefaultFieldBaseUpdated(ContactDomain domainTarget, ContactDomain domainSource) throws RuntimeException {
        ContactDomain domain = (ContactDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
