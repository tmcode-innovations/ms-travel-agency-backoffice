package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DistrictEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultDistrict implements SetFieldDefaultEntity<DistrictEntity, DistrictDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public DistrictEntity setDefaultFieldBaseCreate(DistrictEntity districtEntity) throws RuntimeException {
        DistrictEntity entity = (DistrictEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(districtEntity);
        return entity;
    }

    @Override
    public DistrictEntity setDefaultFieldBaseUpdate(DistrictEntity districtEntity) throws RuntimeException {
        DistrictEntity entity = (DistrictEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(districtEntity);
        return entity;
    }

    @Override
    public DistrictEntity setDefaultFieldBaseDelete(DistrictEntity districtEntity) throws RuntimeException {
        DistrictEntity entity = (DistrictEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(districtEntity);
        return entity;
    }

    @Override
    public DistrictDomain setDefaultFieldBaseUpdated(DistrictDomain domainTarget, DistrictDomain domainSource) throws RuntimeException {
        DistrictDomain domain = (DistrictDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
