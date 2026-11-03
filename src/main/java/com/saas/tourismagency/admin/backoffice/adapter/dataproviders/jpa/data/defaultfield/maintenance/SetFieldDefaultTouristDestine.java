package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TouristDestineEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultTouristDestine implements SetFieldDefaultEntity<TouristDestineEntity, TouristDestineDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public TouristDestineEntity setDefaultFieldBaseCreate(TouristDestineEntity touristDestineEntity) throws RuntimeException {
        TouristDestineEntity entity = (TouristDestineEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(touristDestineEntity);
        return entity;
    }

    @Override
    public TouristDestineEntity setDefaultFieldBaseUpdate(TouristDestineEntity touristDestineEntity) throws RuntimeException {
        TouristDestineEntity entity = (TouristDestineEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(touristDestineEntity);
        return entity;
    }

    @Override
    public TouristDestineEntity setDefaultFieldBaseDelete(TouristDestineEntity touristDestineEntity) throws RuntimeException {
        TouristDestineEntity entity = (TouristDestineEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(touristDestineEntity);
        return entity;
    }

    @Override
    public TouristDestineDomain setDefaultFieldBaseUpdated(TouristDestineDomain domainTarget, TouristDestineDomain domainSource) throws RuntimeException {
        TouristDestineDomain domain = (TouristDestineDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
