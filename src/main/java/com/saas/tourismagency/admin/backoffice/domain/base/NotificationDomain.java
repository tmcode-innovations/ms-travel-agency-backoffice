package com.saas.tourismagency.admin.backoffice.domain.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ObjectEntity;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class NotificationDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String subject;

    private String status;

    private Boolean read;
    private String type;
    private LocalDateTime dateTime;

    private EmployeeDomain employeeSource;
    private UserDomain userSource;

    private EmployeeDomain employeeDestine;
    private UserDomain userDestine;

    private String nameObjectSource;
    private String identifyObjectSource;
    private String idObjectSource;

    private ObjectDomain objectEntitySource;

    private String nameObjectDestine;
    private String identifyObjectDestine;
    private String idObjectDestine;

    private ObjectDomain objectEntityDestine;
}
