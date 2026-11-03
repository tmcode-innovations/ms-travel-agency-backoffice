package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.OrganizationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity {

    @Id
    protected String id;

    protected Boolean deleted;
    protected Boolean enabled;
    protected Boolean migrated;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime deletedAt;

    protected @DocumentReference(lazy = true)
    UserEntity createdBy;
    protected @DocumentReference(lazy = true)
    UserEntity updatedBy;
    protected @DocumentReference(lazy = true)
    UserEntity deletedBy;

    protected String identifierName;

    protected String tenant;
}
