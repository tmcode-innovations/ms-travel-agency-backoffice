package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_change_requests")
public class ChangeRequestEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String status;
    private String type;
    private Boolean activeChangeRequest;
    private Set<String> categories;

    private LocalDateTime dateTime;

    private String nameObjectEmit;
    private String identifyObjectEmit;
    private String idObjectEmit;
    private @DocumentReference(lazy = true)
    EmployeeEntity employeeEmit;
    private @DocumentReference(lazy = true)
    UserEntity userEmit;
    private @DocumentReference(lazy = true)
    ObjectEntity objectEntityEmit;

    private String nameObjectReceive;
    private String identifyObjectReceive;
    private String idObjectReceive;
    private @DocumentReference(lazy = true)
    EmployeeEntity employeeReceive;
    private @DocumentReference(lazy = true)
    UserEntity userReceive;
    private @DocumentReference(lazy = true)
    ObjectEntity objectEntityReceive;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    QuotationEntity quotation;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeAttendedBy;
    private @DocumentReference(lazy = true)
    UserEntity userAttendedBy;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeSentBy;
    private @DocumentReference(lazy = true)
    UserEntity userSentBy;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeRefusedBy;
    private @DocumentReference(lazy = true)
    UserEntity userRefusedBy;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeCompletedBy;
    private @DocumentReference(lazy = true)
    UserEntity userCompletedBy;

    private @DocumentReference(lazy = true)
    EmployeeEntity employeeAcceptedBy;
    private @DocumentReference(lazy = true)
    UserEntity userAcceptedBy;
}
