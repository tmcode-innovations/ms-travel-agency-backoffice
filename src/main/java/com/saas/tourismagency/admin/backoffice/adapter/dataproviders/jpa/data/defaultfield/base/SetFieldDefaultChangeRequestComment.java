package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestCommentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.SetFieldDefaultEntityBase;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestCommentDomain;
import dev.mm.javaee.core.repo.SetFieldDefaultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetFieldDefaultChangeRequestComment implements SetFieldDefaultEntity<ChangeRequestCommentEntity, ChangeRequestCommentDomain> {

    @Autowired
    private SetFieldDefaultEntityBase setFieldDefaultEntityBase;

    @Override
    public ChangeRequestCommentEntity setDefaultFieldBaseCreate(ChangeRequestCommentEntity changeRequestCommentEntity) throws RuntimeException {
        ChangeRequestCommentEntity entity = (ChangeRequestCommentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseCreate(changeRequestCommentEntity);
        return entity;
    }

    @Override
    public ChangeRequestCommentEntity setDefaultFieldBaseUpdate(ChangeRequestCommentEntity changeRequestCommentEntity) throws RuntimeException {
        ChangeRequestCommentEntity entity = (ChangeRequestCommentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseUpdate(changeRequestCommentEntity);
        return entity;
    }

    @Override
    public ChangeRequestCommentEntity setDefaultFieldBaseDelete(ChangeRequestCommentEntity changeRequestCommentEntity) throws RuntimeException {
        ChangeRequestCommentEntity entity = (ChangeRequestCommentEntity) setFieldDefaultEntityBase.setDefaultFieldBaseDelete(changeRequestCommentEntity);
        return entity;
    }

    @Override
    public ChangeRequestCommentDomain setDefaultFieldBaseUpdated(ChangeRequestCommentDomain domainTarget, ChangeRequestCommentDomain domainSource) throws RuntimeException {
        ChangeRequestCommentDomain domain = (ChangeRequestCommentDomain) setFieldDefaultEntityBase.setDefaultFieldBaseUpdated(domainTarget, domainSource);
        return domain;
    }
}
