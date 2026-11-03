package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ObjectEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_notifications")
public class NotificationEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String subject;

    private Boolean read;

    private String status;

    private String type;
    private LocalDateTime dateTime;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeSource;
    private @DocumentReference(lazy = true)
    UserEntity userSource;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeDestine;
    private @DocumentReference(lazy = true)
    UserEntity userDestine;

    private String nameObjectSource;
    private String identifyObjectSource;
    private String idObjectSource;

    private @DocumentReference(lazy = true)
    ObjectEntity objectEntitySource;

    private String nameObjectDestine;
    private String identifyObjectDestine;
    private String idObjectDestine;

    private @DocumentReference(lazy = true)
    ObjectEntity objectEntityDestine;
}
