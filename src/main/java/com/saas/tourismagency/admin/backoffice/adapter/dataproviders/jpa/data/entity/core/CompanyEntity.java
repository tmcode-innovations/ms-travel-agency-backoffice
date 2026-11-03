package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.embedded.SettingEmbeddedEntity;
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
@Document(collection = "core_companies")
public class CompanyEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private @DocumentReference(lazy = true)
    PartnerEntity partner;

    private @DocumentReference(lazy = true)
    OrganizationEntity organization;

    private SettingEmbeddedEntity setting;

}
