package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.GenderEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

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

    @JsonProperty("typeModel")
    private NotificationTypeEnum.NotificationTypeModel typeModel;

    @JsonProperty("typeDescription")
    private String typeDescription;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("dateTimeStr")
    private String dateTimeStr;

    @JsonProperty("employeeSource")
    private EmployeeResponse employeeSource;

    @JsonProperty("userSource")
    private UserResponse userSource;

    @JsonProperty("employeeDestine")
    private EmployeeResponse employeeDestine;

    @JsonProperty("userDestine")
    private UserResponse userDestine;

    @JsonProperty("objectSource")
    private ObjectDomain objectSource;

    @JsonProperty("objectDestine")
    private ObjectDomain objectDestine;

    @JsonProperty("nameObjectSource")
    private String nameObjectSource;

    @JsonProperty("identifyObjectSource")
    private String identifyObjectSource;

    @JsonProperty("idObjectSource")
    private String idObjectSource;

    @JsonProperty("nameObjectDestine")
    private String nameObjectDestine;

    @JsonProperty("identifyObjectDestine")
    private String identifyObjectDestine;

    @JsonProperty("idObjectDestine")
    private String idObjectDestine;


    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static NotificationResponse from(NotificationDomain domain) {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(domain.getId());
        notificationResponse.setName(domain.getName());
        notificationResponse.setDescription(domain.getDescription());
        notificationResponse.setIdentify(domain.getIdentify());

        notificationResponse.setSubject(domain.getSubject());
        notificationResponse.setRead(domain.getRead());
        if (domain.getType() != null && !domain.getType().isEmpty()){
            notificationResponse.setType(domain.getType());
            NotificationTypeEnum.NotificationTypeModel typeModelData = EnumMappeableService
                    .map(NotificationTypeEnum.class)
                    .stream().filter(
                            typeModel -> typeModel.name().equals(domain.getType()))
                    .findFirst().get();
            notificationResponse.setTypeDescription(typeModelData.description());
            notificationResponse.setTypeModel(typeModelData);
        }
        notificationResponse.setDateTime(domain.getDateTime());
        notificationResponse.setDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTime(domain.getDateTime()));

        if (domain.getUserSource() != null &&
                domain.getUserSource().getId() != null &&
                !domain.getUserSource().getId().isEmpty()){
            notificationResponse.setUserSource(UserResponse.from(domain.getUserSource()));
        }
        if (domain.getEmployeeSource() != null &&
                domain.getEmployeeSource().getId() != null &&
                !domain.getEmployeeSource().getId().isEmpty()){
            notificationResponse.setEmployeeSource(EmployeeResponse.from(domain.getEmployeeSource()));
        }
        if (domain.getUserDestine() != null &&
                domain.getUserDestine().getId() != null &&
                !domain.getUserDestine().getId().isEmpty()){
            notificationResponse.setUserDestine(UserResponse.from(domain.getUserDestine()));
        }
        if (domain.getEmployeeDestine() != null &&
                domain.getEmployeeDestine().getId() != null &&
                !domain.getEmployeeDestine().getId().isEmpty()){
            notificationResponse.setEmployeeDestine(EmployeeResponse.from(domain.getEmployeeDestine()));
        }

        notificationResponse.setNameObjectSource(domain.getNameObjectSource());
        notificationResponse.setIdObjectSource(domain.getIdObjectSource());
        notificationResponse.setIdentifyObjectSource(domain.getIdentifyObjectSource());

        notificationResponse.setNameObjectDestine(domain.getNameObjectDestine());
        notificationResponse.setIdObjectDestine(domain.getIdObjectDestine());
        notificationResponse.setIdentifyObjectDestine(domain.getIdentifyObjectDestine());

        notificationResponse.setObjectSource(domain.getObjectEntitySource());
        notificationResponse.setObjectDestine(domain.getObjectEntityDestine());

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            notificationResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            notificationResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            notificationResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        notificationResponse.setCreatedAt(domain.getCreatedAt());
        notificationResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        notificationResponse.setUpdatedAt(domain.getUpdatedAt());
        notificationResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        notificationResponse.setDeletedAt(domain.getDeletedAt());
        notificationResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return notificationResponse;
    }
}
