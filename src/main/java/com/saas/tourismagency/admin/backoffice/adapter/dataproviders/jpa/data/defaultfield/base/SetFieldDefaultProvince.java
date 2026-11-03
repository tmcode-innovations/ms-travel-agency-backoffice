package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ProvinceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultProvince implements SetFieldDefaultEntity<ProvinceEntity, ProvinceDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ProvinceEntity setDefaultFieldBaseCreate(ProvinceEntity provinceEntity) throws RuntimeException {
        ProvinceEntity entity = (ProvinceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(provinceEntity);
        return entity;
    }

    @Override
    public ProvinceEntity setDefaultFieldBaseUpdate(ProvinceEntity provinceEntity) throws RuntimeException {
        ProvinceEntity entity = (ProvinceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(provinceEntity);
        return entity;
    }

    @Override
    public ProvinceEntity setDefaultFieldBaseDelete(ProvinceEntity provinceEntity) throws RuntimeException {
        ProvinceEntity entity = (ProvinceEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(provinceEntity);
        return entity;
    }

    @Override
    public ProvinceDomain setDefaultFieldBaseUpdated(ProvinceDomain domainTarget, ProvinceDomain domainSource) throws RuntimeException {
        ProvinceDomain domain = (ProvinceDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
