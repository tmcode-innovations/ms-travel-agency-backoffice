package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRoomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRoomDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRegisterBookingRoom implements SetFieldDefaultEntity<RegisterBookingRoomEntity, RegisterBookingRoomDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RegisterBookingRoomEntity setDefaultFieldBaseCreate(RegisterBookingRoomEntity entityParam) throws RuntimeException {
        RegisterBookingRoomEntity entity = (RegisterBookingRoomEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingRoomEntity setDefaultFieldBaseUpdate(RegisterBookingRoomEntity entityParam) throws RuntimeException {
        RegisterBookingRoomEntity entity = (RegisterBookingRoomEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingRoomEntity setDefaultFieldBaseDelete(RegisterBookingRoomEntity entityParam) throws RuntimeException {
        RegisterBookingRoomEntity entity = (RegisterBookingRoomEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(entityParam);
        return entity;
    }

    @Override
    public RegisterBookingRoomDomain setDefaultFieldBaseUpdated(RegisterBookingRoomDomain domainTarget, RegisterBookingRoomDomain domainSource) throws RuntimeException {
        RegisterBookingRoomDomain domain = (RegisterBookingRoomDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
