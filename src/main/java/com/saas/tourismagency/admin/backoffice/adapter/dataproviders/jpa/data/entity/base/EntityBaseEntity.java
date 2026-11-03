package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

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
@Document(collection = "base_entities")
public class EntityBaseEntity extends BaseEntity implements Serializable {

    protected String name;
    protected String description;

    protected String identify;

    protected String typeInheritance;
}
