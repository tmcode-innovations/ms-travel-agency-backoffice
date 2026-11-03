package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DocumentAccountTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_service_package_tourists")
public class ServicePqtTouristEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Boolean affectServiceIgv;
    private Boolean costServiceIgv;

    private @DocumentReference(lazy = true)
    DocumentAccountTypeEntity documentAccountType;

    private @DocumentReference(lazy = true)
    GroupPqtTouristEntity groupPqtTourist;

    private String affectIgv;
}
