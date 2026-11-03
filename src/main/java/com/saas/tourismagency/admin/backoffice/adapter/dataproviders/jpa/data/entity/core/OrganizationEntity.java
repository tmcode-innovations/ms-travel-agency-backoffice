package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "core_organization")
public class OrganizationEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String tenantId;
}
