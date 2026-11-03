package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("read")
    private Boolean read;

    @JsonProperty("type")
    private String type;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    private String employeeSource;
    private String userSource;

    private String employeeDestine;
    private String userDestine;

    public NotificationDomain to() {
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setId(id);
        notificationDomain.setName(name);
        notificationDomain.setDescription(description);
        notificationDomain.setIdentify(identify);

        notificationDomain.setSubject(subject);
        notificationDomain.setRead(read);
        notificationDomain.setType(type);
        notificationDomain.setDateTime(dateTime);
        return notificationDomain;
    }

    public NotificationDomain to(UserDomain userSourceDomain, EmployeeDomain employeeSourceDomain, UserDomain userDestineDomain, EmployeeDomain employeeDestineDomain) {
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setId(id);
        notificationDomain.setName(name);
        notificationDomain.setDescription(description);
        notificationDomain.setIdentify(identify);

        notificationDomain.setSubject(subject);
        notificationDomain.setRead(read);
        notificationDomain.setType(type);
        notificationDomain.setDateTime(dateTime);

        if (employeeSourceDomain != null)
            notificationDomain.setEmployeeSource(employeeSourceDomain);

        if (userSourceDomain != null)
            notificationDomain.setUserSource(userSourceDomain);

        if (employeeDestineDomain != null)
            notificationDomain.setEmployeeDestine(employeeDestineDomain);

        if (userDestineDomain != null)
            notificationDomain.setUserDestine(userDestineDomain);

        return notificationDomain;
    }

}
