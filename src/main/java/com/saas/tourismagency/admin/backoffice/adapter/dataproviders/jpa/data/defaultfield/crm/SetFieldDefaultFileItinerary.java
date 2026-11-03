package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.FileItineraryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultFileItinerary implements SetFieldDefaultEntity<FileItineraryEntity, FileItineraryDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public FileItineraryEntity setDefaultFieldBaseCreate(FileItineraryEntity fileItineraryEntity) throws RuntimeException {
        FileItineraryEntity entity = (FileItineraryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(fileItineraryEntity);
        return entity;
    }

    @Override
    public FileItineraryEntity setDefaultFieldBaseUpdate(FileItineraryEntity fileItineraryEntity) throws RuntimeException {
        FileItineraryEntity entity = (FileItineraryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(fileItineraryEntity);
        return entity;
    }

    @Override
    public FileItineraryEntity setDefaultFieldBaseDelete(FileItineraryEntity fileItineraryEntity) throws RuntimeException {
        FileItineraryEntity entity = (FileItineraryEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(fileItineraryEntity);
        return entity;
    }

    @Override
    public FileItineraryDomain setDefaultFieldBaseUpdated(FileItineraryDomain domainTarget, FileItineraryDomain domainSource) throws RuntimeException {
        FileItineraryDomain domain = (FileItineraryDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
