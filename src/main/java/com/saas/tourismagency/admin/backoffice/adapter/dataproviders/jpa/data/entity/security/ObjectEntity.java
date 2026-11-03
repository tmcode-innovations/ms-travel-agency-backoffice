package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security;


import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
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
@Document(collection = "security_objects")
public class ObjectEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String entity;
    private String domain;
    private Integer index;

    private @DocumentReference(lazy = true)
    AppEntity app;
}
