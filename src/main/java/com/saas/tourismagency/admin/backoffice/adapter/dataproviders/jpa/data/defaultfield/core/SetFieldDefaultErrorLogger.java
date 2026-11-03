package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.ErrorLoggerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.core.ErrorLoggerDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultErrorLogger implements SetFieldDefaultEntity<ErrorLoggerEntity, ErrorLoggerDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ErrorLoggerEntity setDefaultFieldBaseCreate(ErrorLoggerEntity errorLoggerEntity) throws RuntimeException {
        ErrorLoggerEntity entity = (ErrorLoggerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(errorLoggerEntity);
        return entity;
    }

    @Override
    public ErrorLoggerEntity setDefaultFieldBaseUpdate(ErrorLoggerEntity errorLoggerEntity) throws RuntimeException {
        ErrorLoggerEntity entity = (ErrorLoggerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(errorLoggerEntity);
        return entity;
    }

    @Override
    public ErrorLoggerEntity setDefaultFieldBaseDelete(ErrorLoggerEntity errorLoggerEntity) throws RuntimeException {
        ErrorLoggerEntity entity = (ErrorLoggerEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(errorLoggerEntity);
        return entity;
    }

    @Override
    public ErrorLoggerDomain setDefaultFieldBaseUpdated(ErrorLoggerDomain domainTarget, ErrorLoggerDomain domainSource) throws RuntimeException {
        ErrorLoggerDomain domain = (ErrorLoggerDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }

}
