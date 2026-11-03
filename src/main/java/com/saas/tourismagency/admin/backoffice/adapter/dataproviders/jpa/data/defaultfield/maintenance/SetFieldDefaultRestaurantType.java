package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.RestaurantTypeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.RestaurantTypeDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultRestaurantType implements SetFieldDefaultEntity<RestaurantTypeEntity, RestaurantTypeDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public RestaurantTypeEntity setDefaultFieldBaseCreate(RestaurantTypeEntity restaurantTypeEntity) throws RuntimeException {
        RestaurantTypeEntity entity = (RestaurantTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(restaurantTypeEntity);
        return entity;
    }

    @Override
    public RestaurantTypeEntity setDefaultFieldBaseUpdate(RestaurantTypeEntity restaurantTypeEntity) throws RuntimeException {
        RestaurantTypeEntity entity = (RestaurantTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(restaurantTypeEntity);
        return entity;
    }

    @Override
    public RestaurantTypeEntity setDefaultFieldBaseDelete(RestaurantTypeEntity restaurantTypeEntity) throws RuntimeException {
        RestaurantTypeEntity entity = (RestaurantTypeEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(restaurantTypeEntity);
        return entity;
    }

    @Override
    public RestaurantTypeDomain setDefaultFieldBaseUpdated(RestaurantTypeDomain domainTarget, RestaurantTypeDomain domainSource) throws RuntimeException {
        RestaurantTypeDomain domain = (RestaurantTypeDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
